package com.john.common.util;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.john.common.R;

import androidx.annotation.NonNull;

/**
 * @author John
 * @Copyright 河北省通信建设有限公司网络分公司
 * @Description $desc$
 * @date 2019/3/14 9:33
 */
public class DialogUtil {
    /**
     * 简易dialog
     *
     * @param context
     * @param content
     * @param callback
     * @return
     */
    public static MaterialDialog getConfirmDialog(Context context, String content, MaterialDialog.SingleButtonCallback callback) {
        return new MaterialDialog.Builder(context)
                .title(context.getResources().getString(R.string.common_tip))
                .content(content)
                .positiveText(context.getResources().getString(R.string.common_confirm))
                .onPositive(callback)
                .cancelable(false)
                .build();
    }

    /**
     * 简易dialog
     *
     * @param context
     * @param content
     * @param callback
     * @return
     */
    public static MaterialDialog getSimpleDialog(Context context, String content, MaterialDialog.SingleButtonCallback callback) {
        return new MaterialDialog.Builder(context)
                .title(context.getResources().getString(R.string.common_tip))
                .content(content)
                .positiveText(context.getResources().getString(R.string.common_confirm))
                .onPositive(callback)
                .negativeText(context.getResources().getString(R.string.common_cancle))
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .build();
    }
}
