package com.john.common.util;

import android.os.Environment;
import android.text.TextUtils;

import com.john.base.BaseApp;

import java.io.File;

/**
 * Copyright：河北省通信建设有限公司网络分公司<p>
 * Author：John<p>
 * Description：文件路径工具类<p>
 * Date：2020/2/10 15:52<p>
 * Updater：
 */
public class FilePathUtil {

    /**
     * 目录结构
     * /sdcard/com.hebccc.oa/user/file|images|db     用户目录:文件,图片,数据
     * /sdcard/com.hebccc.oa/files                   公用目录:文件
     * /sdcard/com.hebccc.oa/videos                  公用目录:视频
     * /sdcard/com.hebccc.oa/images                  公用目录:图片
     * /sdcard/com.hebccc.oa/crashes                 公用目录:崩溃信息
     * /sdcard/com.hebccc.oa/db                      公用目录:数据库
     * /data/data/com.hebccc.oa/cache                应用目录:缓存
     * /data/data/com.hebccc.oa/files                应用目录:文件
     */
    private static final String BASE_PATH = Environment.getExternalStorageDirectory().getPath();
    private static final String CACHE_PATH = BaseApp.context().getCacheDir().getAbsolutePath();
    private static final String CACHE_FILE_PATH = BaseApp.context().getFilesDir().getAbsolutePath();
    private static String APP_PATH;
    private static String USER_PATH;

    public static String appPath() {
        if (TextUtils.isEmpty(APP_PATH)) {
            APP_PATH = BASE_PATH + File.separator + BaseApp.context().getPackageName();
        }
        return APP_PATH;
    }

    /**
     * username希望用md5加密后
     *
     * @param userName 用户
     * @param reset    重置用户路径(重新登录)
     * @return
     */
    public static String userPath(String userName, boolean reset) {
        if (TextUtils.isEmpty(USER_PATH)) {
            USER_PATH = userName;
        }
        return USER_PATH;
    }

    public static String filePath() {
        return appPath() + File.separator + "files";
    }

    public static String imagePath() {
        return appPath() + File.separator + "images";
    }

    public static String crashPath() {
        return appPath() + File.separator + "crashes";
    }

    public static String dbPath() {
        return appPath() + File.separator + "db";
    }

    public static String cachePath() {
        return CACHE_PATH;
    }

    public static String cacheFilePath() {
        return CACHE_FILE_PATH;
    }
}
