package com.gao.bryan.mylistview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by bryan on 2017/3/23.
 */

public class Myadapter extends BaseAdapter {
    private Context mcontext;
    private ArrayList<NameList> list;
    public Myadapter(Context context,ArrayList<NameList> list){
        mcontext = context;
        this.list = list;
        Log.d("getView","context");
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("getView","執行");
        ViewHolder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(mcontext).inflate(R.layout.adapter_layout,null);
            holder = new ViewHolder();
            holder.textView = (TextView) convertView.findViewById(R.id.textView);
            holder.textView2 = (TextView) convertView.findViewById(R.id.textView2);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(list.get(position).getName());
        holder.textView2.setText(list.get(position).getName2());
        return convertView;
    }
    class ViewHolder{
        TextView textView,textView2;

    }
}
