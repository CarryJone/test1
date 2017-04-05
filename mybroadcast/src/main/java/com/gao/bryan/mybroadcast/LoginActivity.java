package com.gao.bryan.mybroadcast;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    private EditText edit_user;
    private EditText edit_pawd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pref = PreferenceManager.getDefaultSharedPreferences(this);

        bindViews();
    }

    private void bindViews() {
        edit_user = (EditText) findViewById(R.id.edit_user);
        edit_pawd = (EditText) findViewById(R.id.edit_pawd);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!pref.getString("user","").equals("")){
            edit_user.setText(pref.getString("user",""));
            edit_pawd.setText(pref.getString("pawd",""));
        }
    }

    public void onClick(View v) {
        String user = edit_user.getText().toString();
        String pawd = edit_pawd.getText().toString();
        if(user.equals("123")&&pawd.equals("123")){
            editor = pref.edit();
            editor.putString("user", user);
            editor.putString("pawd", pawd);
            editor.commit();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(LoginActivity.this,"哟，竟然蒙对了~",Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(LoginActivity.this,"这么简单都输出，脑子呢？",Toast.LENGTH_SHORT).show();
        }

    }
}
