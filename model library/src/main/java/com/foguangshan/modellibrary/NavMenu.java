package com.foguangshan.modellibrary;

/**
 * Created by MacBookPro on 2/27/15.
 */
public class NavMenu {
    public int id;
    public String title;
    public String count;
    public Boolean showCount;

    public NavMenu(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public NavMenu(int id, String title, String count, Boolean showCount) {
        this.id = id;
        this.title = title;
        this.count = count;
        this.showCount = showCount;
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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public Boolean getShowCount() {
        return showCount;
    }

    public void setShowCount(Boolean showCount) {
        this.showCount = showCount;
    }
}
