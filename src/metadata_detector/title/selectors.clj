(ns metadata-detector.title.selectors
  (:require [net.cgrand.enlive-html :as e]))

(def title-selectors
  #{[[:h1 (e/attr-contains :class "-title")]]
    [[:h1 (e/attr-contains :class "Headline")]]
    [:header :h1.post__title]
    [:div.leftcolumn :h1]
    [:section#article :h1#F_Title]})