package media.broker.social.model.twitter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchMetadata {
    private String max_id;
    private String since_id;
    private String refresh_url;
    private String next_results;
    private String count;
    private String completed_in;
    private String since_id_str;
    private String query;
    private String max_id_str;
}
