package com.gao.bryan.mytest;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.gao.bryan.mytest.adapter.GridAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawer_layout;
    private View topbar;
    private ImageButton btn_right;
    private Right_fragment fg_right_menu;
    private FragmentManager fManager;
    private TextView textView;
    private GridView gridView;
    private Context context;
    private boolean isShowDelete;
    private GridAdapter gridAdapter;
    private List<ball> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        //fragment 交易物件
        fManager = getSupportFragmentManager();
        //側邊Menu
        fg_right_menu = (Right_fragment) fManager.findFragmentById(R.id.right_menu);
        init();

    }

    public void init(){
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_test);
        topbar = findViewById(R.id.topbar);
        btn_right = (ImageButton) topbar.findViewById(R.id.imageButton);

        gridView = (GridView) findViewById(R.id.grid_test);
        textView = (TextView) findViewById(R.id.textView);
        setstr("現在時間");

        //關閉手勢操作
        drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.END);
        //監聽fragment  關閉後鎖定
        drawer_layout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                drawer_layout.setDrawerLockMode(
                        DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.END);
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        fg_right_menu.setDrawerLayout(drawer_layout);
        setgritview();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                isShowDelete = false;
                gridAdapter.setIsShowDelete(isShowDelete);
            }
        });
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (position != (list.size()-1) ) {
                    if (isShowDelete){
                        isShowDelete = false;
                        gridAdapter.setnum(position);
                        gridAdapter.setIsShowDelete(isShowDelete);
                    }else{
                        isShowDelete = true;
                        gridAdapter.setnum(position);
                        gridAdapter.setIsShowDelete(isShowDelete);
                    }
                }


                return true;
            }
        });
    }

    public void onClick(View v) {
        drawer_layout.openDrawer(Gravity.RIGHT);
        drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED,
                Gravity.END);    //解除鎖定

    }
    public void setstr(String s){
        textView.setText(s);
    }
    public void setgritview(){
        list =  new ArrayList<>();
        list.add(new ball(R.drawable.www1,"網站1"));
        list.add(new ball(R.drawable.www2,"網站2"));
        list.add(new ball(R.drawable.www3,"網站3"));
        list.add(new ball(R.drawable.www5,"網站4"));
        list.add(new ball(R.drawable.www1,"網站5"));
        gridAdapter = new GridAdapter(list,context);
        gridView.setAdapter(gridAdapter);

    }
}
