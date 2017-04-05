package com.gao.bryan.mylistview;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gao.bryan.mylistview.dummy.MyListAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Myadapter myadapter;
    ListView listView;
    ExpandableListAdapter m1;
    Context c;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        c = this;
        setContentView(R.layout.activity_main);
        final ArrayList<NameList> list = new ArrayList<>();
        list.add(new NameList("111", "222"));
        list.add(new NameList("222", "444"));
        list.add(new NameList("222", "555"));
        list.add(new NameList("333", "888"));
        list.add(new NameList("555", "777"));
        list.add(new NameList("444", "111"));
        myadapter = new Myadapter(this, list);
        listView = (ListView) findViewById(R.id.listview);

        listView.setAdapter(myadapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                NameList a = list.get(position);
                a.setName("999");
                myadapter.notifyDataSetChanged();
            }
        });

        //--------------------------------ExpandableListAdapter

        m1 = new MyListAdapter(this);
        ExpandableListView e = (ExpandableListView) findViewById(R.id.Expand);
        e.setAdapter(m1);
        e.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                midToast(groupPosition +"",Toast.LENGTH_SHORT);
                return false;
            }
        });
        e.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

               midToast(groupPosition + "/" + childPosition,Toast.LENGTH_SHORT);
                return true;
            }
        });
    }

    void midToast(String str, int showTime)
    {
        Toast toast = Toast.makeText(c, str, showTime);
        toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL , 0, -300);  //设置显示位置
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        v.setTextSize(30);
        v.setTextColor(Color.YELLOW);     //设置字体颜色
        toast.show();
    }
}

