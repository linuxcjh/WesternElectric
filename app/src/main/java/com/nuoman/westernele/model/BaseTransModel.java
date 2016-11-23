package com.nuoman.westernele.model;

import java.io.Serializable;

/**
 * AUTHOR: Alex
 * DATE: 9/8/2016 10:38
 */
public class BaseTransModel implements Serializable {

    private String userId;
    private String search;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

