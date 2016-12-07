(ns metadata-detector.java-data-class-test
  (:require [clojure.test :refer :all])
  (:import (lt.tokenmill.metadatadetector DocumentMetadata)))

(deftest DocumentMetadataTest
  (testing "State test"
    (let [document-metadata (DocumentMetadata. "Test Title" "Test Publish Date")]
      (is (= "Test Title" (.getTitle document-metadata)))
      (is (= "Test Publish Date" (.getPublishDate document-metadata))))))
