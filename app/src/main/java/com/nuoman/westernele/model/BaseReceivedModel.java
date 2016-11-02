package com.nuoman.westernele.model;

/**
 * AUTHOR: Alex
 * DATE: 9/8/2016 10:38
 */
public class BaseReceivedModel {

    private String result;//	1成功0失败
    private String errorMsg;//	执行结果内容


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}

