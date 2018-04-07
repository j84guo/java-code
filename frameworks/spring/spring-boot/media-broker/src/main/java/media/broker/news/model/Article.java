package media.broker.news.model;

import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class Article {

    private Source source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;

    public Article(){
    }
}
