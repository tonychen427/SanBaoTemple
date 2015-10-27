package com.foguangshan.modellibrary;

import java.util.List;

/**
 * Created by MacBookPro on 3/30/15.
 */
public class BookChapter {
    public int id;
    public String title;
    public String subTitle;
    public String date;
    public String author;
    public List<Component> componentLists;

    public BookChapter() {
    }

    public BookChapter(int id, String title, String subTitle, String date, String author, List<Component> componentList) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.date = date;
        this.author = author;
        this.componentLists = componentList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Component> getComponentList() {
        return componentLists;
    }

    public void setComponentList(List<Component> componentList) {
        this.componentLists = componentList;
    }
}
