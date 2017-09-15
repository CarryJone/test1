package com.gao.bryan.rxjavatest;

/**
 * Created by bryan on 2017/9/21.
 */

public class webdata {
    private String webId;
    private String webname;
    private String weblink;
    private String webimg;
    private String saltkey;

    @Override
    public String toString() {
        return "webdata{" +
                "webId='" + webId + '\'' +
                ", webname='" + webname + '\'' +
                ", weblink='" + weblink + '\'' +
                ", webimg='" + webimg + '\'' +
                ", saltkey='" + saltkey + '\'' +
                '}';
    }

    public String getWebId() {
        return webId;
    }

    public void setWebId(String webId) {
        this.webId = webId;
    }

    public String getWebname() {
        return webname;
    }

    public void setWebname(String webname) {
        this.webname = webname;
    }

    public String getWeblink() {
        return weblink;
    }

    public void setWeblink(String weblink) {
        this.weblink = weblink;
    }

    public String getWebimg() {
        return webimg;
    }

    public void setWebimg(String webimg) {
        this.webimg = webimg;
    }

    public String getSaltkey() {
        return saltkey;
    }

    public void setSaltkey(String saltkey) {
        this.saltkey = saltkey;
    }
}
