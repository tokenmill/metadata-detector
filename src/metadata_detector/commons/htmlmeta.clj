(ns metadata-detector.commons.htmlmeta
  (require [clojure.string :as s]
           [net.cgrand.enlive-html :as enl]))

(defn meta-val [meta-keys m]
  (let [entry (into {} (filter (fn [[k v]] (meta-keys k)) m))]
    (if (empty? (vals entry)) "blank" (-> entry vals first))))

(defn metatags
  "Get meta tags from html. Argument is string containing html page. Not URL.
   It returns map of metas so that name of meta is key and content value"
  [page]
  (reduce
    (fn [res {attrs :attrs}]
      (let [mkey (meta-val #{:name :property :itemprop} attrs)
            mvals (get res mkey #{})]                       ;some meta fields store value in :name some in :property
        (assoc res
          mkey
          (conj mvals (meta-val #{:content :value} attrs)))))
    {}
    (enl/select
      page
      [[:meta #{(enl/attr? :name) (enl/attr? :property) (enl/attr? :itemprop)}]])))

(defn clean-key
  "Strip namespaces and other not necessary stuff from html.meta key name"
  [k]
  (-> k
      s/lower-case
      (s/replace #"\w+:" "")))

(def tag-synonyms
  {"published_time"               "date"
   "article_date_original"        "date"
   "dashboard_published_date"     "date"
   "originalpublicationdate"      "date"
   "dc.date.issued"               "date"
   "datepublished"                "date"
   "dcterms.created"              "date"
   "sailthru.date"                "date"
   "revision_date"                "date"
   "article:published_time"       "date"
   "publicationdate"              "date"
   "cXenseParse:recs:publishtime" "date"
   "published-date"               "date"

   "og:title"                     "title"
   "dashboard_header"             "title"})

(defn normalize-key [k]
  (let [ck (clean-key k)]
    (get tag-synonyms ck ck)))


(defn extract-tag
  "Extract meta value based on generic key
  that is key can be 'author' but it will match 'ac:author' and a like'
  optionaly value-filter function can be supplied to remove values
  which are not good"
  ([page tag value-filter]
   (let [res                                                ;convert key value pairs into value
         (reduce
           (fn [r [_ v]] (into r v))
           #{}
           ;get only entires which match tag
           (filter
             (fn [[k _]]
               (= tag (normalize-key k)))
             (metatags page)))]
     (first (remove value-filter res))))
  ([page tag] (extract-tag page tag nil?)))
