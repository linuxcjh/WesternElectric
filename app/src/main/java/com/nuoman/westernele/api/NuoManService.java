package com.nuoman.westernele.api;

/**
 * AUTHOR: Alex
 * DATE: 17/11/2015 21:34
 */
public interface NuoManService {



    //登陆
    String LOGIN="Login";

    //7牛token
    String GETTOKEN="GetToken";

    //获取天气
    String GETWEATHERFORONEDAY="GetWeatherForOneDay";

    //上传打卡信息
    String WRITEATTLOG = "WriteAttLog";

    //获取未读消息
    String GETUNREADMSG="GetUnReadMsg";

    //获取家长列表
    String GETPARENTSBYCARDNO="GetParentsByCardNo";

    //老师获取班级列表
    String GETCLASSESBYTEACHERID="GetClassesByTeacherId";

    //上传作业
    String SAVEHOMEWORK= "SaveHomework";

    //发作业鉴权
    String  GETTEACHERID="GetTeacherId";

    //版本更新
    String GETUPDATEINFO="GetUpdateInfo";

    //上传音频文件
    String SAVEVOICEMSG="SaveVoiceMsg";

    //上传及其位置
    String SAVEDVCLOCATION="SaveDvcLocation";

    //获取录音列表
    String GETAUDIOLISTBYUSERID="GetAudioListByUserId";

    //上传设备信息
    String DEVICECOMM="DeviceComm";

}
