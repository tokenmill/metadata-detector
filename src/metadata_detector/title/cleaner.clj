(ns metadata-detector.title.cleaner
  (:require [clojure.string :as s]))

(def title-cleaners
  [
   #"\s+\|.*"                                               ;Title | Host.com
   #"\s+\-\s+\w+\.\w+$"                                     ; Title - Host.com
   #"\u00A0"
   ])

(defn- clean-string [^String string cleaners]
  (assert (or (nil? string) (string? string)) "Only strings can be cleaned")
  (if cleaners
    (when-not (s/blank? string)
      (s/trim (reduce #(s/replace %1 %2 " ") string cleaners)))
    string))

(defn clean-title [^String string] (clean-string string title-cleaners))