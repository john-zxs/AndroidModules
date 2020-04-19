package com.john.base;

import android.content.Context;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author John
 * @Copyright 河北省通信建设有限公司网络分公司
 * @Description $desc$
 * @date 2019/2/12 9:04
 */
public class BaseProperties extends Properties {
    private static final String TAG = BaseProperties.class.getSimpleName();
    public static final String NTS = "://";//net type separator
    public static final String COLON = ":";//冒号
    public static final String SEPERATOR = "_";
    public static final String NET_TYPE = "NET_TYPE";
    public static final String IP = "IP";
    public static final String PORT = "PORT";
    public static final String API = "API";

    public BaseProperties() {
    }

    /**
     * 从 assets 加载property文件
     *
     * @param context
     * @param fileName
     */
    public void loadFromAssets(Context context, String fileName) {
        try {
            InputStream is = context.getAssets().open(fileName);
            this.load(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 从 本地存储 加载property文件
     *
     * @param filePath 文件路径
     */
    public void loadFromSDcard(String filePath) {
        try {
            InputStream is = new FileInputStream(filePath);
            this.load(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String property(String key) {
        return getProperty(key, "");
    }

}
