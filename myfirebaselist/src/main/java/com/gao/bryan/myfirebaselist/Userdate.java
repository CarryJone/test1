package com.gao.bryan.myfirebaselist;

import java.io.Serializable;

/**
 * Created by bryan on 2017/4/5.
 */

public class Userdate implements Serializable {
    private String name;
    private String count;


    public Userdate() {
    }

    public Userdate(String name, String count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
