package com.gao.bryan.myapplication.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.gao.bryan.myapplication.R;
import com.gao.bryan.myapplication.presenler.Loginpersenlercomple;
import com.gao.bryan.myapplication.presenler.Loginpresenler;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ILogin,View.OnClickListener {
    @BindView(R.id.editText)
    EditText username;
    @BindView(R.id.editText2)
    EditText password;
    @BindView(R.id.button)
    Button login;
    @BindView(R.id.button2)
    Button creal;
    @BindView(R.id.linearlayout)
    LinearLayout linearLayout;
    private Loginpresenler loginpresenler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginpresenler = new Loginpersenlercomple(this);
        username = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);
        login = (Button) findViewById(R.id.button);
        creal = (Button) findViewById(R.id.button2);
        login.setOnClickListener(this);
        creal.setOnClickListener(this);
        linearLayout = (LinearLayout) findViewById(R.id.linearlayout);
        ButterKnife.bind(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                Toast.makeText(this,"123",Toast.LENGTH_SHORT).show();
                creal.setVisibility(View.VISIBLE);
                break;
            case R.id.button2:
                loginpresenler.clear();
                creal.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onClearText() {
        username.setText("");
        password.setText("");

    }

    @Override
    public void onLoginResult(Boolean result, int code) {

    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {

    }
}
