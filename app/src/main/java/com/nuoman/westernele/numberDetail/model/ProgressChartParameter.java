package com.nuoman.westernele.numberDetail.model;

/**
 * 进程图表请求参数
 * Created by 杨小过 on 2016/11/24.
 */

public class ProgressChartParameter {
    private String orderId;
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
