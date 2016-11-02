package com.nuoman.westernele.model;

import java.io.Serializable;

/**
 * AUTHOR: Alex
 * DATE: 9/8/2016 10:38
 */
public class BaseTransModel implements Serializable {

    //    {"machineId":"1","cardNo":"0008124733","attDate":"2016-08-16 11:25:02","attPicUrl":"aaaaaaa.jpg"}
    private String tel;
    private String machineNo;


    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMachineNo() {
        return machineNo;
    }

    public void setMachineNo(String machineNo) {
        this.machineNo = machineNo;
    }
}

