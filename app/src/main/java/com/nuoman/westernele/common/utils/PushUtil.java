package com.nuoman.westernele.common.utils;

import android.content.Context;
import android.util.Log;

import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.common.inter.ITagManager;
import com.umeng.message.tag.TagManager;

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

    public static void setTag(String tag) {
        PushAgent pushAgent = PushAgent.getInstance(AppConfig.getContext());
        pushAgent.getTagManager().add(new TagManager.TCallBack() {
            @Override
            public void onMessage(boolean b, ITagManager.Result result) {
                if (b) {
                    Log.d("push", "推送添加tag成功");
                } else {
                    Log.e("push", "推送添加tag失败");
                }
            }
        }, tag);
    }

}
