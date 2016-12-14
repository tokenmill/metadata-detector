(ns metadata-detector.title.selectors
  (:require [net.cgrand.enlive-html :as e]))

(def title-selectors
  #{[[:h1 (e/attr-contains :class "-title")]]
    [[:h1 (e/attr-contains :class "Headline")]]
    [:div.leftcolumn :h1]})