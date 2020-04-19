package com.john.common.util;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.john.base.BaseApp;

import java.util.List;

/**
 * Copyright：河北省通信建设有限公司网络分公司<p>
 * Author：John<p>
 * Description：app级工具类<p>
 * Date：2020/2/10 15:49<p>
 * Updater：
 */
public class AppUtil {

    /**
     * 检测程序是否安装
     *
     * @param packageName
     * @return
     */
    public static boolean isInstalled(String packageName) {
        PackageManager manager = BaseApp.context().getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> installedPackages = manager.getInstalledPackages(0);
        if (installedPackages != null) {
            for (PackageInfo info : installedPackages) {
                if (info.packageName.equals(packageName))
                    return true;
            }
        }
        return false;
    }
}
