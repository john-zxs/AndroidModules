package com.john.common.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import androidx.annotation.RequiresApi;

/**
 * Copyright：河北省通信建设有限公司网络分公司<p>
 * Author：John<p>
 * Description：App安装器<p>
 * Date：2020/2/10 15:49<p>
 * Updater：
 */
public class AppInstaller {
    public static void install(Context context, File apk) {
        if (apk == null) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = FileUtil.getProvider(context, apk);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (uri.toString().contains("content:")) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        }
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    public static void install(Context context, String apkPath) {
        File apk = FileUtil.makeFile(apkPath);
        if (apk == null) {
            return;
        }
        install(context, apk);
    }

    public static boolean checkInstallPermission(Context context, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            boolean hasInstallPermission = isHasInstallPermissionWith(context);
            if (!hasInstallPermission) {
                startInstallPermissionSettingActivity(context, requestCode);
                return false;
            }
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static boolean isHasInstallPermissionWith(Context context) {
        if (context == null) {
            return false;
        }
        return context.getPackageManager().canRequestPackageInstalls();
    }

    /**
     * 开启设置安装未知来源应用权限界面
     *
     * @param context
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void startInstallPermissionSettingActivity(Context context, int requestCode) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, Uri.parse("package:" + context.getApplicationContext().getPackageName()));
        ((Activity) context).startActivityForResult(intent, requestCode);
        Toast.makeText(context, "请开启未知应用安装权限", Toast.LENGTH_SHORT).show();
    }

    /**
     * 静默安装,需要root权限
     *
     * @param path
     */
    private void silentInstall(final String path) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String TAG = AppInstaller.class.getSimpleName();
                DataOutputStream out = null;
                BufferedReader errorStream = null;
                try {
                    //申请su权限
                    Process process = Runtime.getRuntime().exec("su");
                    out = new DataOutputStream(process.getOutputStream());
                    //执行pm install 命令
                    String command = "pm install -r " + path + "\n";
                    out.write(command.getBytes(Charset.forName("UTF-8")));
                    out.flush();
                    out.writeBytes("exit\n");
                    out.flush();
                    process.waitFor();
                    errorStream = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                    String msg = "";
                    String line;
                    while ((line = errorStream.readLine()) != null) {
                        msg += line;
                    }
                    Log.i(TAG, "SilentInstall: " + msg);
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage(), e);
                } finally {
                    try {
                        if (out != null) {
                            out.close();
                        }
                        if (errorStream != null) {
                            errorStream.close();
                        }
                    } catch (IOException e) {
                        Log.e(TAG, e.getMessage(), e);
                    }
                }
            }
        }).start();
    }
}
