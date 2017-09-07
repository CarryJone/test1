package com.gao.bryan.rxjavatest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by hermes on 2016/12/16.
 */
public class ResultData<T>
{
    @SerializedName("result")
    private String result;

    @SerializedName("msg")
    private String message;

    @SerializedName("data")
    @Expose
    private ArrayList<Logindata> data = new ArrayList<Logindata>();

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getResult()
    {
        return result;
    }

    public void setResult(String result)
    {
        this.result = result;
    }

    public ArrayList<Logindata> getData()
    {
        return data;
    }

    public void setData(ArrayList<Logindata> data)
    {

        this.data = data;
    }

    @Override
    public String toString()
    {
        final StringBuffer sb = new StringBuffer("ResultData{");
        sb.append("data=").append(data);
        sb.append(", result='").append(result).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}