package com.nuoman.westernele.billInformation.model;

/**
 * 账款信息请求参数
 * Created by 杨小过 on 2016/11/18.
 */

public class BillInformationParameter {

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
