package com.nuoman.westernele.projectmanage.model;

import java.util.List;

/**
 * AUTHOR: Alex
 * DATE: 24/11/2016 11:32
 */

public class ProjectDetailItemModel {

    private List<ProjectDetailModel> transport;
    private List<ProjectDetailModel> purchase;
    private List<ProjectDetailModel> production;
    private List<ProjectDetailModel> tech;

    public List<ProjectDetailModel> getTransport() {
        return transport;
    }

    public void setTransport(List<ProjectDetailModel> transport) {
        this.transport = transport;
    }

    public List<ProjectDetailModel> getPurchase() {
        return purchase;
    }

    public void setPurchase(List<ProjectDetailModel> purchase) {
        this.purchase = purchase;
    }

    public List<ProjectDetailModel> getProduction() {
        return production;
    }

    public void setProduction(List<ProjectDetailModel> production) {
        this.production = production;
    }

    public List<ProjectDetailModel> getTech() {
        return tech;
    }

    public void setTech(List<ProjectDetailModel> tech) {
        this.tech = tech;
    }
}
