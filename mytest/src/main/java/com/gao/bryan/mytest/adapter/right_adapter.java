package com.gao.bryan.mytest.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gao.bryan.mytest.R;

/**
 * Created by bryan on 2017/9/14.
 */

public class right_adapter extends BaseAdapter {
    private static final String TAG = right_adapter.class.getSimpleName() ;
    private final String[] list ;
    private Context context;
    private LayoutInflater layoutInflater;
    public right_adapter(String[] list, Context context) {
        this.list = list;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        Log.d(TAG, "right_adapter: ");

    }

    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public Object getItem(int position) {
        return list[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.item_text,null);
        TextView textView = (TextView) view.findViewById(R.id.text_list);
        textView.setText(list[position]);
        Log.d(TAG, "getView: "+list[position]);
        return view;
    }
}
