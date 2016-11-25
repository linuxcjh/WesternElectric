package com.nuoman.westernele.warningCenter.model;

/**
 * 请求已读预警信息的参数
 * Created by 杨小过 on 2016/11/25.
 */

public class MarkWarningParameter {

    private String userId;
    private String pages;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }
}
