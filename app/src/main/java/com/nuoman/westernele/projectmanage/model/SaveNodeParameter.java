package com.nuoman.westernele.projectmanage.model;

/**
 * 保存节点信息请求参数
 * Created by 杨小过 on 2016/11/30.
 */

public class SaveNodeParameter {

    /*
    {
        planStartTime:"2016-11-01 10:00:00",
        planEndTime:"2016-12-15 12:00:02",
        actureStartTime:"2016-11-02 09:33:55",
        actureEndTime:"2016-12-16 18:03:58",
        saleOrderId:"1",
        operId:"2",
        nodeId:"1",
        nodePic:""
    }
     */

    private String planStartTime;
    private String planEndTime;
    private String actureStartTime;
    private String actureEndTime;
    private String saleOrderId;
    private String operId;
    private String nodeId;
    private String nodePic;

    public String getActureEndTime() {
        return actureEndTime;
    }

    public void setActureEndTime(String actureEndTime) {
        this.actureEndTime = actureEndTime;
    }

    public String getActureStartTime() {
        return actureStartTime;
    }

    public void setActureStartTime(String actureStartTime) {
        this.actureStartTime = actureStartTime;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodePic() {
        return nodePic;
    }

    public void setNodePic(String nodePic) {
        this.nodePic = nodePic;
    }

    public String getOperId() {
        return operId;
    }

    public void setOperId(String operId) {
        this.operId = operId;
    }

    public String getPlanEndTime() {
        return planEndTime;
    }

    public void setPlanEndTime(String planEndTime) {
        this.planEndTime = planEndTime;
    }

    public String getPlanStartTime() {
        return planStartTime;
    }

    public void setPlanStartTime(String planStartTime) {
        this.planStartTime = planStartTime;
    }

    public String getSaleOrderId() {
        return saleOrderId;
    }

    public void setSaleOrderId(String saleOrderId) {
        this.saleOrderId = saleOrderId;
    }
}
