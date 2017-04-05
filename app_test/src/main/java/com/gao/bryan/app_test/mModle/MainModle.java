package com.gao.bryan.app_test.mModle;

import java.util.ArrayList;

/**
 * Created by bryan on 2017/3/10.
 */

public class MainModle {
    ArrayList<String> list = new ArrayList<>();

    public void setToNoteList(String toNoteList) {
        list.add(0,toNoteList);
    }

    public ArrayList<String> getlist() {

        return list;
    }

    public void deletelist(int p0) {
        list.remove(p0);
    }
}
