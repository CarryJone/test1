package com.gao.bryan.myintentservice;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;


public class MyService extends IntentService {
    final String TAG = "MyIntentService";
    Mybinder mybinder;
    public MyService() {
        super("MyService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            mybinder = new Mybinder("time","12345678");
            Bundle bundle = intent.getExtras();
            String action = bundle.getString("keyman");

            if (action.equals("1")) Log.d(TAG,"service1");
            else if (action.equals("2")) Log.d(TAG,"service2");
            else if (action.equals("3")) Log.d(TAG,"service3");

        }
        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){e.printStackTrace();}
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }

    @Nullable


    @Override
    public IBinder onBind(Intent intent) {
        return mybinder;
    }
}
