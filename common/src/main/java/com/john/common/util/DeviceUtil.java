package com.john.common.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

/**
 * Copyright: 河北省通信建设有限公司网络分公司<p>
 * Author: John<p>
 * Description: 设备信息工具类<p>
 * Date: 2020/2/10 15:53<p>
 * Updater：<p>
 */
public class DeviceUtil {
    /**
     * 设备简易信息
     * @param context
     * @param separator 分隔符
     * @return
     */
    public static String simplePhoneInfo(Context context, String separator) {
        String phoneInfo = "";
        if (separator == null || separator.isEmpty()) {
            separator = "&";
        }
        PackageManager pm = context.getPackageManager();
        PackageInfo pi = null;
        try {
            pi = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
            //app版本
            phoneInfo = "App_Version=" + pi.versionName + "_" + pi.versionCode + separator;
            //Android版本号
            phoneInfo = phoneInfo + "OS_Version=" + Build.VERSION.RELEASE + "_" + Build.VERSION.SDK_INT + separator;
            //手机制造商
            phoneInfo = phoneInfo + "Vendor=" + Build.MANUFACTURER + separator;
            //手机型号
            phoneInfo = phoneInfo + "Model=" + Build.MODEL + separator;
            //CPU架构
            phoneInfo = phoneInfo + "CPU_ABI=" + Build.CPU_ABI + separator;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return phoneInfo;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
