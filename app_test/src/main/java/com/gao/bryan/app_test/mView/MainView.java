package com.gao.bryan.app_test.mView;

import java.util.ArrayList;

/**
 * Created by bryan on 2017/3/10.
 */

public interface MainView {
    void setcontentview();

    void initListview();
    void updataListview(ArrayList<String> getlist);

    void clearNote();

}
