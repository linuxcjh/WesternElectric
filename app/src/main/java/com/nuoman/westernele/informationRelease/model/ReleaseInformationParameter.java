package com.nuoman.westernele.informationRelease.model;

/**
 * 请求发布的信息的参数
 * Created by 杨小过 on 2016/11/22.
 */

public class ReleaseInformationParameter {

    private String userId;
    private String pages;

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
