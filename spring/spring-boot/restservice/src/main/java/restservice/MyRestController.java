package restservice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MyRestController {

    private static final String template = "Hello, %s!";

    @RequestMapping(value="/json")
    public Greeting json() {
        return new Greeting(1, "This is a json string.");
    }

    @RequestMapping(value="/text")
    public String text() {
        return "This is some plain text.";
    }

    @RequestMapping(value="/quote")
    public Quote quote() {
        RestTemplate restTemplate = new RestTemplate();
        Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
        return quote;
    }

}
