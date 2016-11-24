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
    /*
    信息发布列表
     */
    String RELEASE_INFORMATION_LIST = "GetProjectListForCheck";

    /*
    信息发布列表详情
     */
    String RELEASE_INFORMATION_DETAIL = "GetNodeInfoForCheck";

    /*
    审核请求
     */
    String RELEASE_INFORMATION_CHECK = "CheckNode";

    /*
    上传deviceToken
     */
    String UPLOAD_DEVICE_TOKEN = "SaveUmengPushRelation";

    /*
    获取进度图表信息
     */
    String PROGRESS_CHART = "GetOrderProcessByOrderId";

}
