(ns metadata-detector.core-test
  (:require [clojure.test :refer :all]
            [metadata-detector.core :refer [-detect]])
  (:import (lt.tokenmill.metadatadetector DocumentMetadata)))

(defn load-html [f] (slurp (str "test/data/" f ".html")))

(defn- get-byline [file-name url]
  (let [^DocumentMetadata metadata (-detect nil url (load-html file-name))]
    {:title       (.getTitle metadata)
     :date        (.getPublishDate metadata)}))

(deftest core-test

  (testing "Clevelend"
    (is (= {:title       "That 'Who's Who' invite aims at your ego -- and your wallet: Plain Dealing"
            :date        "Tuesday, February 12, 2013,  2:00 PM"}
           (get-byline "en/cleveland" "cleveland.com/consumeraffairs/index.ssf/2013/02/a_"))))

  (testing "The Age 2"
    (is (= {:title       "What Bayley did in Jill's final hours"
            :date        "March 13, 2013 - 1:32PM"}
           (get-byline "en/theage2" "theage.com.au/victoria/what-bayley-"))))

  (testing "The Age"
    (is (= {:title       "AFL clubs respond to our survey on drug use and illegal practices"
            :date        "February 9, 2013"}
           (get-byline "en/theage" "theage.com.au/afl/afl-news/afl-clubs"))))

  (testing "CBS News"
    (is (= {:title       "Despite decades of effort, immigration reform still eludes Congress"
            :date        "February 5, 2013, 1:02 PM"}
           (get-byline "en/cbsnews" "snews.com/8301-250_162-57567583/despite-"))))

  (testing "ABC News"
    (is (= {:title       "Galaxy May Be Full of 'Second Earths'"
            :date        "2013-02-06"}
           (get-byline "en/abcnews" "abcnews.go.com/Technology/earths"))))

  (testing "The Australian"
    (is (= {:title       "Isolating Fiji, Sri Lanka or Myanmar would serve no useful purpose"
            :date        "February 07, 2013"}
           (get-byline "en/theaustralian" "theaustralian.com.au/isolating-"))))

  (testing "Mercury News"
    (is (= {:title       "Cowboys involved in fatal wreck were close friends"
            :date        "12/09/2012 09:54:07 AM PST"}
           (get-byline "en/mercurynews" "mercurynews.com/49ers/ci_22157407/cowboys-"))))

  (testing "Chron"
    (is (= {:title       "Iranian official: Israel will regret Syria strike"
            :date        "Monday February  4, 2013"}
           (get-byline "en/chron" "chron.com/news/world/article/Senior-Ir"))))

  (testing "JPost"
    (is (= {:title       "French troops deploy to last of Mali rebel strongholds"
            :date        "01/30/2013 09:14"}
           (get-byline "en/jpost" "jpost.com/Headlines/Article.aspx?id="))))

  (testing "Telegraph"
    (is (= {:title       "Mystery over 'explosion' at Iran's Fordow nuclear site"
            :date        "2013-01-28"}
           (get-byline "en/telegraph" "telegraph.co.uk///9831282/Myste"))))

  (testing "Chicago Tribune"
    (is (= {:title       "Snow shield over Chicago fails after protecting city all night"
            :date        "2013-01-25"}
           (get-byline "en/chicagotribune" "chicagotribune.com/business/breaking/chi-dream"))))

  (testing "WSJ"
    (is (= {:title       "North Korea Issues Warning to the South"
            :date        "2013-01-25 05:58:00.0"}
           (get-byline "en/wsj" "wsj.com/article/SB100014"))))

  (testing "Computer World"
    (is (= {:title       "Twitter's Vine serves users' inner movie maker"
            :date        "2013-01-24"}
           (get-byline "en/computerworld" "computerworld.com/s/article/9236181/Twitter_s_Vine_"))))

  (testing "CS Monitor"
    (is (= {:title       "North Korea threatens new nuclear test 'aimed' at US"
            :date        "2013-01-24"}
           (get-byline "en/csmonitor" "csmonitor.com/World/terrorism-security/2013/0124/North-Korea-"))))

  (testing "CS Monitor 2"
    (is (= {:title       "Michael Bloomberg as counterweight to NRA: What are his chances?"
            :date        "2013-03-26"}
           (get-byline "en/csmonitor2" "csmonitor.com/USA/Politics/2013/0326/Michael-"))))

  (testing "Washington Post"
    (is (= {:title       "In her Capitol Hill swan song, Hillary Clinton shines"
            :date        "2013/01/24"}
           (get-byline "en/washingtonpost" "washingtonpost.com/blogs/she-the-people/wp/2013/01/24/in-her-"))))

  (testing "Bloomberg"
    (is (= {:title       "North Korea Threatens Nuclear Test to Derail U.S. Policies"
            :date        "2013-01-24T08:46:54Z"}
           (get-byline "en/bloomberg" "bloomberg.com/news/2013-01-24/north-korea-"))))

  (testing "BBC"
    (is (= {:title       "Scots GPs 'could save NHS £26m'"
            :date        "2013/01/24 01:24:31"}
           (get-byline "en/bbc" "bbc.co.uk/news/uk-scotland-"))))

  (testing "BBC 2"
    (is (= {:title       "Row over farm 'double payments'"
            :date        "2013/01/22 01:31:05"}
           (get-byline "en/bbc2" "bbc.co.uk/news/scie"))))

  (testing "NY Times"
    (is (= {:title       "North Korea Vows Nuclear Test and Threatens U.S."
            :date        "2013-01-24"}
           (get-byline "en/nytimes" "nytimes.com/2013/01/25/world/asia/north-korea-"))))

  (testing "Business Week"
    (is (= {:title       "NKorea warns that nuke test may be imminent"
            :date        "2013-01-23"}
           (get-byline "en/businessweek" "businessweek.com/ap/2013-01-23/nkorea"))))

  (testing "Evening Times"
    (is (= {:title       "Marks of success for school"
            :date        "2013-01-23T00:00:00+00:00"}
           (get-byline "en/eveningtimes" "eveningtimes.co.uk/news/marks-of-success-for-school"))))


  (testing "The Sunday Times"
    (is (= {:title       "Rod Stewart faked his driving test"
            :date        "Sun Jan 20 2013 00:01 UTC+0000"}
           (get-byline "en/thesundaytimes" "thesundaytimes.co.uk/sto/news/uk_news/People/article1198215"))))

  (testing "Daily Record"
    (is (= {:title       "Family tell how Scots hostage Kenny Whiteside was executed"
            :date        "2013-01-22"}
           (get-byline "en/dailyrecord" "dailyrecord.co.uk/news/scottish-news/family-tell"))))

  (testing "Scotsman"
    (is (= {:title       "Andrew Wilson: Damned with fulsome praise for a Liberal you can do business with"
            :date        "Sunday 20 January 2013 00:00"}
           (get-byline "en/scotsman" "scotsman.com/scotland-on-sunday/opinion/comment/andrew-wilson-"))))

  (testing "Fox"
    (is (= {:title       "What everybody needs to know about our Constitution and gun control"
            :date        "2013-01-16 08:11:37 EST"}
           (get-byline "en/fox" "foxnews.com/opinion/2013/01/16/what-everybody-needs"))))

  (testing "Guardian"
    (is (= {:title       "Delhi gang-rape: suspects in fatal attack on student to go to fast-track court"
            :date        "2013-01-17"}
           (get-byline "en/guardian" "guardian.co.uk/world/2013/jan/17/delhi-gang-"))))

  (testing "Daily Mail"
    (is (= {:title       "Football starlet, 20, jailed for animal cruelty after RSPCA inspectors find starving Staffordshire Bull Terrier and bodies of two dogs in his shed"
            :date        "2013-01-18 12:21:41 UTC"}
           (get-byline "en/dailymail" "dailymail.co.uk/news/article-2264425/Football-"))))

  (testing "Daily Mail Team Page"
    (is (= {:title       "West Ham United F.C. - Latest Team and Transfer News"
            :date        "08/02/16 12:33"}
           (get-byline "en/dailymail-team-page" "dailymail.co.uk/sport/teampages/west-ham-united.html"))))


  (testing "Time"
    (is (= {:title       "An Hour with Naftali Bennett: Is the Right-Wing Newcomer the New Face of Israel?"
            :date        "Jan. 18, 2013"}
           (get-byline "en/time" "time.com/2013/01/18/an-hour-with-naftali-"))))

  (testing "Reauters"
    (is (= {:title       "Sahara hostage holders make new threat"
            :date        "Fri Jan 18 13:08:57 UTC 2013"}
           (get-byline "en/reuters" "reuters.com/article/2013/01/18/us-sahara-crisis"))))

  (testing "RFERL"
    (is (= {:title       "Sokh: In One Tiny Territory, A World Of Problems"
            :date        "January 18, 2013"}
           (get-byline "en/rferl" "rferl.org/content/sokh-exclave-kyrgyzstan-uzbekistan/24"))))

  (testing "Boston Globe"
    (is (= {:title       "Mortgage business boosts Wells Fargo profit - The Boston Globe"
            :date        "2013/01/12"}
           (get-byline "en/bostonglobe" "bostonglobe.com/business/2013/01/12/mortgage-business-boosts-"))))

  (testing "FT"
    (is (= {:title       "Greek bailout monitors hold emergency summit"
            :date        "June 1, 2015 11:53 pm"}
           (get-byline "en/ft" "ft.com/intl/cms/s/0/433612d4-08a1-11e5-b38c-00144feabdc0.html"))))

  (testing "USA Today"
    (is (= {:title       "LeBron James, Heat too much for much-improved Lakers"
            :date        "2013-01-18T04:57:51.0000000Z"}
           (get-byline "en/usatoday" "usatoday.com/story/sports/nba/2013/01/18/lebron-james"))))

  (testing "MarketWatch"
    (is (= {:title       "Oil ends higher as OPEC output falls, global supply risks rise"
            :date        "Nov 16, 2015 3:13 p.m. ET"}
           (get-byline "en/marketwatch" "www.marketwatch.com/story/oil-futures-rise-on-worries-about-supply-disruptions-2015-11-16"))))

  (testing "CommodityOnline"
    (is (= {:title       "Four reasons why Gold may recover from recent low prices"
            :date        "13 Nov 2015"}
           (get-byline "en/commodityonline" "www.commodityonline.com/news/four-reasons-why-gold-may-recover-from-recent-low-prices-68351-3-68352.html"))))

  (testing "Platts"
    (is (= {:title       "Chicago gasoline prices fall to all-time low on abundant supply -  Oil"
            :date        "Houston (Platts)--16 Nov 2015 517 pm EST/2217 GMT"}
           (get-byline "en/platts" "http://www.platts.com/latest-news/oil/houston/chicago-gasoline-prices-fall-to-all-time-low-21479938"))))

  (testing "Gold-Eagle"
    (is (= {:title       "Sellers Of Gold And Silver Remain In Control"
            :date        "2015-11-14T00:00:00-05:00"}
           (get-byline "en/gold-eagle" "http://www.gold-eagle.com/article/sellers-gold-and-silver-remain-control"))))

  (testing "Nasdaq"
    (is (= {:title       "U.S. Oil Prices Fall Toward $40 a Barrel"
            :date        "2015-11-17T05:17:00-05:00"}
           (get-byline "en/nasdaq" "http://www.nasdaq.com/article/us-oil-prices-fall-toward-40-a-barrel-20151117-00815"))))

  (testing "Oilvoice"
    (is (= {:title       "Oil & Gas UK Welcomes Energy Secretary's Launch of MER UK Strategy Consultation as Consistent with Progressive Decarbonisation"
            :date        "Wednesday, November 18, 2015"}
           (get-byline "en/oilvoice" "http://www.oilvoice.com/n/Oil-Gas-UK-Welcomes-Energy-Secretarys-Launch-of-MER-UK-Strategy-Consultation-as-Consistent-with-Progressive-Decarbonisation/8e0a524a282f.aspx"))))

  (testing "Offshore Mag"
    (is (= {:title       "Shell finds success in deepwater Gulf of Mexico with Kaikias discovery appraisal"
            :date        "11/18/2015"}
           (get-byline "en/offshore-mag" "http://www.offshore-mag.com/articles/2015/11/shell-finds-success-once-again-in-deepwater-gulf-of-mexico-with-kaikias-appraisal.html"))))

  (testing "CityLab"
    (is (= {:title       "Where U.S. Metro Economies Are Growing or Shrinking"
            :date        "2015-11-20T10:33:36"}
           (get-byline "en/citylab" "http://www.citylab.com/work/2015/11/where-us-metro-economies-are-growing-or-shrinking/416808/"))))

  (testing "Benzinga"
    (is (= {:title       "iShares Silver Trust ETF (ETF:SLV), ETFS Silver Trust ETF (ETF:SIVR) - Traders Nail The Silver ETF Trade"
            :date        "November 12, 2015 1:35pm"}
           (get-byline "en/benzinga" "http://www.benzinga.com/trading-ideas/long-ideas/15/11/5974869/traders-nail-the-silver-etf-trade"))))

  (testing "BankOfEngland"
    (is (= {:title       "News Release - Publication of the PRA and FCA review into the failure of HBOS"
            :date        "19 November 2015"}
           (get-byline "en/bankofengland" "http://www.bankofengland.co.uk/publications/Pages/news/2015/086.aspx"))))

  (testing "MSN"
    (is (= {:title       "Asia markets trade mixed, Nikkei 225 slips 2.73%"
            :date        "2016-01-12T05:53:00.000Z"}
           (get-byline "en/msn" "http://www.msn.com/en-us/money/markets/asia-markets-trade-mixed-nikkei-225-slips-273percent/ar-CCp9y9"))))

  (testing "Express & Star"
    (is (= {:title       "Two arrested on suspicion of Syria related terrorism offences at Walsall home"
            :date        "2016-01-12T11:20Z"}
           (get-byline "en/expressandstar" "http://www.expressandstar.com/news/2016/01/12/two-arrested-on-suspicion-of-syria-related-terrorism-offences-at-walsall-home/"))))

  (testing "Metal"
    (is (= {:title       "Silver Going On 3rd Consecutive Annual Shortfall, Should Be Price Supportive:Thomson Reuters GFMS - Shanghai Metals Market"
            :date        "Nov 19, 2015 09:23 GMT"}
           (get-byline "en/metal" "http://www.metal.com/newscontent/81737_silver-going-on-3rd-consecutive-annual-shortfall-should-be-price-supportivethomson-reuters-gfms"))))

  (testing "ChannelNewAsia"
    (is (= {:title       "Singapore growing as precious metals trading hub"
            :date        "17 Nov 2015 00:10"}
           (get-byline "en/channelnewasia" "http://www.channelnewsasia.com/news/singapore/singapore-growing-as/2266920.html"))))

  (testing "ChannelNewAsia"
    (is (= {:title       "Singapore growing as precious metals trading hub"
            :date        "17 Nov 2015 00:10"}
           (get-byline "en/channelnewasia" "http://www.channelnewsasia.com/news/singapore/singapore-growing-as/2266920.html"))))

  (testing "IG"
    (is (= {:title       "FTSE races higher for second day"
            :date        "2015-11-17T16:01:37+0000"}
           (get-byline "en/ig" "http://www.ig.com/uk/market-update/2015/11/17/ftse-races-higher-for-second-day-29083"))))

  (testing "TheTimes"
    (is (= {:title       "A nation divided"
            :date        "Wed Feb 24 2016 00:01 UTC+1201"}
           (get-byline "en/thetimes" "http://www.thetimes.co.uk/tto/news/politics/article4697974.ece"))))

  (testing "globes.co.il"
    (is (= {:title       "Companies - P Updated - Globes English"
            :date        "14/09/2000, 13:19"}
           (get-byline "en/globes" "http://www.thetimes.co.uk/tto/news/politics/article4697974.ece"))))

  (testing "geektime.com"
    (is (= {:title       "Geektime"
            :date        "32 mins ago"}
           (get-byline "en/geektime" "http://www.geektime.com/"))))

  (testing "itbusinessnet.com"
    (is (= {:title       "FileFacets Announces $4 Million in Series A Funding"
            :date        nil}                               ; todo
           (get-byline "en/itbusinessnet" "http://www.geektime.com/")))))
