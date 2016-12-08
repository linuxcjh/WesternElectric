package com.nuoman;

import android.app.Activity;
import android.app.Application;

import com.nuoman.westernele.common.utils.AppConfig;
import com.nuoman.westernele.common.utils.PushUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 应用application
 * Created by chen on 21/10/2015.
 */
public class NuoManApplication extends Application {


    public List<Activity> toHomeList = new ArrayList<>();

    public List<Activity> recordActivity = new ArrayList<>();

    public List<Activity> getRecordActivity() {
        return recordActivity;
    }

    public void setRecordActivity(List<Activity> recordActivity) {
        this.recordActivity = recordActivity;
    }

    public void addActivityList(Activity activity) {
        toHomeList.add(activity);
    }

    public void clearActivityToHome() {
        for (Activity a : toHomeList) {
            a.finish();
        }
        toHomeList.clear();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AppConfig.setContext(this);
        PushUtil.registerPush(getApplicationContext());
        PushUtil.setTag("xd");
    }


}
