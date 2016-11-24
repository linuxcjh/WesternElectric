package com.nuoman.westernele.numberDetail.model;

import java.io.Serializable;
import java.util.List;

/**
 * progressChart中的NodeStatus
 * Created by 杨小过 on 2016/11/24.
 */

public class NodeStatus implements Serializable {
    private List<NodeStatusItem> transport;
    private List<NodeStatusItem> purchase;
    private List<NodeStatusItem> production;
    private List<NodeStatusItem> tech;

    public List<NodeStatusItem> getTransport() {
        return transport;
    }

    public void setTransport(List<NodeStatusItem> transport) {
        this.transport = transport;
    }

    public List<NodeStatusItem> getProduction() {
        return production;
    }

    public void setProduction(List<NodeStatusItem> production) {
        this.production = production;
    }

    public List<NodeStatusItem> getPurchase() {
        return purchase;
    }

    public void setPurchase(List<NodeStatusItem> purchase) {
        this.purchase = purchase;
    }

    public List<NodeStatusItem> getTech() {
        return tech;
    }

    public void setTech(List<NodeStatusItem> tech) {
        this.tech = tech;
    }
}
