package com.gao.bryan.mytablelayout;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity  {
    FragmentManager fragmentMgr;
    FragmentTransaction fragmentTrans;
    FragmentA f1;
    FragmentB f2;
    @BindView(R.id.textView)
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentMgr = getFragmentManager();
        fragmentTrans = fragmentMgr.beginTransaction();
        ButterKnife.bind(this);

        f1 = new FragmentA();

        f2 = new FragmentB();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                changeFragment(f1);
                f1.getDate(new FragmentA.CallBack() {
                    @Override
                    public void getResult(String name) {
                        textView.setText(name);
                    }
                });
                break;
            case R.id.button2:
                changeFragment(f2);
                    textView.setText(f2.getDate2());
                break;
        }
    }

    public void changeFragment(Fragment f) {
        FragmentTransaction a1 = this.getFragmentManager().beginTransaction();
        a1.replace(R.id.frameLay, f);
        a1.commitAllowingStateLoss();

    }
}