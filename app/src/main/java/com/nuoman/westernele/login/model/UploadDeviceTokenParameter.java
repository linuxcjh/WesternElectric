package com.nuoman.westernele.login.model;

/**
 * 上床deviceToken的请求参数
 * Created by 杨小过 on 2016/11/23.
 */

public class UploadDeviceTokenParameter {

    private String deviceToken;
    private String deviceType;//0:IOS,1:Android
    private String userId;

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
