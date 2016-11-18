package com.nuoman.westernele.login.model;

/**
 * AUTHOR: Alex
 * DATE: 2/11/2016 15:18
 */

public class LoginReturn {

    /*
    {
        "data":{
            "appVersion":"",
            "departmentName":"",
            "isAvailable":"1",
            "roleId":"1",
            "roleName":"管理员",
            "showUpdate":"",
            "userId":"2",
            "userName":"abcd"
            },
         "errorMsg":"",
         "result":"1"
      }

     */

    private String errorMsg;
    private String result;
    private User data;

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
