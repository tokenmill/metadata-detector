(ns metadata-detector.title
  (:require [net.cgrand.enlive-html :as e]
            [metadata-detector.utils :refer [remove-tags remove-content non-blank best-candidate]]
            [metadata-detector.htmlmeta :as m]
            [metadata-detector.path :as path]
            [clojure.string :as s]))

(def title-cleaners
  [
   #"\s+\|.*"                                               ;Title | Host.com
   #"\s+\-\s+\w+\.\w+$"                                     ; Title - Host.com
   ])

(defn- clean-string [^String string cleaners]
  (assert (or (nil? string) (string? string)) "Only strings can be cleaned")
  (if cleaners
    (when-not (s/blank? string)
      (s/trim (reduce #(s/replace %1 %2 " ") string cleaners)))
    string))

(defn clean-title [^String string] (clean-string string title-cleaners))

(defn extract-title-tag [page]
  (clean-title (m/extract-tag page "title")))

(defn retrieve-title-area [page]
  (-> page
      remove-tags
      (e/select path/title-selectors)
      remove-content
      non-blank
      best-candidate
      doall
      (clean-title)))

(defn extract-head-title [page]
  (-> page
      (e/select #{[:title]})
      first
      :content
      first
      clean-title))

(defn detect-title
  "Extract article title"
  [url page]
  (or (extract-title-tag page)
      (retrieve-title-area page)
      (extract-head-title page)))
