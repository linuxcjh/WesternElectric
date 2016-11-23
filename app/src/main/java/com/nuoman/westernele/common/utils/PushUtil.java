package com.nuoman.westernele.common.utils;

import android.content.Context;
import android.util.Log;

import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;

/**
 * 推送工具类
 * Created by 杨小过 on 2016/11/23.
 */

public class PushUtil {

    public static void registerPush(Context context) {
        PushAgent pushAgent = PushAgent.getInstance(context);
        pushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String s) {
                Log.d("push", s);
            }

            @Override
            public void onFailure(String s, String s1) {
                Log.e("push", s + ":" + s1);
            }
        });
    }

    public static String getDeviceToken() {
        PushAgent pushAgent = PushAgent.getInstance(AppConfig.getContext());
        return pushAgent.getRegistrationId();
    }

}
