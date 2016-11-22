package com.nuoman.westernele.warningCenter.model;

/**
 * 预警信息实体类
 * Created by 杨小过 on 2016/11/21.
 */

public class WarningInformation {

    /*
    {
        "alertInfoId":"2",
        "alertKind":"1",   0是预警，1是通知
        "fDate":"2016.11.10",
        "noticeString":"测试测试审核未通过", 当是通知时显示
        "projectName":"项目： 测试测试", 当是预警时显示
        "status":""
    }
     */


    private String alertInfoId;
    private String alertKind;
    private String fDate;
    private String noticeString;
    private String projectName;
    private String status;

    public String getAlertInfoId() {
        return alertInfoId;
    }

    public void setAlertInfoId(String alertInfoId) {
        this.alertInfoId = alertInfoId;
    }

    public String getAlertKind() {
        return alertKind;
    }

    public void setAlertKind(String alertKind) {
        this.alertKind = alertKind;
    }

    public String getfDate() {
        return fDate;
    }

    public void setfDate(String fDate) {
        this.fDate = fDate;
    }

    public String getNoticeString() {
        return noticeString;
    }

    public void setNoticeString(String noticeString) {
        this.noticeString = noticeString;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
