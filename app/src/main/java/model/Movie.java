package model;

/**
 * Created by JasonChen on 2017/9/20.
 */

public class Movie {
    private String title;
    private String content;

    public Movie(String title, String content){
        this.title=title;
        this.content=content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
