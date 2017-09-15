package com.gao.bryan.rxjavatest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by hermes on 2016/12/16.
 */
public class ResultWebdata<T>
{
    @SerializedName("Result")
    private String result;

    @SerializedName("MSG")
    private String MSG;
    @SerializedName("Data")
    @Expose
    private ArrayList<webdata> data = new ArrayList<webdata>();

    public String getResult()
    {
        return result;
    }

    public void setResult(String result)
    {
        this.result = result;
    }

    public ArrayList<webdata> getData()
    {
        return data;
    }

    public void setData(ArrayList<webdata> data)
    {

        this.data = data;
    }

    public String getMSG() {
        return MSG;
    }

    public void setMSG(String MSG) {
        this.MSG = MSG;
    }

    @Override
    public String toString()
    {
        final StringBuffer sb = new StringBuffer("ResultData{");
        sb.append("data=").append(data);
        sb.append(", result='").append(result).append('\'');
        sb.append('}');
        return sb.toString();
    }
}