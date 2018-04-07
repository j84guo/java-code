package media.broker.social.model.twitter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tweet {
    private String created_at;
    private String id_str;
    private String text;
    private User user;
}
