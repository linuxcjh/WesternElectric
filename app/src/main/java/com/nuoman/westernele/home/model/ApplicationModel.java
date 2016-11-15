package com.nuoman.westernele.home.model;

/**
 * AUTHOR: Alex
 * DATE: 19/11/2015 19:45
 */
public class ApplicationModel {

    private int ids;
    private String title;
    private boolean isVisible;

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
