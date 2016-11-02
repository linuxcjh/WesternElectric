package com.nuoman.tabletattendance;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.nuoman.tabletattendance.common.BaseActivity;
import com.nuoman.tabletattendance.common.NuoManConstant;
import com.nuoman.tabletattendance.common.utils.AppConfig;

/**
 * 跳转页面
 * Created by 杨小过 on 2016/5/17.
 */
public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_layout);
        initData();
    }

    private void initData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkUserName();
            }
        }, 1 * 1000);
    }


    private void checkUserName() {

        if (!AppConfig.getBooleanConfig(NuoManConstant.IS_LOGIN, false)) {
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            startActivity(new Intent(this, MainActivity.class));
        }
        this.finish();
    }
}
