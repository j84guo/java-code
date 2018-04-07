package media.broker.social;

import media.broker.social.model.twitter.AccessResponse;
import media.broker.social.model.twitter.TweetSearchResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.Base64;

@Component
public class SocialClient {

    @Value("${social.twitter.consumerKey}")
    private String consumerKey;

    @Value("${social.twitter.consumerSecret}")
    private String consumerSecret;

    @Autowired
    private RestTemplate restTemplate;

    private String accessToken;

    private static final String baseUrl = "https://api.twitter.com/1.1";
    private static final String accessUrl = "https://api.twitter.com/oauth2/token";

    public TweetSearchResponse searchTwitterTweets(Map<String,String> queryStringMap){

        if(accessToken == null){
            accessToken = getAccessToken();
        }

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(baseUrl + "/search/tweets.json");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity<?> request = new HttpEntity<>(headers);

        for(String key : queryStringMap.keySet()){
            if(key.equals("q")){
                uriBuilder.queryParam(key, queryStringMap.get(key));
            }
        }

        ResponseEntity<TweetSearchResponse> response = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.GET,
                request,
                TweetSearchResponse.class
        );

        return response.getBody();
    }

    public String getAccessToken(){

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(accessUrl);

        String consumerKeyAndSecret = consumerKey + ":" + consumerSecret;
        String accessTokenCredentials = new String(Base64.getEncoder().encode(consumerKeyAndSecret.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + accessTokenCredentials);
        headers.set("Content-Type", "application/x-www-form-urlencoded");
        HttpEntity<String> request = new HttpEntity<String>("grant_type=client_credentials",headers);

        ResponseEntity<AccessResponse> response = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.POST,
                request,
                AccessResponse.class
        );

        AccessResponse accessResponse = response.getBody();
        return accessResponse.getAccess_token();
    }
}
