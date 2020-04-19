package com.john.base;

import android.content.Context;
import android.content.SharedPreferences;

import com.john.base.interfaces.ISP;

/**
 * 至少使用过一次file方法才能存储数据
 */
public class BaseSharedPreference implements ISP {
    private SharedPreferences instance;
    protected String fileName = "";//名字由子类填写

    /**
     * @param name Desired preferences file.
     */
    public BaseSharedPreference(String name) {
        file(name);
    }

    /**
     * @param name Desired preferences file.
     */
    @Override
    public ISP file(String name) {
        if (name != null && !fileName.equals(name)) fileName = name;
        isInstanceNull();
        return this;
    }

    @Override
    public void save(String name, String value) {
        if (!isInstanceNull()) {
            instance.edit().putString(name, value);
        }
    }

    @Override
    public String get(String name) {
        if (isInstanceNull()) {
            return "";
        } else {
            return instance.getString(name, "");
        }
    }

    private boolean isInstanceNull() {
        if (instance == null && !fileName.isEmpty()) {
            instance = BaseApp.context().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        }
        return instance == null;
    }
}
