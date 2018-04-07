package restservice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MyRestController {

    private static final String template = "Hello, %s!";

    @RequestMapping(value="/json")
    public Greeting json() {

        // objects are serialized into json by default
        return new Greeting(1, "This is a json string.");
    }

    @RequestMapping(value="/text")
    public String text() {

        // string's become plain text
        return "This is a plain text string.";
    }

    @RequestMapping(value="/quote")
    public Quote quote() {

        // fetches third party provider response
        RestTemplate restTemplate = new RestTemplate();
        Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
        return quote;
    }
}
