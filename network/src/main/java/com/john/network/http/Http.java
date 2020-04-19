package com.john.network.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.john.network.BuildConfig;
import com.john.network.util.Const;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Http {

    /**
     * 创建接口服务
     *
     * @param url   指定链接
     * @param clazz 接口对象
     * @return
     */
    public static <T> T createService(String url, Class<T> clazz) {
        return createService(url, clazz, null);
    }

    /**
     * 创建接口服务
     *
     * @param url          指定链接
     * @param clazz        接口对象
     * @param interceptors 拦截器
     * @return
     */
    public static <T> T createService(String url, Class<T> clazz, Interceptor... interceptors) {
        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .retryOnConnectionFailure(true)
                    .connectTimeout(Const.TIME_OUT_CONNECT, TimeUnit.SECONDS)
                    .writeTimeout(Const.TIME_OUT_READ, TimeUnit.SECONDS)
                    .readTimeout(Const.TIME_OUT_WRITE, TimeUnit.SECONDS);

            if (BuildConfig.DEBUG) {
                //                                Logger.addLogAdapter(new AndroidLogAdapter());
                //                                LogInterceptor interceptor = new LogInterceptor();
                OKHttpOptionHelper.addLogging(builder);
            }
            OKHttpOptionHelper.addSSLSocketFactory(builder);
            OKHttpOptionHelper.addSSLSocketFactory(builder);
            OKHttpOptionHelper.addHostNameVerifier(builder);

            if (interceptors != null) {
                for (Interceptor interceptor : interceptors) {
                    builder.addInterceptor(interceptor);
                }
            }
            OkHttpClient httpClient = builder.build();

            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(httpClient)
                    .build();
            return retrofit.create(clazz);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
    
