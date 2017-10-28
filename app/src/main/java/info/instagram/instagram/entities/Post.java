package info.instagram.instagram.entities;

import java.util.Date;
import java.util.List;

/**
 * Created by Naser on 10/28/2017.
 */

public class Post {

    private long id;
    private String title;
    private String slug;
    private String excerpt;
    private String content;
    private String pubDate;
    private String lastModified;
    private Boolean isPublished;
    private List<String> categories;
    private List<Comment> comments;

    public Post() {
    }

    public Post(long id, String title, String slug, String excerpt, String content, String pubDate, String lastModified, Boolean isPublished, List<String> categories, List<Comment> comments) {
        this.id = id;
        this.title = title;
        this.slug = slug;
        this.excerpt = excerpt;
        this.content = content;
        this.pubDate = pubDate;
        this.lastModified = lastModified;
        this.isPublished = isPublished;
        this.categories = categories;
        this.comments = comments;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public Boolean getPublished() {
        return isPublished;
    }

    public void setPublished(Boolean published) {
        isPublished = published;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }


}




