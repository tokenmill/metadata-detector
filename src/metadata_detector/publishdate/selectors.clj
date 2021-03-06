(ns metadata-detector.publishdate.selectors
  (:require [net.cgrand.enlive-html :as e]))

(def date-attr-selectors
  {#{[:body :* (e/attr= :itemprop "datePublished")]
     [:body :* (e/attr= :itemprop "dateCreated")]
     [:body :* (e/attr= :itemprop "dateModified")]
     [:body :* (e/attr= :property "dc:date")]
     [:time]} [:content :datetime]})

(def date-content-selectors
  #{[:* (e/attr= :property "dc:issued")]
    [:* (e/attr= :property "dc:date")]
    [:body :* (e/attr= :itemprop "datePublished")]
    [:body :* (e/attr= :itemprop "dateCreated")]
    [:.article-timestamp] [:.authored_date]
    [:dtstamp] [:.entry-date] [:.news_posttime] [:.posted]  [:.pdate]
    [:.dateString] [:#date] [:#newsdate] [:.date] [:.post-date] [:.datestamp] [:.dateline] [:.createdate] [:.created] [:.commondate]
    [:.articleDate] [:.article-date] [:.itemDateCreated]
    [:.published] [:.pubDate] [:.publishedDate] [:.published-date] [:#publicationDate] [:#news-date]
    [:* (e/attr-contains :class "_date")]
    [:* (e/attr-contains :class "publishDate")]
    [:* (e/attr-contains :class "publish-time")]
    [:* (e/attr-contains :class "publicationdate")]
    [:* (e/attr-contains :class "article-timestamp")]
    [:.time-bar] [:span.time] [:time]
    [:* :div.articletext-holder :div.channel-date-container.sport :span]
    [:div#content :span.prdata]
    [:div.byline-date :span.publish-date]
    [:div.publish-date :a]
    })
