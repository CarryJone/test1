package com.gao.bryan.mytablelayout;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by bryan on 2017/3/20.
 */

public class FragmentB extends Fragment {
    private View v;
    private int num;
    private String name;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_tmp, container, false);
        TextView text = (TextView)v.findViewById(R.id.text_view);
        text.setText("BBBBBBBBBBBBBBBBBB");
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
    public String getDate2(){
        name = "654321";
        return name;
    }
}

