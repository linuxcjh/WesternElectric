package com.nuoman;

import android.app.Application;

import com.nuoman.westernele.common.utils.AppConfig;

/**
 * Created by chen on 21/10/2015.
 */
public class NuoManApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        AppConfig.setContext(this);

    }


}
