(ns metadata-detector.publishdate.cleaner
  (:require [clojure.string :as s]))

(def date-cleaners
  [
   #"\t"
   #" " ;Do not touch this is &nbsp;
   #"(?m)Last updated at.*"
   #"(?m)Last updated.*"
   #"(?m)As of closing*"
   #"(?m)Source:.*"
   #"(?im)(published at|published|updated|posted)\:?"
   #"--$"
   ])


(defn- clean-string [^String string cleaners]
  (assert (or (nil? string) (string? string)) "Only strings can be cleaned")
  (if cleaners
    (when-not (s/blank? string)
      (s/trim (reduce #(s/replace %1 %2 " ") (s/trim string) cleaners)))
    string))

(defn clean-date [^String string] (clean-string string date-cleaners))
