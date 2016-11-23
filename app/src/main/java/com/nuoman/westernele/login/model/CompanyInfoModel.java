package com.nuoman.westernele.login.model;

/**
 * AUTHOR: Alex
 * DATE: 23/11/2016 19:17
 */

public class CompanyInfoModel {

    private String dataName;
    private String id;

    public CompanyInfoModel(String dataName, String id) {
        this.dataName = dataName;
        this.id = id;
    }

    public CompanyInfoModel() {
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
