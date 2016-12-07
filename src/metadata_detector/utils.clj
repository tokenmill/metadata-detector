(ns metadata-detector.utils
  (:require [net.cgrand.enlive-html :as e]
            [clojure.string :as s]))

(def content-to-remove #{"Twitter" "Google+" "Facebook", "Associated Press"})

(defn re-first-text-pred
  "Predicate to match first string of content in hierarchy of nodes
  So in <x>X<y>123<z>abc</z></y></x> (match-first-text-pred '123')
  will get <y> tag"
  [x]
  (letfn [(match-first-text [r n]
            (if-let [txt (-> n :content first str)]
              (re-find r txt)))]
    (e/pred
      (partial match-first-text x))))

(defn remove-tags
  "Remove html nodes which are unlikely to contain byline but mess things up"
  [nodes]
  (e/at nodes
        #{[:option] [:input] [:script] [:img]
          [:.byline-title]
          ;Remove all nodes which have class containing those strings
          [:* (e/attr-contains :class "Follow")]
          [:* (e/attr-contains :class "Title")]
          [:* (e/attr-contains :class "previous")]
          [:* (e/attr-contains :class "blogger")]
          [:* (e/attr-contains :class "next")]
          [:* (e/attr-contains :class "photo-")]
          [(re-first-text-pred #"View more")]}
        {}))

(defn remove-content [nodes] (remove #(-> % e/text s/trim content-to-remove) nodes))


(defn- node-text
  "Get node text joined with spaces"
  [node]
  (-> node e/text))

(defn non-blank
  "Get first node with non blank content"
  [nodes] (remove #(-> % node-text s/blank?) nodes))

(defn- blacklisted-date [text]
  (re-matches #"(?s)(?:\d{2}:\d{2} [AP]M)" text))

(defn best-candidate
  "Try to choose the best note to be metadata, in case we have multi matches."
  [nodes]
  (->> nodes
       (map #(vector % (node-text %)))
       (remove #(blacklisted-date (second %)))
       (remove #(> 8 (count (second %))))
       (map second)
       (filter string?)
       (first)))