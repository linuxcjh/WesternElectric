package com.nuoman.westernele.numberQuery.model;

import java.io.Serializable;

/**
 * 查询所得的工号实体
 * Created by 杨小过 on 2016/11/14.
 */

public class Number implements Serializable {

    /*
    {
        "dataName":"XD0000000001",
        "id":"1"
    },
     */

    private String dataName;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }
}
