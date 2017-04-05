package com.gao.bryan.myfirebaselist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private static final String TAG="MyFirebase";
    private List<Userdate> list ;
    public EditText e01,e02;
    private TextView t01,t02;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t01 = (TextView) findViewById(R.id.t01);
        t02 = (TextView) findViewById(R.id.t02);
        e01 = (EditText) findViewById(R.id.editText);
        e02 = (EditText) findViewById(R.id.editText2);
        list = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message/");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                list.clear();
                for (DataSnapshot ds :dataSnapshot.getChildren()){
                    Userdate user = ds.getValue(Userdate.class);
                    list.add(user);
                }
                Collections.sort(list, new Comparator<Userdate>() {
                    @Override
                    public int compare(Userdate o1, Userdate o2) {
                        return o1.getCount().compareTo(o2.getCount());
                    }
                });
                for(Userdate date :list){
                    t01.append(date.getName());
                    t02.append(date.getCount());
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
    public void onClick(View view){
        Userdate user = new Userdate(e01.getText().toString(),e02.getText().toString());

        Userdate user2 = list.get(list.size()-1);
        Log.d(TAG,"user2 value = "+user2.getCount());
        Log.d(TAG,"list size value = "+list.size());
        Integer a = Integer.parseInt(user.getCount());
        Integer b = Integer.parseInt(user2.getCount());
        // 我的分數比資料庫的大 >0   我的分數比資料庫小 < 0
        //user.getCount().compareTo(user2.getCount())
        if(a<b) {
            list.add(user);
            myRef.setValue(list);
        }
    }
}
