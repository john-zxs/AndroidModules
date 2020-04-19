package com.john.common.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author John
 * @Copyright 河北省通信建设有限公司网络分公司
 * @Description $desc$
 * @date 2019/4/2 18:35
 */
public class NetUtil {
    /**
     * 判断是否连接网络
     * @param context
     * @return
     */
    public static boolean isNetConnected(Context context) {
        boolean isAvailable = false ;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if(networkInfo!=null && networkInfo.isAvailable()){
            isAvailable = true;
        }
        return isAvailable;
    }
}
