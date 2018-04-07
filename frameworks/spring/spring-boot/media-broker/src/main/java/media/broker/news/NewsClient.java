package media.broker.news;

import media.broker.news.model.NewsArticlesResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

@Component
public class NewsClient {

    @Value("${news.apiKey}")
    private String apiKey;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Autowired
    private RestTemplate restTemplate;

    private static final String baseUrl = "https://newsapi.org/v2";

    public NewsArticlesResponse getHeadlines(Map<String,String> queryStringMap) {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(baseUrl + "/top-headlines");

        for(String key : queryStringMap.keySet()){
            if(key.equals("q")){
                uriBuilder.queryParam(key, queryStringMap.get(key));
            }else if(key.equals("category")){
                uriBuilder.queryParam(key, queryStringMap.get(key));
            }else if(key.equals("sources")){
                uriBuilder.queryParam(key, queryStringMap.get(key));
            }else if(key.equals("country")){
                uriBuilder.queryParam(key, queryStringMap.get(key));
            }
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", apiKey);
        HttpEntity<?> request = new HttpEntity<>(headers);

        System.out.println("Making request to " + uriBuilder.toUriString());

        ResponseEntity<NewsArticlesResponse> response = restTemplate.exchange(
            uriBuilder.toUriString(),
            HttpMethod.GET,
            request,
            NewsArticlesResponse.class
        );

        return response.getBody();
    }

    public NewsArticlesResponse getEverything(Map<String,String> queryStringMap) {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(baseUrl + "/everything");

        for(String key : queryStringMap.keySet()){
            if(key.equals("q")){
                uriBuilder.queryParam(key, queryStringMap.get(key));
            }else if(key.equals("category")){
                uriBuilder.queryParam(key, queryStringMap.get(key));
            }else if(key.equals("sources")){
                uriBuilder.queryParam(key, queryStringMap.get(key));
            }else if(key.equals("country")){
                uriBuilder.queryParam(key, queryStringMap.get(key));
            }else if(key.equals("from")){
                uriBuilder.queryParam(key, queryStringMap.get(key));
            }else if(key.equals("to")){
                uriBuilder.queryParam(key, queryStringMap.get(key));
            }else if(key.equals("sortBy")){
                uriBuilder.queryParam(key, queryStringMap.get(key));
            }else if(key.equals("page")){
                uriBuilder.queryParam(key, queryStringMap.get(key));
            }else if(key.equals("domains")){
                uriBuilder.queryParam(key, queryStringMap.get(key));
            }
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", apiKey);
        HttpEntity<?> request = new HttpEntity<>(headers);

        ResponseEntity<NewsArticlesResponse> response = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.GET,
                request,
                NewsArticlesResponse.class
        );

        return response.getBody();
    }
}
