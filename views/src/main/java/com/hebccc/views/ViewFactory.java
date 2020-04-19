package com.hebccc.views;

import android.content.Context;

import com.hebccc.views.selector.BottomSelector;

/**
 * Copyright: 河北省通信建设有限公司网络分公司<p>
 * Author: John<p>
 * Description: <p>
 * Date: 2020/4/3 16:30<p>
 * Updater：<p>
 */
public class ViewFactory {
    public static BottomSelector bottomSelector(Context context) {
        return new BottomSelector(context);
    }
}
