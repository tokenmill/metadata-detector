(ns metadata-detector.java-data-class
  (:gen-class
    :name         lt.tokenmill.metadatadetector.DocumentMetadata
    :constructors {[String String] []}
    :state        state
    :init         init
    :methods      [[getTitle [] String]
                   [getPublishDate [] String]]))

(defn -init
  [title publish-date]
  [[] {:title title :publish-date publish-date}])

(defn -getTitle [this]
  (:title (.state this)))

(defn -getPublishDate [this]
  (:publish-date (.state this)))

(defn -toString [this]
  (str "DocumentMetadata{"
       "title='" (:title (.state this))
       "', publishDate='" (:publish-date (.state this))
       "'}"))
