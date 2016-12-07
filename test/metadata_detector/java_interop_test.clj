(ns metadata-detector.java-interop-test
  (:require [clojure.test :refer :all])
  (:import (lt.tokenmill.metadatadetector MetadataDetector)
           (lt.tokenmill.metadatadetector DocumentMetadata)))

(deftest JavaInteropTest
  (testing "types"
    (let [^MetadataDetector metadata-detector (MetadataDetector.)
          ^DocumentMetadata document-metadata (.detect metadata-detector
                                     "cleveland.com/consumeraffairs/index.ssf/2013/02/a_"
                                     (slurp "test/data/en/cleveland.html"))]
      (is (= "That 'Who's Who' invite aims at your ego -- and your wallet: Plain Dealing"
             (.getTitle document-metadata)))
      (is (= "Tuesday, February 12, 2013,  2:00 PM"
             (.getPublishDate document-metadata))))))
