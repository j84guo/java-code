package media.service;

import media.broker.news.NewsBroker;

import media.broker.news.model.NewsArticlesResponse;
import media.broker.social.SocialBroker;
import media.broker.social.model.twitter.TweetSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MediaService {

    @Autowired
    private NewsBroker newsBroker;

    @Autowired
    private SocialBroker socialBroker;

    public NewsArticlesResponse getHeadlines(Map<String,String> queryStringMap){
        return newsBroker.getHeadlines(queryStringMap);
    }

    public NewsArticlesResponse getEverything(Map<String,String> queryStringMap){
        return newsBroker.getEverything(queryStringMap);
    }

    public TweetSearchResponse searchTwitterTweets(Map<String,String> queryStringMap){
        return socialBroker.searchTwitterTweets(queryStringMap);
    }

    public String getMedia(){
        return "media response";
    }

    public String getSources(){
        return "sources response";
    }
}
