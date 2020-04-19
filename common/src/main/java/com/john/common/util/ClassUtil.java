package com.john.common.util;

/**
 * Copyright：河北省通信建设有限公司网络分公司<p>
 * Author：John<p>
 * Description：类工具类<p>
 * Date：2020/2/10 15:49<p>
 * Updater：
 */
public class ClassUtil {
    public static boolean hasInterface(Class clazz, Class interfacea) {
        return hasInterface(clazz, interfacea.getName());
    }

    /**
     * 是否实现了某个接口
     *
     * @param clazz         类
     * @param interfaceName 接口名
     * @return
     */
    public static boolean hasInterface(Class clazz, String interfaceName) {
        for (Class anInterface : clazz.getInterfaces()) {
            if (anInterface.getName().equals(interfaceName)) {
                return true;
            }
        }
        return false;
    }

    public static <T> T instance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
