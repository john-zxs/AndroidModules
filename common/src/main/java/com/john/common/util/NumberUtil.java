package com.john.common.util;

import java.math.BigDecimal;

/**
 * Copyright: 河北省通信建设有限公司网络分公司<p>
 * Author: John<p>
 * Description: <p>
 * Date: 2019/7/3 11:07<p>
 * Updater：<p>
 */
public class NumberUtil {
    //BigDecimal.ROUND_HALF_UP 四舍五入
    //BigDecimal.ROUND_FLOOR 不四舍五入

    /**
     * 保留scal位小数,进行四舍五入
     *
     * @param d    原数值
     * @param scal 要保留几位
     * @return
     */
    public static Double getDecimal(Double d, int scal) {
        return getDecimal(d, scal, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 保留两位小数,进行四舍五入
     *
     * @param d         原数值
     * @param scal      要保留几位
     * @param roundMode rounding mode to be used to round the result.
     * @return
     */
    public static Double getDecimal(Double d, int scal, int roundMode) {
        BigDecimal bd = new BigDecimal(d);
        Double tem = bd.setScale(scal, roundMode).doubleValue();
        return tem;
    }

}
