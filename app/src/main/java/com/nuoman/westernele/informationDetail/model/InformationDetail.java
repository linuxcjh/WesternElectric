package com.nuoman.westernele.informationDetail.model;

/**
 * 项目详细信息
 * Created by 杨小过 on 2016/11/23.
 */

public class InformationDetail {

    /*
    {
        "actualEndDate":"2016.12.17",
        "actualStartDate":"2016.11.03",
        "nodeCheckStatus":"0",
        "nodeId":"2",
        "nodeName":"b",
        "nodeStatus":"1",
        "picUrl":"",
        "planEndDate":"2016.12.16",
        "planStartDate":"2016.11.02",
        "projectManager":"Aidy",
        "projectName":"测试测试"
     }
     */

    private String actualEndDate;
    private String actualStartDate;
    private String nodeCheckStatus;
    private String nodeId;
    private String nodeName;
    private String nodeStatus;
    private String picUrl;
    private String planEndDate;
    private String planStartDate;
    private String projectManager;
    private String projectName;

    public String getActualEndDate() {
        return actualEndDate;
    }

    public void setActualEndDate(String actualEndDate) {
        this.actualEndDate = actualEndDate;
    }

    public String getActualStartDate() {
        return actualStartDate;
    }

    public void setActualStartDate(String actualStartDate) {
        this.actualStartDate = actualStartDate;
    }

    public String getNodeCheckStatus() {
        return nodeCheckStatus;
    }

    public void setNodeCheckStatus(String nodeCheckStatus) {
        this.nodeCheckStatus = nodeCheckStatus;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeStatus() {
        return nodeStatus;
    }

    public void setNodeStatus(String nodeStatus) {
        this.nodeStatus = nodeStatus;
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

    public String getPlanStartDate() {
        return planStartDate;
    }

    public void setPlanStartDate(String planStartDate) {
        this.planStartDate = planStartDate;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
