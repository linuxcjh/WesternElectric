package com.nuoman;

import android.app.Application;

import com.nuoman.westernele.common.utils.AppConfig;
import com.nuoman.westernele.common.utils.PushUtil;

/**
 * 应用application
 * Created by chen on 21/10/2015.
 */
public class NuoManApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppConfig.setContext(this);
        PushUtil.registerPush(getApplicationContext());
        PushUtil.setTag("xd");
    }


}
