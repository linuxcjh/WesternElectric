package com.nuoman.westernele.numberDetail.model;

import java.io.Serializable;

/**
 * 进度图标请求返回信息
 * Created by 杨小过 on 2016/11/24.
 */

public class ProgressChart implements Serializable {
    private NodeStatus nodeStatus;
    private String orderId;
    private String produceStatus;
    private String purchaseStatus;
    private String techStatus;
    private String totalCompletePercent;
    private String transportStatus;

    public NodeStatus getNodeStatus() {
        return nodeStatus;
    }

    public void setNodeStatus(NodeStatus nodeStatus) {
        this.nodeStatus = nodeStatus;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProduceStatus() {
        return produceStatus;
    }

    public void setProduceStatus(String produceStatus) {
        this.produceStatus = produceStatus;
    }

    public String getPurchaseStatus() {
        return purchaseStatus;
    }

    public void setPurchaseStatus(String purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }

    public String getTechStatus() {
        return techStatus;
    }

    public void setTechStatus(String techStatus) {
        this.techStatus = techStatus;
    }

    public String getTotalCompletePercent() {
        return totalCompletePercent;
    }

    public void setTotalCompletePercent(String totalCompletePercent) {
        this.totalCompletePercent = totalCompletePercent;
    }

    public String getTransportStatus() {
        return transportStatus;
    }

    public void setTransportStatus(String transportStatus) {
        this.transportStatus = transportStatus;
    }
}
