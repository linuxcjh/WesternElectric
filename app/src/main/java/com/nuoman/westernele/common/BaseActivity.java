package com.nuoman.westernele.common;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.WindowManager;

import com.google.gson.reflect.TypeToken;
import com.nuoman.NuoManApplication;
import com.nuoman.westernele.api.NuoManService;
import com.nuoman.westernele.common.utils.AppConfig;
import com.nuoman.westernele.common.utils.AppTools;
import com.nuoman.westernele.login.LoginActivity;
import com.nuoman.westernele.login.model.LoginParameter;
import com.nuoman.westernele.login.model.User;
import com.nuoman.westernelectric.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * AUTHOR: Alex
 * DATE: 21/10/2015 18:55
 */
public class BaseActivity extends FragmentActivity implements ICommonHomeAction {

    public SystemBarTintManager tintManager;
    public boolean isActive = true;//是否处于前台运行
    public static List<Activity> activityList = new ArrayList<>();

    public CommonHomePresenter commonPresenter = new CommonHomePresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityList.add(0, this);
        ((NuoManApplication) getApplication()).getRecordActivity().add(0, this);
    }

    public void initWindow() {
        //设置为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintColor(ContextCompat.getColor(this, R.color.colorBlue));
        tintManager.setStatusBarTintEnabled(true);
//        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (AppTools.isAppOnForeground(BaseActivity.this)) {
            isActive = true;

        } else {
            isActive = false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isActive) {
            isActive = true;
            loginInvoke(AppConfig.getStringConfig("userName", ""), AppConfig.getStringConfig("password", ""));

        }
    }

    /**
     * 验证登陆接口
     *
     * @param userName
     * @param passWord
     */
    public void loginInvoke(String userName, String passWord) {

        commonPresenter.isShowProgressDialog = false;
        LoginParameter loginParameter = new LoginParameter();
        loginParameter.setUserName(userName);
        loginParameter.setPassword(passWord);
        loginParameter.setPhoneType("1");
        commonPresenter.invokeInterfaceObtainData(true, "appUserCtrl", NuoManService.LOGIN,
                loginParameter, new TypeToken<User>() {
                });
    }

    @Override
    public void obtainDataHome(Object data, String methodIndex, int status, Map<String, String> parameterMap) {
        switch (methodIndex) {
            case NuoManService.LOGIN:
                if (status == 0) {
                    AppConfig.setBooleanConfig(NuoManConstant.IS_FIRST, false);
                    AppConfig.setBooleanConfig(NuoManConstant.IS_LOGIN, false);
                    AppConfig.setStringConfig("userName", "");
                    AppConfig.setStringConfig("password", "");
                    startActivity(new Intent(this, LoginActivity.class));

                    List<Activity> activities = ((NuoManApplication) getApplication()).getRecordActivity();
                    for (Activity aty : activities) {
                        aty.finish();
                    }
                }
                break;
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
    protected void onDestroy() {
        super.onDestroy();
        ((NuoManApplication) getApplication()).getRecordActivity().remove(this);
    }


}
