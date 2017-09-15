package com.gao.bryan.mygridviewtest;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private GridView gridView ;
    private Context context;
    private Spinner spinner;
    private  List<Integer> list;
    private DataAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        gridView = (GridView) findViewById(R.id.testgrid);
        list = new ArrayList<Integer>(){};
        adapter = new DataAdapter(context,list);
        spinner = (Spinner) findViewById(R.id.spinner);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context,position+"",Toast.LENGTH_SHORT).show();
            }
        });
       spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               TextView textView = (TextView) view;
               String who = textView.getText().toString();
               switch (who){
                   case "動物":
                       Log.d(TAG, "onFocusChange: "+"動物");
                       setanimal();
                       adapter.notifyDataSetChanged();
                       break;
                   case "球類":
                       Log.d(TAG, "onFocusChange: "+"球類");
                       setball();
                       adapter.notifyDataSetChanged();
                       break;
               }
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });

    }

    @Override
    protected void onResume() {
        super.onResume();
        init();

    }

    public void init(){
        setanimal();
        gridView.setAdapter(adapter);
        String[] str = new String[]{"動物","球類"};
        ArrayAdapter<String> spinnadapter = new ArrayAdapter<String>(context,android.R.layout.simple_dropdown_item_1line,str);
        spinner.setAdapter(spinnadapter);
    }


    public List<Integer> setball(){
        list.clear();

        list.add(R.drawable.ball1);
        list.add(R.drawable.ball1);
        list.add(R.drawable.ball1);
        list.add(R.drawable.ball1);
        list.add(R.drawable.ball1);
        list.add(R.drawable.ball1);
        list.add(R.drawable.ball1);
        list.add(R.drawable.ball1);
        list.add(R.drawable.ball1);
        list.add(R.drawable.ball1);
        list.add(R.drawable.ball2);
        list.add(R.drawable.ball2);
        list.add(R.drawable.ball2);
        list.add(R.drawable.ball2);
        list.add(R.drawable.ball2);
        list.add(R.drawable.ball2);
        list.add(R.drawable.ball2);
        list.add(R.drawable.ball2);
        list.add(R.drawable.ball2);
        list.add(R.drawable.ball2);
        list.add(R.drawable.ball2);
        list.add(R.drawable.ball2);
        list.add(R.drawable.ball3);
        list.add(R.drawable.ball3);
        list.add(R.drawable.ball3);
        list.add(R.drawable.ball3);
        list.add(R.drawable.ball3);
        list.add(R.drawable.ball3);
        list.add(R.drawable.ball3);
        list.add(R.drawable.ball3);
        list.add(R.drawable.ball3);
        list.add(R.drawable.ball3);
        list.add(R.drawable.ball3);
        list.add(R.drawable.ball3);
        list.add(R.drawable.ball3);
        list.add(R.drawable.ball4);
        list.add(R.drawable.ball4);
        list.add(R.drawable.ball4);
        list.add(R.drawable.ball4);
        list.add(R.drawable.ball4);
        list.add(R.drawable.ball4);
        list.add(R.drawable.ball4);
        list.add(R.drawable.ball4);
        list.add(R.drawable.ball4);
        list.add(R.drawable.ball4);
        list.add(R.drawable.ball4);
        list.add(R.drawable.ball4);
        list.add(R.drawable.ball4);
        list.add(R.drawable.ball4);
        list.add(R.drawable.ball4);
        list.add(R.drawable.ball4);
        Collections.shuffle(list);
        return list;
    }

    private List<Integer> setanimal(){
        Integer[] animal = new Integer[]{R.drawable.animal1,R.drawable.animal2,R.drawable.animal3,R.drawable.animal4};
        list.clear();
        for (int a= 0;a<=50;a++){
            list.add(animal[a%4]);
        }
        Collections.shuffle(list);

        return list;
    }

}
