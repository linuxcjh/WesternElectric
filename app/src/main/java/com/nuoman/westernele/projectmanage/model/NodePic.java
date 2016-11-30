package com.nuoman.westernele.projectmanage.model;

/**
 * 节点照片的对象类
 * Created by 杨小过 on 2016/11/30.
 */

public class NodePic {

    /*
    {
        "actualEndDate":"",
        "picUrl":"",
        "planEndDate":"",
        "status":"0"
    }
     */

    private String actualEndDate;
    private String picUrl;
    private String planEndDate;
    private String status;

    public String getActualEndDate() {
        return actualEndDate;
    }

    public void setActualEndDate(String actualEndDate) {
        this.actualEndDate = actualEndDate;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getPlanEndDate() {
        return planEndDate;
    }

    public void setPlanEndDate(String planEndDate) {
        this.planEndDate = planEndDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
