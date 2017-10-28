package info.instagram.instagram.entities;

import java.util.Date;

/**
 * Created by Naser on 10/28/2017.
 */

public class Comment {

    private String id;
    private String author;
    private String email;
    private String content;
    private String pubDate;
    private boolean isAdmin;


    public Comment() {
    }

    public Comment(String id, String author, String email, String content, String pubDate, boolean isAdmin) {
        this.id = id;
        this.author = author;
        this.email = email;
        this.content = content;
        this.pubDate = pubDate;
        this.isAdmin = isAdmin;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }


}
