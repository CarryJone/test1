package com.gao.bryan.app_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.gao.bryan.app_test.Mypresenter.MainPresenter;
import com.gao.bryan.app_test.mView.MainView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainView {
    private MainPresenter preserten;
    private ListView listView;
    private ArrayList<String> notelist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preserten = new MainPresenter(this);
        preserten.onCreat();
    }

    @Override
    public void setcontentview() {
        setContentView(R.layout.activity_main);

    }

    @Override
    public void initListview() {
        notelist = preserten.getlist();

    }

    @Override
    public void updataListview(ArrayList<String> getlist) {
        notelist = getlist;
        TextView textView = (TextView) findViewById(R.id.textView);
        for (String s :notelist){
            textView.append(s);
        }
    }


    @Override
    public void clearNote() {
        EditText editText = getEditView();
        editText.setText("");
    }

    private EditText getEditView() {
        return (EditText) findViewById(R.id.editText);
    }

    public void onClick(View view){
        preserten.onSaveButtonClick(getEditView().getText().toString());
    }

}
