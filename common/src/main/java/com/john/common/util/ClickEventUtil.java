package com.john.common.util;

import android.util.Log;

/**
 * @author John
 * @Copyright 河北省通信建设有限公司网络分公司
 * @Description 点击事件工具类
 * @date 2019/4/2 16:03
 */
public class ClickEventUtil {
    private static long lastClickTime = 0;//上次点击的系统时间
    private static long interval = 500;//快速点击  间隔  阈值(单位毫秒)

    /**
     * 是否为快速点击,当前系统时间
     *
     * @return
     */
    public static boolean isFastClick() {
        return isFastClick(System.currentTimeMillis());
    }

    /**
     * 是否为快速点击
     *
     * @param curSysTime
     * @return
     */
    public static boolean isFastClick(long curSysTime) {
        boolean isFast = true;
        if (curSysTime - lastClickTime > interval) {
            isFast = false;
        }
        Log.i("xxx", "isFastClick: lastClickTime=" + lastClickTime + "              curSysTime=" + curSysTime);
        lastClickTime = curSysTime;
        return isFast;
    }
}
