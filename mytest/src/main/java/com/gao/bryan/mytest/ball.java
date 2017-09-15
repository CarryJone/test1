package com.gao.bryan.mytest;

/**
 * Created by bryan on 2017/9/14.
 */

public class ball {
    private int image;
    private String name;

    public ball() {

    }

    public ball(int image, String name) {
        this.image = image;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
