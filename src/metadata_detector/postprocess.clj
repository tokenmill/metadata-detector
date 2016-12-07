(ns metadata-detector.postprocess
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
   ])

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
(defn clean-date [^String string] (clean-string string date-cleaners))
