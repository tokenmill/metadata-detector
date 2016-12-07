(ns metadata-detector.title
  (:require [net.cgrand.enlive-html :as e]
            [metadata-detector.utils :refer [remove-tags remove-content non-blank best-candidate]]
            [metadata-detector.postprocess :refer [clean-title]]
            [metadata-detector.htmlmeta :as m]
            [metadata-detector.path :as path]))

(defn retrieve-title-area [page]
  (-> page
      remove-tags
      (e/select path/title-selectors)
      remove-content
      non-blank
      best-candidate
      doall
      (clean-title)))

(defn detect-title
  "Extract article title"
  [url page]
  (or (clean-title (m/extract-tag page "title"))
      (retrieve-title-area page)))
