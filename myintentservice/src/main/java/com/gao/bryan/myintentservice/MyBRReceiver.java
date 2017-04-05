package com.gao.bryan.myintentservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by bryan on 2017/3/24.
 */

public class MyBRReceiver extends BroadcastReceiver {
    private final String ACTION_BOOT = "com.example.broadcasttest.MY_BROADCAST";
    @Override
    public void onReceive(Context context, Intent intent) {
        if(ACTION_BOOT.equals(intent.getAction()))
        Toast.makeText(context,"網路已掛斷",Toast.LENGTH_SHORT).show();
    }
}
