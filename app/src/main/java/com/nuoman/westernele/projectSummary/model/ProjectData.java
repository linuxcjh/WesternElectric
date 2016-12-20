package com.nuoman.westernele.projectSummary.model;

/**
 * 项目汇总实体类
 * Created by 杨小过 on 2016/12/5.
 */

public class ProjectData {

    /*
        {"projectName":"陕西美鑫项目",
        "saleOrderName":"2017020221-228"
        }
     */

    private String projectName;
    private String saleOrderName;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getSaleOrderName() {
        return saleOrderName;
    }

    public void setSaleOrderName(String saleOrderName) {
        this.saleOrderName = saleOrderName;
    }
}
