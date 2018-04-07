package media.broker.news;

import media.broker.news.model.NewsArticlesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class NewsBroker {

    @Autowired
    private NewsClient newsClient;

    public NewsArticlesResponse getHeadlines(Map<String,String> queryStringMap){
        return newsClient.getHeadlines(queryStringMap);
    }

    public NewsArticlesResponse getEverything(Map<String,String> queryStringMap){
        return newsClient.getEverything(queryStringMap);
    }
}
