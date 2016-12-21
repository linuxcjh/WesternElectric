package com.nuoman.westernele.common.utils;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.nuoman.westernele.common.NuoManConstant;
import com.nuoman.westernele.common.SelectionDialogListAdapter;
import com.nuoman.westernele.components.SelectionDialog;
import com.nuoman.westernele.login.model.User;
import com.nuoman.westernele.mine.model.BaseDataModel;
import com.nuoman.westernelectric.R;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * AUTHOR: Alex
 * DATE: 12/11/2015 09:47
 */
public class AppTools {
    /**
     * 遍历参数
     *
     * @param map
     */
    public static void ergodicParameters(Map<String, String> map) {
        if (map != null) {
            Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, String> entry = entries.next();
                Log.i("NuoMan", "Key = " + entry.getKey() + ", Value = " + entry.getValue());
            }
        }
    }

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
     * 获取日期
     *
     * @param textView
     * @return
     */
    public static void obtainData(Activity activity, final TextView textView, final String content) {
        final DatePickerDialog datePickerDialog;
        final Calendar calendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                textView.setText(content + inspectDate(year, monthOfYear, dayOfMonth));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    /**
     * 转换日期格式
     *
     * @param year
     * @param monthOfYear
     * @param dayOfMonth
     * @return
     */
    public static String inspectDate(int year, int monthOfYear, int dayOfMonth) {

        String tempMonth = String.valueOf((monthOfYear + 1));
        String tempDay = String.valueOf((dayOfMonth));

        if ((monthOfYear + 1) < 10) {
            tempMonth = "0" + String.valueOf((monthOfYear + 1));
        }
        if (dayOfMonth < 10) {
            tempDay = "0" + String.valueOf((dayOfMonth));
        }
        String dateString = year + "-" + tempMonth + "-" + tempDay;

        return dateString;

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

    /**
     * 发短信
     *
     * @param phone
     */
    public static void sendSMS(Context context, String phone) {
        Uri smsToUri = Uri.parse("smsto:" + (TextUtils.isEmpty(phone) ? "" : phone));
        Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
        intent.putExtra("sms_body", "");
        context.startActivity(intent);
    }

    /**
     * 打电话
     *
     * @param context
     * @param phone
     */
    public static void callPhone(Context context, String phone) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + (TextUtils.isEmpty(phone) ? "" : phone)));
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        context.startActivity(intent);
    }

    /**
     * Toast
     *
     * @param contentStr
     */
    public static void getToast(String contentStr) {
        Toast.makeText(AppConfig.getContext(), contentStr, Toast.LENGTH_SHORT).show();

    }

    /**
     * 无水印相机
     *
     * @param activity fragment
     */
    public static void getCapturePathNoWater(Activity activity, Fragment fragment) {

        String path = getImageSavePath(activity) + "/"
                + DateUtil.getTime(DateUtil.yyyyMMddHHmmss) + ".jpg";
        AppConfig.setStringConfig("cameraPath", path);
        Intent cameraIntent = new Intent(activity, CameraNoMarkActivity.class);
        cameraIntent.putExtra("path", path);
        if (fragment != null) {
            fragment.startActivityForResult(cameraIntent,
                    NuoManConstant.CAMERA_REQUEST_CODE);
        } else {
            activity.startActivityForResult(cameraIntent,
                    NuoManConstant.CAMERA_REQUEST_CODE);

        }

    }


    /**
     * 方法名:获取图片文件存取路径
     * <p>
     * 功能说明：获取图片文件存取路径
     * </p>
     *
     * @return
     */
    public static String getImageSavePath(Activity activity) {
        if (AppTools.getSDPath().equals("")) {
            return activity.getFilesDir().getPath();
        }
        File file = new File(AppTools.getSDPath() + "/nuoman/profession/dcim");
        if (!file.exists()) {
            if (file.mkdirs()) {
                return file.getPath();
            }
            return "";
        }
        return file.getPath();
    }

    /**
     * 调用系统相册
     *
     * @param activity
     */
    public static void getSystemImage(Fragment activity) {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(intent, NuoManConstant.SELECT_PICTURE);

    }

    /**
     * 调用系统相册
     *
     * @param activity
     */
    public static void getSystemImage(Activity activity) {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(intent, NuoManConstant.SELECT_PICTURE);

    }

    /**
     * 调用拍照、相册
     *
     * @param activity
     * @param handler
     */

    public static void selectPhotoShow(Activity activity, Handler handler, int selectSign) {

        if (PermissionsChecker.getPermissionsChecker().lacksPermissions(ConstantPermission.PERMISSIONS_PICTURE)) {
            PermissionsChecker.getPermissionsChecker().startPermissionsActivity(activity, ConstantPermission.PERMISSIONS_PICTURE);
        } else {
            List<BaseDataModel> data = new ArrayList<>();
            BaseDataModel model = new BaseDataModel();
            model.setDictionaryName(activity.getString(R.string.camera_picture));
            BaseDataModel modelT = new BaseDataModel();
            modelT.setDictionaryName(activity.getString(R.string.photo_picture));

            data.add(model);
            data.add(modelT);
            selectDialog("请选择", activity, data, handler, selectSign);
        }

    }

    /**
     * 选择框
     *
     * @param activity
     * @param data
     * @param handler
     */
    public static void selectDialog(String title, Activity activity, List<BaseDataModel> data, Handler handler, int selectSign) {
        AppTools.selectHeightDialog(title, activity, data, handler, selectSign, 0);
    }


    /**
     * 动态设置高度的选择框
     *
     * @param activity
     * @param data
     * @param handler
     * @param selectSign
     */
    public static void selectHeightDialog(String title, Activity activity, List<BaseDataModel> data, Handler handler, int selectSign, int height) {
        SelectionDialogListAdapter adapter = new SelectionDialogListAdapter(activity, R.layout.selection_dialog_listview_item, data);
        SelectionDialog dialog = new SelectionDialog(activity, R.layout.selection_dialog_listview_layout, handler, selectSign, height);

        dialog.buildDialog().setAdapter(adapter).setTitle(title);
    }

    /**
     * 更具uri获取绝对路径
     *
     * @param context
     * @param uri
     * @return
     */
    public static String getAbsolutePath(Context context, Uri uri) {
        if (uri == null) {
            return null;
        }
        if (uri.toString().contains("file://")) {

            String path = uri.toString().replace("file://", "");

            return Uri.decode(path);
        }
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, proj, null,
                null, null);
        if (cursor == null) {
            return null;
        }
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


}
