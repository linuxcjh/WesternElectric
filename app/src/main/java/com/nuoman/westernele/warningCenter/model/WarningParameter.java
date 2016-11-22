package com.nuoman.westernele.warningCenter.model;

/**
 * 请求预警信息参数的实体
 * Created by 杨小过 on 2016/11/21.
 */

public class WarningParameter {

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
