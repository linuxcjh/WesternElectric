package com.nuoman.tabletattendance.common.utils;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 一些小的工具方法
 * Created by 杨小过 on 2016/5/18.
 */
public class BaseUtil {


    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static String YYYY_MM_DD_HH_MM = "yyyy-MM-dd ";

    public static String HH_MM_SS = "HH:mm:ss";

    public static String HH_MM = "HH:mm";

    /**
     * 获取蓝牙地址
     *
     * @return 返回本机蓝牙地址
     */
    public static String getBluetoothMac() {
        BluetoothManager bluetoothManager = (BluetoothManager) AppConfig.getContext().getSystemService(Context.BLUETOOTH_SERVICE);
        BluetoothAdapter bluetoothAdapter = bluetoothManager.getAdapter();
        if (bluetoothAdapter == null)
            return "无蓝牙模块";
        else
            return bluetoothAdapter.getAddress().replace(":", "");
    }

    /**
     * 格式化时间
     *
     * @param patter 格式化时间的格式
     * @return 格式化收的时间
     */
    public static String getTime(String patter) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patter);
        return simpleDateFormat.format(new Date());
    }

    public static String getTime(String patter, long deltaTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patter);
        return simpleDateFormat.format(new Date(System.currentTimeMillis() + deltaTime));
    }

    /**
     * 获取星期数
     *
     * @return 返回星期几
     */
    public static String getDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        String dow = String.valueOf(calendar.get(Calendar.DAY_OF_WEEK));
        if ("1".equals(dow)) {
            dow = "天";
        } else if ("2".equals(dow)) {
            dow = "一";
        } else if ("3".equals(dow)) {
            dow = "二";
        } else if ("4".equals(dow)) {
            dow = "三";
        } else if ("5".equals(dow)) {
            dow = "四";
        } else if ("6".equals(dow)) {
            dow = "五";
        } else if ("7".equals(dow)) {
            dow = "六";
        }
        return "星期" + dow;
    }

    /**
     * 将byte数组转换成字符串
     *
     * @param bytes 要转换的byte数组
     * @return 转换收的字符串
     */
    public static String bytes2hex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        String temp;
        for (byte b : bytes) {
            temp = Integer.toHexString(b & 0XFF);
            if (temp.length() == 1) {
                sb.append("0");
                sb.append(temp);
            } else {
                sb.append(temp);
            }
        }
        return sb.toString();
    }


}
