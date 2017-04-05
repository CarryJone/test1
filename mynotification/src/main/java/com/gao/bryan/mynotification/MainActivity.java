package com.gao.bryan.mynotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private NotificationManager mNManager;
    private Notification notify1;
    Bitmap LargeBitmap = null;
    private static final int NOTIFYID_1 = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        //创建大图标的Bitmap
        mNManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        bindView();

    }

    private void bindView() {

    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                //定义一个PendingIntent点击Notification后启动一个Activity
                Intent it = new Intent(mContext, MainActivity.class);
                PendingIntent pit = PendingIntent.getActivity(mContext, 0, it, 0);

                //设置图片,通知标题,发送时间,提示方式等属性
                Notification.Builder mBuilder = new Notification.Builder(this);
                mBuilder.setContentTitle("叶良辰")                        //标题
                        .setContentText("我有一百种方法让你呆不下去~")      //内容
                        .setSubText("——记住我叫叶良辰")                    //内容下面的一小段文字
                        .setTicker("收到叶良辰发送过来的信息~")             //收到信息后状态栏显示的文字信息
                        .setWhen(System.currentTimeMillis())           //设置通知时间


                        .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE)    //设置默认的三色灯与振动器
                        .setAutoCancel(true)                           //设置点击后取消Notification
                        .setContentIntent(pit);                        //设置PendingIntent
                notify1 = mBuilder.build();
                mNManager.notify(NOTIFYID_1, notify1);
                break;

            case R.id.button2:
                //除了可以根据ID来取消Notification外,还可以调用cancelAll();关闭该应用产生的所有通知
                mNManager.cancel(NOTIFYID_1);                          //取消Notification
                break;

        }
    }
}
