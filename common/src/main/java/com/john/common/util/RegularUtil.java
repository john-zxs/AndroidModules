package com.john.common.util;

import java.util.regex.Pattern;

/**
 * Copyright：河北省通信建设有限公司网络分公司<p>
 * Author：John<p>
 * Description：正则匹配工具<p>
 * Date：2020/2/10 16:09<p>
 * Updater：
 */
public class RegularUtil {
    //手机号正则判断,家庭电话(座机)
    private static final String PHONE_NUM = "^1(3[0-9]|4[579]|5[0-35-9]|6[6]|7[0135678]|8[0-9]|9[89])\\d{8}$";
    private static final String PHONE_NUM_HOME = "^(0[0-9]{2})\\d{8}$|^(0[0-9]{3}(\\d{7,8}))$";

    /**
     * 手机号正则
     *
     * @param str
     * @return
     */
    public static boolean isHomePhoneNum(String str) {
        if (str == null) return false;
        return Pattern.compile(PHONE_NUM_HOME).matcher(str).matches();
    }

    /**
     * 手机号正则
     *
     * @param str 只处理11位
     * @return
     */
    public static boolean isPhoneNum(String str) {
        if (str == null) return false;
        return Pattern.compile(PHONE_NUM).matcher(str).matches();
    }

    //    /**
    //     * 中文正则
    //     * @param string
    //     * @return
    //     */
    //    public static boolean isChinese(String string) {
    //
    //    }
}
