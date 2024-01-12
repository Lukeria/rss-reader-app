package com.course.spp.rss_reader_app.dto;

import java.util.List;

public class FeedItemDto {

    private String title;
    private String link;
    private String description;
    private String source;
    private String publishDate;
    private String author;
    private List<String> categories;
    private boolean saved;

    public FeedItemDto(String title, String link, String description, String source, String publishDate, String author) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.source = source;
        this.publishDate = publishDate;
        this.author = author;
    }

    public FeedItemDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }
}
