package com.nuoman.westernele.numberQuery.model;

/**
 * 工号查询请求参数
 * Created by 杨小过 on 2016/11/22.
 */

public class NumberQueryParameter {

    private String orderNo;
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
