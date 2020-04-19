package com.john.common.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Copyright：河北省通信建设有限公司网络分公司<p>
 * Author：John<p>
 * Description：弹窗<p>
 * Date：2020/2/10 16:10<p>
 * Updater：
 */
public class ToastUtil {

    private static String oldMsg;
    private static long time;

    public static void showToast(Context context, String msg) {
        // 当显示的内容不一样时，即断定为不是同一个Toast
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        if (!msg.equals(oldMsg)) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            time = System.currentTimeMillis();
        } else {
            if (System.currentTimeMillis() - time > 2000) {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                time = System.currentTimeMillis();
            }
        }
        oldMsg = msg;
    }
}
