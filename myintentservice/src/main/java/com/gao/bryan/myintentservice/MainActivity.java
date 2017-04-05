package com.gao.bryan.myintentservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements ServiceConnection {
    TextView textView;
    MyBRReceiver myBRReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Intent a1 = new Intent("com.test.intentservice");
//        Bundle bundle = new Bundle();
//        bundle.putString("keyman","1");
//        a1.putExtras(bundle);
//
//        Intent a2 = new Intent("com.test.intentservice");
//        Bundle bundle2 = new Bundle();
//        bundle2.putString("keyman","2");
//        a2.putExtras(bundle2);
//
//        Intent a3 = new Intent("com.test.intentservice");
//        Bundle bundle3 = new Bundle();
//        bundle3.putString("keyman","3");
//        a3.putExtras(bundle3);
//
//        startService(a1);
//        startService(a2);
//        startService(a3);

        myBRReceiver = new MyBRReceiver();

//        IntentFilter itFilter = new IntentFilter();
//        itFilter.addAction("android.intent.action.BOOT_COMPLETED");
//        registerReceiver(myBRReceiver, itFilter);


    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {

    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
    public void onClick(View view){
        sendBroadcast(new Intent("com.example.broadcasttest.MY_BROADCAST"));
    }
}

