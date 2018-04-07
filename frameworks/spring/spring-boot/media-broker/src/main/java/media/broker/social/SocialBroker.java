package media.broker.social;

import media.broker.social.model.twitter.TweetSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SocialBroker {

    @Autowired
    private SocialClient socialClient;

    public TweetSearchResponse searchTwitterTweets(Map<String,String> queryStringMap){
        return socialClient.searchTwitterTweets(queryStringMap);
    }
}
