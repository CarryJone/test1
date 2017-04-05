package com.gao.bryan.myportsiptest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
loginfragmentFragment login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setLoginfm();
    }
    public void setLoginfm(){
        if(login == null){
            login = new loginfragmentFragment();
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, login).commit();
    }
}
