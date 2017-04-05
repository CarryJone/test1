package com.gao.bryan.app_test.Mypresenter;

import com.gao.bryan.app_test.mModle.MainModle;
import com.gao.bryan.app_test.mView.MainView;

import java.util.ArrayList;

/**
 * Created by bryan on 2017/3/10.
 */

public class MainPresenter {
    private final MainView view;
    private final MainModle modle;

    public MainPresenter(MainView view) {
        this.view = view;
        this.modle = new MainModle();
    }




    public void onCreat(){
        view.setcontentview();
        view.initListview();
    }

    public void onSaveButtonClick(String note){
        if(note.isEmpty()){
            return;
        }
        modle.setToNoteList(note);
        view.clearNote();
        view.updataListview(getlist());
    }
    public void ondeleteButton(int num){
        modle.deletelist(num);
        view.updataListview(modle.getlist());
    }
    public ArrayList<String> getlist(){
        return modle.getlist();
    }

}
