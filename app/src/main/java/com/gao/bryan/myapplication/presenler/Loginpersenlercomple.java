package com.gao.bryan.myapplication.presenler;

import com.gao.bryan.myapplication.view.ILogin;

import java.util.logging.Handler;

/**
 * Created by bryan on 2017/3/9.
 */

public class Loginpersenlercomple implements Loginpresenler {
     ILogin iLogin ;
     Handler handler;
    public Loginpersenlercomple(ILogin iLogin){
        this.iLogin = iLogin;

    }
    @Override
    public void clear()
    {

        iLogin.onClearText();
    }

    @Override
    public void doLogin(String name, String passwd) {

    }

    @Override
    public void setProgressBarVisiblity(int visiblity) {

    }
}
