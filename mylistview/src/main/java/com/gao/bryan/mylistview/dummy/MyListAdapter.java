package com.gao.bryan.mylistview.dummy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.gao.bryan.mylistview.R;

/**
 * Created by bryan on 2017/3/23.
 */

public class MyListAdapter extends BaseExpandableListAdapter {
    String[] aaa = {"A","B","C"};
    String[][] bbb = {{"1","2","3"},{"4","5","6"},{"7","8","9"}};
    private  Context context;
    public MyListAdapter(Context context){
        this.context = context;
    }

    //第一層數量
    @Override
    public int getGroupCount() {
        return aaa.length;
    }
    //第二層數量
    @Override
    public int getChildrenCount(int groupPosition) {
        return bbb[groupPosition].length;
    }
    //第一層內容
    @Override
    public Object getGroup(int groupPosition) {
        return aaa[groupPosition];
    }
    //第二層內容
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return bbb[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
    //每个item的id是否是固定？一般true
    @Override
    public boolean hasStableIds() {
        return true;
    }
        //第一層的View
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_1,null);
        }
        TextView textview = (TextView) convertView.findViewById(R.id.textView4);
        textview.setText(aaa[groupPosition]);
        return convertView;
    }
        //第二層的View
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_2,null);
        }
        TextView textview = (TextView) convertView.findViewById(R.id.textView3);
        textview.setText(bbb[groupPosition][childPosition]);

        return convertView;
    }
        //第二層item是否可以被選
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
