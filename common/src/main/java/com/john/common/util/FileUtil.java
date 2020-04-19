package com.john.common.util;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import java.io.File;

import androidx.core.content.FileProvider;

/**
 * @author John
 * @Copyright 河北省通信建设有限公司网络分公司
 * @Description 文件工具类
 * @date 2019/2/12 9:34
 */
public class FileUtil {
    public static File makeFile(String filePath, String fileName) {
        makePath(filePath);
        return makeFile(filePath + File.separator + fileName);
    }

    public static void makePath(String path) {
        File file = null;
        try {
            file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
        } catch (Exception e) {
            Log.i("error:", e + "");
        }
    }

    public static File makeFile(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("error:", e + "");
        }
        return file;
    }

    /**
     * 获取provider
     *
     * @param context
     * @param authority 签名
     * @param file
     * @return
     */
    public static Uri getProvider(Context context, String authority, File file) {
        Uri fileUri = null;
        if (Build.VERSION.SDK_INT >= 24) {
            fileUri = FileProvider.getUriForFile(context, authority, file);
        } else {
            fileUri = Uri.fromFile(file);
        }
        return fileUri;
    }

    /**
     * 获取provider
     *
     * @param context
     * @param file
     * @return
     */
    public static Uri getProvider(Context context, File file) {
        return getProvider(context, context.getPackageName() + ".fileprovider", file);
    }
}
