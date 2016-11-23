package com.nuoman.westernele.informationDetail.model;

/**
 * 审核请求参数
 * Created by 杨小过 on 2016/11/23.
 */

public class CheckParameter {

    private String nodeId;
    private String userId;
    private String oper;//1:通过，2：驳回

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }
}
