package com.gao.bryan.mygridviewtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by bryan on 2017/9/11.
 */

public class DataAdapter extends BaseAdapter {
    private Context context;
    private List<Integer> list ;
    private String[] strlist = new String[]{"棒球","足球","籃球","棒球"};


    public DataAdapter(Context context,List<Integer> list){
        this.list = list;
        this.context = context;
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
        View view = LayoutInflater.from(context).inflate(R.layout.layout,null);

        final int image = list.get(position);
        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(strlist[position%4]);
        imageView.setImageResource(image);

        return view;
    }
    public void setdata(List<Integer> list){

        this.list = list;
    }
}
