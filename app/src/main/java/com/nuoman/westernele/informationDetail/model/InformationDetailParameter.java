package com.nuoman.westernele.informationDetail.model;

/**
 * 项目详情请求参数
 * Created by 杨小过 on 2016/11/23.
 */

public class InformationDetailParameter {

    private String userId;
    private String nodeId;

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
