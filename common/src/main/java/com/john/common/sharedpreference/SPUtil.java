package com.john.common.sharedpreference;

import com.john.base.BaseSharedPreference;
import com.john.base.interfaces.ISP;

/**
 * SharedPreference工具类
 */
public class SPUtil {
    private static ISP instance;
    private static SPFileName fileName = SPFileName.USERINFO;

    /**
     * @param fileName
     */
    public void resetFile(SPFileName fileName) {
        instance = new BaseSharedPreference(fileName.fileName);
    }

    /**
     * @param name
     */
    public void resetFile(String name) {
        instance = new BaseSharedPreference(name);
    }

    public void save(String name, String value) {
        instance.save(name, value);
    }

    public String get(String name) {
        return instance.get(name);
    }

    private static ISP instance() {
        if (instance == null) {
            if (fileName == null) {
                fileName = SPFileName.USERINFO;
            }
            instance = new BaseSharedPreference(fileName.fileName);
        }
        return instance;
    }

}
