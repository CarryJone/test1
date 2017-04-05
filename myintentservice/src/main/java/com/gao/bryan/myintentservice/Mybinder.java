package com.gao.bryan.myintentservice;

import android.os.Binder;

/**
 * Created by bryan on 2017/3/24.
 */

public class Mybinder extends Binder {
    String name ;
    String acc;

    public Mybinder(String name, String acc) {
        this.name = name;
        this.acc = acc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }


}
