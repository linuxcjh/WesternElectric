package com.nuoman.westernele.billInformation.model;

/**
 * 账款信息类
 * Created by 杨小过 on 2016/11/18.
 */

public class BillInformation {
    /*
    "data":[
        {
            "accountId":"1",
            "dataString":"您有一笔测试测试项目的应收账款",
            "fDate":"2016-11-15 17:50:00",
            "money":"账款金额：50000元"
        }
    ],
     */

    private String accountId;
    private String dataString;
    private String fDate;
    private String money;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getDataString() {
        return dataString;
    }

    public void setDataString(String dataString) {
        this.dataString = dataString;
    }

    public String getfDate() {
        return fDate;
    }

    public void setfDate(String fDate) {
        this.fDate = fDate;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
