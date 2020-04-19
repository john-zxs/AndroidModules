package com.john.common.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

/**
 * @author John
 * @Copyright 河北省通信建设有限公司网络分公司
 * @Description $desc$
 * @date 2019/3/28 15:02
 */
public class NotificationUtil extends ContextWrapper {

    private final int Notification_Id = 1;
    private NotificationManager mManager;
    public static final String sID = "channel_1";
    public static final String sName = "channel_name_1";
    private int iconId = 0;
    private Notification.Builder builder;
    private NotificationCompat.Builder ncBuilder;

    public NotificationUtil(Context context, int iconResId) {
        super(context);
        iconId = iconResId;
    }

    public void sendNotification(String title, String content) {
        if (Build.VERSION.SDK_INT >= 26) {
            createNotificationChannel();
            Notification notification = getNotification_26(title, content).build();
            getmManager().notify(Notification_Id, notification);
        } else {
            Notification notification = getNotification_25(title, content).build();
            getmManager().notify(Notification_Id, notification);
        }
    }

    public void sendProgressNotification(String title, int total) {
        if (Build.VERSION.SDK_INT >= 26) {
            createNotificationChannel();
            builder = getNotification_26(title, total);
            getmManager().notify(Notification_Id, builder.build());
        } else {
            ncBuilder = getNotification_25(title, total);
            getmManager().notify(Notification_Id, ncBuilder.build());
        }
    }

    public void updateProgress(int progress) {
        if (builder != null) {
            builder.setProgress(100, progress, false);
            getmManager().notify(Notification_Id, builder.build());
        } else if (ncBuilder != null) {
            ncBuilder.setProgress(100, progress, false);
            getmManager().notify(Notification_Id, ncBuilder.build());
        }
        if (progress >= 100) {
            getmManager().cancel(Notification_Id);
        }
    }


    private NotificationManager getmManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        }
        return mManager;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createNotificationChannel() {
        NotificationChannel channel = new NotificationChannel(sID, sName, NotificationManager.IMPORTANCE_HIGH);
        getmManager().createNotificationChannel(channel);
    }

    public NotificationCompat.Builder getNotification_25(String title, String content) {

        // 以下是展示大图的通知
        NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle();
        style.setBigContentTitle("BigContentTitle");
        style.setSummaryText("SummaryText");
        //        style.bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.pic));

        // 以下是展示多文本通知
        NotificationCompat.BigTextStyle style1 = new NotificationCompat.BigTextStyle();
        style1.setBigContentTitle(title);
        style1.bigText(content);

        return new NotificationCompat.Builder(getApplicationContext())
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(iconId)
                //                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round))
                .setStyle(style)
                .setAutoCancel(true);
    }

    public NotificationCompat.Builder getNotification_25(String title, int total) {
        return new NotificationCompat.Builder(getApplicationContext())
                .setContentTitle(title)
                .setProgress(total, 0, false)
                .setSmallIcon(iconId)
                .setAutoCancel(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Notification.Builder getNotification_26(String title, String content) {
        return new Notification.Builder(getApplicationContext(), sID)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(iconId)
                //                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round))
                //                .setStyle(new Notification.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.pic)))
                .setNumber(1)
                .setAutoCancel(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Notification.Builder getNotification_26(String title, int total) {
        return new Notification.Builder(getApplicationContext(), sID)
                .setContentTitle(title)
                .setProgress(total, 0, false)
                .setSmallIcon(iconId)
                .setNumber(1)
                .setAutoCancel(true);
    }
}
