package com.john.common.util;

public class Const {
    public static final int TIME_OUT_CONNECT = 30;//秒
    public static final int TIME_OUT_READ = 30;//秒
    public static final int TIME_OUT_WRITE = 30;//秒
    //configurations
    public static boolean useH5Link = false;//是否使用H5页面

    //上传的文件的最大值
    public static final int URL_UPLOAD_FILE_MAX = 2 * 1024 * 1024;//
    //上传图片 地址
    public static final String URL_UPLOAD_PICTURS = "111";//

    //Notification
    public static final String NOTIFICATION_CHANNEL_ID = "notification_id";
    public static final String NOTIFICATION_NAME = "通知";
    public static final int NOTIFICATION_ID_NORMAL = 0x01;
    //考勤状态
    public static final int ATTENDANCE_STATE_SUCCESS = 1;
    public static final int ATTENDANCE_STATE_FAIL = 0;

    //SP
    public static final String SP_NAME = "oa";
    //照片数量
    public static final int MAX_IMAGE_COUNT = 6;

    //    //MediaType
    //    public static final MediaType MEDIATYPE_TEXT = MediaType.parse("text/plain;charset=UTF-8");
    //    public static final MediaType MEDIATYPE_JPG = MediaType.parse("image/jpeg");
    //    public static final MediaType MEDIATYPE_PNG = MediaType.parse("image/png");
    //    public static final MediaType MEDIATYPE_FILE = MediaType.parse("application/octet-stream");
    //    public static final MediaType MEDIATYPE_FILE1 = MediaType.parse("multipart/form-data;charset=UTF-8");

    //pagesize
    public static final int PAGE_SIZE = 10;

}
