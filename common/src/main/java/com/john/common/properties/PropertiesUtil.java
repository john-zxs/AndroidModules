package com.john.common.properties;


import com.john.base.BaseApp;
import com.john.base.BaseProperties;

/**
 * @author John
 * @Copyright 河北省通信建设有限公司网络分公司
 * @Description 读取assets 文件内容
 * @date 2019/2/12 9:03
 */
public class PropertiesUtil {
    private static final String propertyName = "settings.properties";
    //网络地址
    public static final String Base = "BASE";
    private static boolean mDebug;

    private static BaseProperties properties;

    private static BaseProperties instance(boolean debug) {
        mDebug = debug;
        return instance();
    }

    private static BaseProperties instance() {
        if (properties == null) {
            properties = new BaseProperties();
            properties.loadFromAssets(BaseApp.context(), propertyName);
        }
        return properties;
    }

    /**
     * 获取运行环境
     */
    public static String getEnviroment() {
        if (mDebug) {
            return "DEBUG";
        } else {
            return "RELEASE";
        }
    }

    /**
     * 获取 基础url
     *
     * @return
     */
    public String getBaseUrl() {
        return getUrl(Base);
    }

    public String getUrl(String address) {
        return getNetType() + BaseProperties.NTS + getIP(address) + BaseProperties.COLON + getPort(address) + getApi();
    }

    /**
     * 默认返回base的地址前缀
     *
     * @return
     */
    public String getNetType() {
        return instance().property(Base + BaseProperties.SEPERATOR + BaseProperties.NET_TYPE);
    }

    public String getNetType(String address) {
        return instance().property(address + BaseProperties.SEPERATOR + BaseProperties.NET_TYPE);
    }

    /**
     * 默认返回base的地址前缀
     *
     * @return
     */
    public String getIP() {
        return instance().property(Base + BaseProperties.SEPERATOR + BaseProperties.IP + BaseProperties.SEPERATOR + getEnviroment());
    }

    public String getIP(String address) {
        return instance().property(address + BaseProperties.SEPERATOR + BaseProperties.IP + BaseProperties.SEPERATOR + getEnviroment());
    }

    /**
     * 默认返回base的地址前缀
     *
     * @return
     */
    public String getIPPort() {
        return instance().property(Base + BaseProperties.SEPERATOR + BaseProperties.PORT + BaseProperties.SEPERATOR + getEnviroment());
    }

    public String getPort(String address) {
        return instance().property(address + BaseProperties.SEPERATOR + BaseProperties.PORT + BaseProperties.SEPERATOR + getEnviroment());
    }

    /**
     * 默认返回base的地址前缀
     *
     * @return
     */
    public String getApi() {
        return instance().property(Base + BaseProperties.SEPERATOR + BaseProperties.API);
    }

    public String getApi(String address) {
        return instance().property(address + BaseProperties.SEPERATOR + BaseProperties.API);
    }

}
