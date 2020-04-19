package com.john.network.http.Apis;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Url;

/**
 * Copyright: 河北省通信建设有限公司网络分公司<p>
 * Author: John<p>
 * Description: <p>
 * Date: 2020/2/11 11:37<p>
 * Updater：<p>
 */
public interface Uploader {
    String path="";
    @Multipart
    @POST("msg/upload-file-test2")
    Observable<String> sendMsgfile(@Part("file") RequestBody body, @PartMap Map<String, Object> map);

    @Multipart
        //    @POST("msg/upload-file-test2")
    Observable<String> uploadImg(@Url String url, @Part("file") RequestBody body, @PartMap Map<String, Object> map);
}
