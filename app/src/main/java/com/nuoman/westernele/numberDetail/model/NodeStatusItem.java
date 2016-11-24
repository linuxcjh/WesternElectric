package com.nuoman.westernele.numberDetail.model;

import java.io.Serializable;

/**
 * nodeStatus中的transport
 * Created by 杨小过 on 2016/11/24.
 */

public class NodeStatusItem implements Serializable {

    /*
    {
        "isVisible":"1",
        "nodeId":"12",
        "nodeKind":"transport",
        "nodeName":"l",
        "nodeStatus":"0" 0:未完成，1：已完成，2：逾期，3故障
     },
     */
    private String isVisible;
    private String nodeId;
    private String nodeKind;
    private String nodeName;
    private String nodeStatus;

    public String getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(String isVisible) {
        this.isVisible = isVisible;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeKind() {
        return nodeKind;
    }

    public void setNodeKind(String nodeKind) {
        this.nodeKind = nodeKind;
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
}
