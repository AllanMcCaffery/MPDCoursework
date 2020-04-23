package org.me.gcu.mpd.model;

public class Incidents {

    private String title;
    private String description;
    private String link;
    private String latitude;
    private String longitude;
    private String author;
    private String comments;
    private String pubDate;


    public Incidents() {
    }

    public Incidents(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    @Override
    public String toString() {
        return "title='" + title + "\n" +
                ", description='" + description + "\n" +
                ", link='" + link + "\n" +
                ", latitude='" + latitude + "\n" +
                ", longitude='" + longitude + "\n" +
                ", author='" + author + "\n" +
                ", comments='" + comments + "\n" +
                ", pubDate='" + pubDate + '\'' +
                '}';
    }
}