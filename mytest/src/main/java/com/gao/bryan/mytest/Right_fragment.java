package com.gao.bryan.mytest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.gao.bryan.mytest.adapter.right_adapter;

/**
 * Created by bryan on 2017/9/12.
 */

public class Right_fragment extends Fragment implements View.OnClickListener{

    private static final String TAG = Right_fragment.class.getSimpleName();
    private DrawerLayout drawer_layout;
    private FragmentManager fManager;
    private FragmentActivity myContext;
    private Button button;
    private ListView listView;
    private String[] number ;
    private Context context;
    public Right_fragment(){}
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment,container,false);
        number = getResources().getStringArray(R.array.right_text);
        context = getActivity();
        button = (Button) view.findViewById(R.id.button5);
        listView = (ListView) view.findViewById(R.id.text_list);
        right_adapter adapter = new right_adapter(number,context);
        Log.d(TAG, "onCreateView: "+number.length);
        listView.setAdapter(adapter);
        button.setOnClickListener(this);
        fManager = myContext.getSupportFragmentManager();
        return view;
    }

    @Override
    public void onClick(View v) {
        Log.d("test", "onClick: ");

    }
    public void setDrawerLayout(DrawerLayout drawer_layout){
        this.drawer_layout = drawer_layout;
    }
    @Override
    public void onAttach(Activity activity) {
        myContext = (FragmentActivity) activity;
        super.onAttach(activity);

    }
}
