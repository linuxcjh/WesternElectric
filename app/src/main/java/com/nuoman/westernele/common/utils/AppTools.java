package com.nuoman.westernele.common.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.text.InputType;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.bumptech.glide.Glide;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.nuoman.tabletattendance.R;
import com.nuoman.westernele.common.NuoManConstant;
import com.nuoman.westernele.login.model.User;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

/**
 * AUTHOR: Alex
 * DATE: 12/11/2015 09:47
 */
public class AppTools {


    /**
     * Bean Convert Map
     *
     * @param targetBean
     * @return
     */
    public static Map<String, String> toMap(Object targetBean) {

        Map<String, String> result = new IdentityHashMap<String, String>();
//        result.putAll(AppTools.toMap());
        Method[] methods = targetBean.getClass().getDeclaredMethods();

        for (Method method : methods) {
            try {
                if (method.getName().startsWith("get")) {
                    String field = method.getName();
                    field = field.substring(field.indexOf("get") + 3);
                    field = field.toLowerCase().charAt(0) + field.substring(1);

                    Object value = method.invoke(targetBean, (Object[]) null);
                    result.put(field, null == value ? "" : value.toString());
                }
            } catch (Exception e) {
            }
        }


        return result;
    }

    /**
     * Bean Convert Map
     *
     * @return
     */
//    public static Map<String, String> toMap() {
//        BaseTransModel commonBean = new BaseTransModel();
//        Map<String, String> result = new IdentityHashMap<>();
//        Method[] commonMethods = commonBean.getClass().getDeclaredMethods();
//
//        for (Method method : commonMethods) {
//            try {
//                if (method.getName().startsWith("get")) {
//                    String field = method.getName();
//                    field = field.substring(field.indexOf("get") + 3);
//                    field = field.toLowerCase().charAt(0) + field.substring(1);
//
//                    Object value = method.invoke(commonBean, (Object[]) null);
//                    result.put(field, null == value ? "" : value.toString());
//                }
//            } catch (Exception e) {
//            }
//        }
//
//        return result;
//    }

    /**
     * 方法名:获取文件存取路径
     * <p>
     * 功能说明：获取文件存取路径
     * </p>
     *
     * @return
     */
    public static String getFileSavePath(Context context) {
        if (AppTools.getSDPath().equals("")) {
            return context.getFilesDir().getPath();
        }
        File file = new File(AppTools.getSDPath() + "/pictures");
        if (!file.exists()) {
            if (file.mkdirs()) {
                return file.getPath();
            }
            return "";
        }
        return file.getPath();
    }

    /**
     * 方法名: 判断SD卡是否存在
     * <p>
     * 功能说明： 判断SD卡是否存在, 如果存在返回SD卡路径 , 如果不存在返回路径为空
     * </p>
     *
     * @return
     */
    public static String getSDPath() {
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
        if (sdCardExist) {
            File sdDir = Environment.getExternalStorageDirectory();
            return sdDir.toString();
        }
        return "";
    }

