(ns metadata-detector.core
  (:require [clojure.string :as s]
            [net.cgrand.enlive-html :as e]
            [metadata-detector.utils :refer [remove-tags]]
            [metadata-detector.title :refer [detect-title]]
            [metadata-detector.date :refer [detect-publish-date]]
            [metadata-detector.path :as path]
            [metadata-detector.htmlmeta :as m])
  (:import (java.net URL)
           (lt.tokenmill.metadatadetector DocumentMetadata))
  (:gen-class
    :name lt.tokenmill.metadatadetector.MetadataDetector
    :methods [[detect [String String] lt.tokenmill.metadatadetector.DocumentMetadata]]))

(defn detect
  "Detects metadata of the article: title and date."
  [^String url ^String html]
  (let [page (e/html-snippet html)]
    {:title (detect-title url page)
     :date  (detect-publish-date url page)}))

(defn -detect [_ url html]
  (let [{:keys [title date]} (detect url html)]
    (DocumentMetadata. title date)))
