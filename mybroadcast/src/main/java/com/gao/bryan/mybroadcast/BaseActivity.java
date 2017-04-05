package com.gao.bryan.mybroadcast;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by bryan on 2017/3/24.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}