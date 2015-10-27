package com.foguangshan.modellibrary;

/**
 * Created by MacBookPro on 3/20/15.
 */
public class Book {
    public Integer id;
    public String publisherDate;
    public String publisherName;
    public String publisherImageUrl;
    public String bookTitle;
    public String bookImageUrl;
    public String bookDescription;
    public String bookNumberOfFavorite;
    public String bookRating;
    public String bookUrl;

    public Book() {
    }

    public Book(Integer id, String publisherDate, String publisherName, String publisherImageUrl, String bookTitle, String bookImageUrl, String bookDescription, String bookNumberOfFavorite, String bookRating, String bookUrl) {
        this.id = id;
        this.publisherDate = publisherDate;
        this.publisherName = publisherName;
        this.publisherImageUrl = publisherImageUrl;
        this.bookTitle = bookTitle;
        this.bookImageUrl = bookImageUrl;
        this.bookDescription = bookDescription;
        this.bookNumberOfFavorite = bookNumberOfFavorite;
        this.bookRating = bookRating;
        this.bookUrl = bookUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPublisherDate() {
        return publisherDate;
    }

    public void setPublisherDate(String publisherDate) {
        this.publisherDate = publisherDate;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getPublisherImageUrl() {
        return publisherImageUrl;
    }

    public void setPublisherImageUrl(String publisherImageUrl) {
        this.publisherImageUrl = publisherImageUrl;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookImageUrl() {
        return bookImageUrl;
    }

    public void setBookImageUrl(String bookImageUrl) {
        this.bookImageUrl = bookImageUrl;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public String getBookNumberOfFavorite() {
        return bookNumberOfFavorite;
    }

    public void setBookNumberOfFavorite(String bookNumberOfFavorite) {
        this.bookNumberOfFavorite = bookNumberOfFavorite;
    }

    public String getBookRating() {
        return bookRating;
    }

    public void setBookRating(String bookRating) {
        this.bookRating = bookRating;
    }

    public String getBookUrl() {
        return bookUrl;
    }

    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }
}
