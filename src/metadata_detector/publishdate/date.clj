(ns metadata-detector.publishdate.date
  (:require [clojure.string :as s]
            [net.cgrand.enlive-html :as e]
            [metadata-detector.publishdate.cleaner :refer [clean-date]]
            [metadata-detector.publishdate.selectors :as selectors]
            [metadata-detector.commons.utils :refer [remove-tags remove-content non-blank best-candidate]]
            [metadata-detector.commons.htmlmeta :as m]))

(defn retrieve-attributes
  [page]
  (->> selectors/date-attr-selectors
       (mapcat
         (fn [[selector attributes]]
           (mapcat
             (fn [node]
               (let [node-attrs (:attrs node)
                     attr-values (->> attributes
                                      (map #(get node-attrs %))
                                      (remove s/blank?))]
                 attr-values))
             (e/select page selector))
           ))
       (sort-by (comp - count))
       (first)))

(defn retrieve-date-area [page]
  (-> page
      remove-tags
      (e/select selectors/date-content-selectors)
      remove-content
      non-blank
      best-candidate
      doall
      (clean-date)))

(defn detect-url-date
  "Given url try to fetch date embeded in url. Thus having
  edition.cnn.com/2012/12/04/world/asia/nauru-ocean-danger/index.html?hpt=hp_c2
  we get back - 2012/12/04"
  [url]
  (second (re-find
            #"/(20\d{2}[/-]([01]\d|\w{3})[/-]?[0123]\d)/"
            url)))

(defn with-regexes
  [page]
  (when-let [matched (->> (pr-str page)
                  (re-find #".com/archive/\d{4}/\d{2}\\\">.*</a>"))]
    (s/replace matched #".*\\\">|</a>" "")))

(defn detect-publish-date
  "Extract article date. Check multiple sources:
  url, metadata, html structure, text"
  ^String [url page]
  (or (m/extract-tag page "date")
      (retrieve-attributes page)
      (retrieve-date-area page)
      (detect-url-date url)
      (with-regexes page)))
