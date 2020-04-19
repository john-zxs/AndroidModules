package com.john.common.util;

import androidx.annotation.Nullable;

/**
 * Copyright：河北省通信建设有限公司网络分公司<p>
 * Author：John<p>
 * Description：字符串工具<p>
 * Date：2020/2/10 16:10<p>
 * Updater：
 */
public class StringUtil {
    public final static String DOUBLE_POINTS_2 = "%4.2f";

    /**
     * 向左补全padStr到length
     *
     * @param source
     * @param length
     * @param padStr
     * @return
     */
    public static String leftPad(String source, int length, String padStr) {
        return String.format("%" + length + "s", source).replace(" ", padStr);
    }

    /**
     * 向右补全padStr到length
     *
     * @param source
     * @param length
     * @param padStr
     * @return
     */
    public static String rightPad(String source, int length, String padStr) {
        return String.format("%-" + length + "s", source).replace(" ", padStr);
    }

    /**
     * 两位小数的double数字
     *
     * @param data
     * @return
     */
    public static String doublePoints2(String data) {
        if (data == null || data.isEmpty()) {
            return "0.00";
        }
        return String.format(DOUBLE_POINTS_2, Double.valueOf(data));
    }

    /**
     * @return
     */
    public static String returnStr(Object str) {
        return str == null ? "" : str.toString();
    }

    public static boolean isEmpty(@Nullable CharSequence str) {
        return str == null || str.length() == 0;
    }
}
