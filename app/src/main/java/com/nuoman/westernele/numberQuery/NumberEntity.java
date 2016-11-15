package com.nuoman.westernele.numberQuery;

import java.io.Serializable;

/**
 * 查询所得的工号实体
 * Created by 杨小过 on 2016/11/14.
 */

public class NumberEntity implements Serializable {

    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
