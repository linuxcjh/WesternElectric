package com.nuoman.westernele.api;

/**
 * AUTHOR: Alex
 * DATE: 17/11/2015 21:34
 */
public interface NuoManService {


    /*
    登陆接口
     */
    String LOGIN = "Login";

    /*
    首页数据接口
     */
    String GETMAINPAGEINFO = "GetMainPageInfo";

    /*
    账款信息
     */
    String BILL_INFORMATION = "GetAccountInfoByPage";

    /*
    根据工号查询项目
     */
    String NUMBER_QUERY = "GetOrderInfoListByOrderNo";

    /*
    根据工号查询出基本信息
     */
    String NUMBER_BASIC_INFORMATION = "GetOrderBaseInfoByOrderId";
    /*
    预警信息
     */
    String WARNING_CENTER = "GetAlertInfoByPage";

}
