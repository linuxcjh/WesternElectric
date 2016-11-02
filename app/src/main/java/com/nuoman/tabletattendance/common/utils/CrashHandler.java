package com.nuoman.tabletattendance.common.utils;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.nuoman.tabletattendance.SplashActivity;
import com.nuoman.tabletattendance.common.BaseActivity;
import com.nuoman.tabletattendance.common.NuoManConstant;

import java.lang.Thread.UncaughtExceptionHandler;

public class CrashHandler implements UncaughtExceptionHandler {

    private Context mContext;
    // CrashHandler实例
    private static CrashHandler INSTANCE = new CrashHandler();

    /**
     * 保证只有一个CrashHandler实例
     */
    private CrashHandler() {
    }

    /**
     * 获取CrashHandler实例 ,单例模式
     */
    public static CrashHandler getInstance() {
        return INSTANCE;
    }

    /**
     * 初始化
     *
     * @param context
     */
    public void init(Context context) {
        // 设置该CrashHandler为程序的默认处理器
        mContext = context;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {

        try {
            AppConfig.setIntConfig(NuoManConstant.DEVICE_STATUS, 3);
            AppConfig.setStringConfig(NuoManConstant.DEVICE_STATUS_DEC, "程序退出：" + ex.getMessage().substring(0, 253));
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(mContext.getApplicationContext(), SplashActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent restartIntent = PendingIntent.getActivity(
                mContext, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        //退出程序
        AlarmManager mgr = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000,
                restartIntent); // 1秒钟后重启应用


        for (Activity activity : BaseActivity.activityList) {
            activity.finish();
        }
        android.os.Process.killProcess(android.os.Process.myPid());

    }
}
