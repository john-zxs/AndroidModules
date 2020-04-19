package com.john.network.http;


import com.john.network.http.cert.TrustAllManager;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author John
 * @Copyright 河北省通信建设有限公司网络分公司
 * @Description $desc$
 * @date 2019/2/26 9:46
 */
public class OKHttpOptionHelper {
    /**
     * 添加日志拦截
     *
     * @param builder
     */
    public static void addLogging(OkHttpClient.Builder builder) {
        //        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        //            @Override
        //            public void log(String message) {
        //
        //            }
        //        });
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        builder.addInterceptor(interceptor);
    }

    /**
     * 添加参数拦截器
     */
    public static void addBasicParamsInterceptor(OkHttpClient.Builder builder) {
        //        BasicParamsInterceptor basicParamsInterceptor = new BasicParamsInterceptor.Builder()
        //                .addHeaderParam("userName","")//添加公共参数
        //                .addHeaderParam("device","")
        //                .build();
        //
        //        builder.addInterceptor(basicParamsInterceptor);
    }

    /**
     * 默认信任所有证书
     *
     * @param builder
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    public static void addSSLSocketFactory(OkHttpClient.Builder builder) throws NoSuchAlgorithmException, KeyManagementException {
        // Create an ssl socket factory with our all-trusting manager
        final X509TrustManager trustAllManager = new TrustAllManager();
        final TrustManager[] trustAllCerts = new TrustManager[]{trustAllManager};
        final SSLContext sslContext = SSLContext.getInstance("TLS");
        //        final SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
        final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
        builder.sslSocketFactory(sslSocketFactory, trustAllManager);
    }

    //    public static SSLSocketFactory getSSLSocketFactoryFromAssets(String certPathName) {
    //
    //    }

    public static void addHostNameVerifier(OkHttpClient.Builder builder) {
        builder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
    }

}
