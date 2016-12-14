(ns metadata-detector.title.title
  (:require [clojure.string :as s]
            [net.cgrand.enlive-html :as e]
            [metadata-detector.title.cleaner :refer [clean-title]]
            [metadata-detector.title.selectors :refer [title-selectors]]
            [metadata-detector.commons.utils :refer [remove-tags remove-content non-blank best-candidate]]
            [metadata-detector.commons.htmlmeta :as m]))

(defn extract-title-tag [page]
  (clean-title (m/extract-tag page "title")))

(defn retrieve-title-area [page]
  (-> page
      remove-tags
      (e/select title-selectors)
      remove-content
      non-blank
      best-candidate
      doall
      (clean-title)))

(defn extract-head-title [page]
  (-> page
      (e/select #{[:title]})
      first
      (e/text)
      (clean-title)))

(defn detect-title
  "Extract article title"
  [url page]
  (or (retrieve-title-area page)
      (extract-title-tag page)
      (extract-head-title page)))
