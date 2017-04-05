package com.gao.bryan.myfirebase;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by bryan on 2017/3/17.
 */

public class MyFirebaseService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        String message = remoteMessage.getNotification().getBody();
        //createNotifocation(message);
    }
    private void createNotifocation(String message) {
        // 1.取得NotificationManager物件
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // 2.建立NotificationCompat.Builder物件
        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setWhen(System.currentTimeMillis())
                .setContentTitle("推播通知")
                .setContentText(message);

        // 3.建立通知物件
        Notification notification = builder.build();

        // 4.使用BASIC_ID為編號發出通知
        manager.notify(101, notification);
    }
}
