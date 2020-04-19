package com.john.network.http;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;

/**
 * Copyright: 河北省通信建设有限公司网络分公司<p>
 * Author: John<p>
 * Description: <p>
 * Date: 2020/2/11 9:29<p>
 * Updater：<p>
 */
public class Api {
    private static Map<String, Object> services = new HashMap<>();

    public static <T> T service(Class<T> clazz) {
        if (services == null) {
            services = new HashMap<>();
        }
        Object service = services.get(clazz.getSimpleName());
        if (service == null) {
            String url="";
            Interceptor[] interceptors=null;
            Field field = null;
            try {
                field = clazz.getField("BASE_URL");
                url = (String) field.get(clazz);
                field = clazz.getField("BASE_URL");
                interceptors = (Interceptor[]) field.get(clazz);
            } catch (Exception e) {
                e.printStackTrace();
            }
            service = Http.createService(url, clazz, interceptors);
            services.put(clazz.getSimpleName(), service);
        }
        if (service == null) {
            return null;
        }
        return (T) service;

    }
}
