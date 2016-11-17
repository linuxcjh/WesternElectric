package com.nuoman.westernele.model;

import java.io.Serializable;

/**
 * AUTHOR: Alex
 * DATE: 9/8/2016 10:38
 */
public class BaseTransModel implements Serializable {

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

