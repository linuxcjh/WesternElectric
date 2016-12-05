package com.nuoman.westernele.projectSummary.model;

/**
 * 项目概览请求参数实体
 * Created by 杨小过 on 2016/12/5.
 */

public class ProjectSummaryParameter {

    private String userId;
    private String kind;//0:未开始项目，1：进行中，2：已完成
    private String pages;//页码从0开始

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

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
