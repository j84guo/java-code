package media.broker.news.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsArticlesResponse {

    private String status;
    private int totalResults;
    private Article[] articles;
}
