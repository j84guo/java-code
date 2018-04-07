package media.controller;

import media.broker.news.model.NewsArticlesResponse;
import media.service.MediaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private MediaService service;

    @RequestMapping("/headlines")
    public NewsArticlesResponse getHeadlines(@RequestParam Map<String,String> queryStringMap){
        return service.getHeadlines(queryStringMap);
    }

    @RequestMapping("/everything")
    public NewsArticlesResponse getEverything(@RequestParam Map<String,String> queryStringMap){
        return service.getEverything(queryStringMap);
    }
}
