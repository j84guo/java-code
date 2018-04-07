package media.controller;

import media.broker.social.model.twitter.TweetSearchResponse;
import media.service.MediaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/social")
public class SocialController {

    @Autowired
    private MediaService service;

    @RequestMapping("/twitter/search")
    public TweetSearchResponse searchTwitterTweets(@RequestParam Map<String,String> queryStringMap){
        return service.searchTwitterTweets(queryStringMap);
    }
}
