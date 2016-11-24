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

    /**
     * 联系人
     */
    String GETCONTACTSLIST = "GetContactsList";

    /**
     * 7牛token
     */
    String GETTOKEN = "GetToken";

    /**
     * 获取账户信息
     */
    String GETACCOUNTINFODETAIL = "GetAccountInfoDetail";

    /**
     * 保存用户头像
     */
    String SAVEUSERHEADPIC="SaveUserHeadPic";

    /**
     * 保存账户信息
     */
    String SAVEACCOUNTINFODETAIL = "SaveAccountInfoDetail";
    /*
    上传deviceToken
     */
    String UPLOAD_DEVICE_TOKEN = "SaveUmengPushRelation";

    /*
    获取进度图表信息
     */
    String PROGRESS_CHART = "GetOrderProcessByOrderId";

    /**
     * 项目管理
     */
    String GETPROJECTLISTCONDITION="GetProjectListCondition";

    /**
     * 项目员工列表
     */
    String GETORDERLISTBYPROJECTID="GetOrderListByProjectId";

    /**
     * 项目查询详情
     */
    String GETNODELISTBYORDERID="GetNodeListByOrderId";

    /**
     * 获取筛选条件
     */
    String GETPROJECTCONDITIONS="GetProjectConditions";

}
