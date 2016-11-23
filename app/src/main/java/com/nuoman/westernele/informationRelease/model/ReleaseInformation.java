package com.nuoman.westernele.informationRelease.model;

/**
 * 发布的信息实体类
 * Created by 杨小过 on 2016/11/22.
 */

public class ReleaseInformation {

    /*
    {
        "fdate":"2016-11-06 12:11",
        "nodeId":"2",
        "nodeName":"b",
        "projectId":"1",
        "projectName":"测试测试",
        "projectState":"1",0:待审核，1：通过审核，2：拒绝
        "saleOrderId":"1",
        "saleOrderNo":"XD0000000001"
    }
     */

    private String fdate;
    private String nodeId;
    private String nodeName;
    private String projectId;
    private String projectName;
    private String projectState;
    private String saleOrderId;
    private String saleOrderNo;

    public String getFdate() {
        return fdate;
    }

    public void setFdate(String fdate) {
        this.fdate = fdate;
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

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectState() {
        return projectState;
    }

    public void setProjectState(String projectState) {
        this.projectState = projectState;
    }

    public String getSaleOrderId() {
        return saleOrderId;
    }

    public void setSaleOrderId(String saleOrderId) {
        this.saleOrderId = saleOrderId;
    }

    public String getSaleOrderNo() {
        return saleOrderNo;
    }

    public void setSaleOrderNo(String saleOrderNo) {
        this.saleOrderNo = saleOrderNo;
    }
}
