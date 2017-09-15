package com.gao.bryan.mytest.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gao.bryan.mytest.R;
import com.gao.bryan.mytest.ball;

import java.util.List;

/**
 * Created by bryan on 2017/9/14.
 */

public class GridAdapter extends BaseAdapter {
    private final static String TAG = GridAdapter.class.getSimpleName();
    private List<ball> list;
    private Context context;
    private LayoutInflater layoutInflater;
    private boolean isShowDelete;
    private int num;
    public GridAdapter(List<ball> list, Context context){
        this.list = list;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
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
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ball b = list.get(position);
        View v = layoutInflater.inflate(R.layout.item_imger,null);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageView2);
        TextView textView = (TextView) v.findViewById(R.id.textView3);
        ImageView deleteImage=(ImageView)v.findViewById(R.id.delete_markView);
        deleteImage.setVisibility(isShowDelete && num==position ? View.VISIBLE :View.GONE);
        Log.d(TAG, "num : "+num+"size : "+list.size());
        imageView.setImageResource(b.getImage());
        textView.setText(b.getName());
        if(isShowDelete){
            deleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.remove(position);
                    Log.d(TAG, "onClick: 刪除"+position);
                    setIsShowDelete(false);
                }
            });
        }

        return v;
    }
    public void setIsShowDelete(boolean isShowDelete) {
        this.isShowDelete = isShowDelete;
        notifyDataSetChanged();
    }
    public void setnum(int num){
        this.num = num;
    }
}
