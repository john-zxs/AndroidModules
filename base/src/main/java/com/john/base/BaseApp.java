package com.john.base;

import android.app.Application;
import android.content.Context;

public class BaseApp extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        mContext = this;
        super.onCreate();
    }

    public static Context context() {
        return mContext;
    }
}
