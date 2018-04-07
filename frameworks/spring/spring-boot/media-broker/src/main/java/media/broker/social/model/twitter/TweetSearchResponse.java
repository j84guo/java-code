package media.broker.social.model.twitter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TweetSearchResponse {
    List<Tweet> statuses;

    @JsonProperty("search_metadata")
    private SearchMetadata searchMetadata;
}
