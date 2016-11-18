package com.nuoman.westernele.login.model;

/**
 * 登录参数实体类
 * Created by 杨小过 on 2016/11/18.
 */

public class LoginParameter {

    /*
    userName:"18300000001",
    password:"ZTEwYWRjMzk0OWJhNTlhYmJlNTZlMDU3ZjIwZjg4M2U=",
    phoneType:"0" 0代表IOS，1代表Android
     */

    private String userName;
    private String password;
    private String phoneType;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