    /**
     * 禁止Edittext弹出软件盘，光标依然正常显示。
     */
    public static void disableShowSoftInput(EditText editTv) {
        if (Build.VERSION.SDK_INT <= 10) {
            editTv.setInputType(InputType.TYPE_NULL);
        } else {
            Class<EditText> cls = EditText.class;
            Method method;
            try {
                method = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
                method.setAccessible(true);
                method.invoke(editTv, false);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                method = cls.getMethod("setSoftInputShownOnFocus", boolean.class);
                method.setAccessible(true);
                method.invoke(editTv, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 从assets 文件夹中读取图片
     */
    public static Drawable loadImageFromAsserts(final Context ctx, String fileName) {
        try {
            InputStream is = ctx.getResources().getAssets().open(fileName);
            return Drawable.createFromStream(is, null);
        } catch (IOException e) {
            if (e != null) {
                e.printStackTrace();
            }
        } catch (OutOfMemoryError e) {
            if (e != null) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            if (e != null) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 方法名: getVersionCode
     * <p>
     * 功能说明： 返回当前应用的版本号
     * </p>
     *
     * @return
     */
    public static int getVersionCode() {
        int verCode = 0;
        try {
            verCode = AppConfig.getContext().getPackageManager()
                    .getPackageInfo(AppConfig.getContext().getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
        }
        return verCode;
    }

    /*
    删除目录下的所有文件
     */

    public static void deleteAllFiles(File root) {
        File files[] = root.listFiles();
        if (files != null)
            for (File f : files) {
                if (f.isDirectory()) { // 判断是否为文件夹
                    deleteAllFiles(f);
                    try {
                        f.delete();
                    } catch (Exception e) {
                    }
                } else {
                    if (f.exists()) { // 判断是否存在
                        deleteAllFiles(f);
                        try {
                            f.delete();
                        } catch (Exception e) {
                        }
                    }
                }
            }
    }


    /**
     * 获取时间
     *
     * @param textView
     */
    public static void obtainTime(final Activity activity, final TextView textView, final int index) {
        final Calendar calendar = Calendar.getInstance();
        TimePickerDialog timePickerDialog = new TimePickerDialog(activity, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                textView.setText(inspectTime(hourOfDay, minute));
                if (index == 1) {
                    AppConfig.setStringConfig(NuoManConstant.DOWN_SCREEN_LIGHT, textView.getText().toString());
                } else if (index == 2) {
                    AppConfig.setStringConfig(NuoManConstant.REBACK_SCREEN_LIGHT, textView.getText().toString());
                }
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false);
        timePickerDialog.show();
    }


    /**
     * 格式化事件
     *
     * @param hourOfDay
     * @param minute
     * @return
     */
    public static String inspectTime(int hourOfDay, int minute) {

        String tempHour = String.valueOf((hourOfDay));
        String tempMin = String.valueOf((minute));

        if ((hourOfDay) < 10) {
            tempHour = "0" + String.valueOf(hourOfDay);
        }
        if (minute < 10) {
            tempMin = "0" + String.valueOf((minute));
        }
        String timeStr = tempHour + ":" + tempMin;

        return timeStr;

    }


    /**
     * 获取ACACHE实力
     *
     * @return
     */
    public static ACache getAcache() {

        return ACache.get(AppConfig.getContext());
    }

    /**
     * 存数据
     *
     * @param key
     * @param value
     */
    public static void acachePut(String key, String value) {
        getAcache().put(key, value);
    }

    /**
     * 获取String类型数据
     *
     * @param key
     * @return
     */
    public static String getAcacheData(String key) {

        String result = getAcache().getAsString(key);
        if (TextUtils.isEmpty(result)) {
            result = "";
        }
        return result;
    }


    /**
     * Uses Root access to enable and disable SystemUI.
     *
     * @param enabled Decide whether to enable or disable.
     */
    public static void setSystemUIEnabled(boolean enabled) {
        try {
            Process p = Runtime.getRuntime().exec("su");
            DataOutputStream os = new DataOutputStream(p.getOutputStream());
            os.writeBytes("pm " + (enabled ? "enable" : "disable")
                    + " com.android.systemui\n");
            os.writeBytes("exit\n");
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断程序是否在前台运行
     *
     * @param context
     * @return
     */
    public static boolean isAppOnForeground(Activity context) {
        // Returns a list of application processes that are running on the
        // device

        ActivityManager activityManager = (ActivityManager) context.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = context.getApplicationContext().getPackageName();

        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        if (appProcesses == null) {
            return false;
        }

        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses)

        {
            // The name of the process that this object is associated with.
            if (appProcess.processName.equals(packageName)
                    && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }

        return false;
    }

    // wifi热点开关
    public static boolean setWifiApEnabled(boolean enabled) {
        WifiManager wifiManager = (WifiManager) AppConfig.getContext().getSystemService(Context.WIFI_SERVICE);

        if (enabled) { // disable WiFi in any case
            //wifi和热点不能同时打开，所以打开热点的时候需要关闭wifi
            wifiManager.setWifiEnabled(false);
        }
        try {
            //热点的配置类
            WifiConfiguration apConfig = new WifiConfiguration();
            //配置热点的名称(可以在名字后面加点随机数什么的)
            apConfig.SSID = "HeAnQuan-AP";
            //配置热点的密码
            apConfig.preSharedKey = "11111111";
            apConfig.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
            apConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
            apConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
            apConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
            apConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
            apConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
            apConfig.status = WifiConfiguration.Status.ENABLED;

            //通过反射调用设置热点
            Method method = wifiManager.getClass().getMethod(
                    "setWifiApEnabled", WifiConfiguration.class, Boolean.TYPE);
            //返回热点打开状态
            return (Boolean) method.invoke(wifiManager, apConfig, enabled);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 保存登录信息
     *
     * @param user 用户信息类
     */
    public static void saveUser(User user) {
        String userModelString = new GsonBuilder().create().toJson(user);
        AppConfig.setStringConfig("user", userModelString);
    }

    /**
     * 获取登录人对象
     *
     * @return 登录人实体类
     */
    public static User getUser() {
        User user = new User();
        String str = AppConfig.getStringConfig("user", null);
        if (!TextUtils.isEmpty(str)) {
            user = new GsonBuilder().create().fromJson(str, new TypeToken<User>() {
            }.getType());
        }
        return user;
    }
    /**
     * 异步加载圆形图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void setImageViewPicture(Context context, String url, ImageView imageView) {

        Glide.with(context).load(url).placeholder(R.drawable.defaultavatar).error(R.drawable.defaultavatar).transform(new GlideRoundTransform(context)).into(imageView);

    }

    /**
     * 异步加载方块图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void setImageViewClub(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).placeholder(R.drawable.photo_none).error(R.drawable.photo_none).into(imageView);
    }


}
