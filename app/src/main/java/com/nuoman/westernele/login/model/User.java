package com.nuoman.westernele.login.model;

/**
 * 用户信息实体类
 * Created by 杨小过 on 2016/11/18.
 */

public class User {

    /*
    "data":{
            "appVersion":"",
            "departmentName":"",
            "isAvailable":"1",
            "roleId":"1", 当为5时是客户，其他是内部人员
            "roleName":"管理员",
            "showUpdate":"",
            "userId":"2",
            "userName":"abcd"
            }
     */

    private String appVersion;
    private String departmentName;
    private String isAvailable;
    private String roleId;
    private String roleName;
    private String showUpdate;
    private String userId;
    private String userName;

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(String isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getShowUpdate() {
        return showUpdate;
    }

    public void setShowUpdate(String showUpdate) {
        this.showUpdate = showUpdate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
