package com.nuoman.westernele.common;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.nuoman.westernele.common.utils.AppConfig;
import com.nuoman.westernele.common.utils.AppTools;

import java.util.ArrayList;
import java.util.List;

/**
 * AUTHOR: Alex
 * DATE: 21/10/2015 18:55
 */
public class BaseActivity extends FragmentActivity {

    public static List<Activity> activityList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//去掉Activity上面的状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//去掉虚拟按键全屏显示
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        activityList.add(0, this);
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (AppTools.isAppOnForeground(BaseActivity.this)) {
            AppConfig.setIntConfig(NuoManConstant.DEVICE_STATUS, 3);
            AppConfig.setStringConfig(NuoManConstant.DEVICE_STATUS_DEC, "程序恢复前台运行");
        } else {
            AppConfig.setIntConfig(NuoManConstant.DEVICE_STATUS, 1);
            AppConfig.setStringConfig(NuoManConstant.DEVICE_STATUS_DEC, "程序后台运行");

        }
    }


    /**
     * app 字体不受系统字体大小影响
     *
     * @return
     */
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    @Override
    protected void onResume() {
        super.onResume();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityList.remove(this);
    }


}
