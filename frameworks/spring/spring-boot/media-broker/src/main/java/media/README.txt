/*********
Media API:
*********/

Consolidates news and social media sources into a common broker :

-> *** start with one 3pp *** google news - https://newsapi.org
-> associated press - https://developer.ap.org/ap-content-api
-> cnn - https://developer.cnn.com/docs/read/basics/API_Keys
-> new - york times https://developer.nytimes.com/
-> the - guardian http://open-platform.theguardian.com/documentation/
-> bbc - http://docs.bbcnewslabs.co.uk/
-> financial times - https://developer.ft.com/portal
-> twitter - https://developer.twitter.com/en/docs
-> facebook - https://developers.facebook.com/

Abstract endpoints :
-> news
-> social

Structure :
-> one 3pp per endpoint

-> main controller
    -exposes abstract endpoints
    -performs validation
    -request preparation

-> main service
    -autowired to controller
    -provides methods matching each controller endpoint

-> broker per 3pp
    -provider specific conversion logic

-> client per 3pp
    -autowired to broker
    -gets provider response

-> response converter
    -autowired to broker
    -converts third party providers to Media response

-> media model
    -media response objects

-> broker model
    -3pp response objects

-> database
    -store sources
    -possibly other data as well
    -mysql/postgresql/mongodb using hibernate jpa
