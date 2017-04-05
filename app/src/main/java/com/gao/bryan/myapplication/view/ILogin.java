package com.gao.bryan.myapplication.view;

import android.view.View;

/**
 * Created by bryan on 2017/3/9.
 */

public interface ILogin {
     void onClick(View v);
     void onClearText();
     void onLoginResult(Boolean result, int code);
     void onSetProgressBarVisibility(int visibility);
}
