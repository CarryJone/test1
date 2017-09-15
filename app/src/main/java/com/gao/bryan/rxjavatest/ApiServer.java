package com.gao.bryan.rxjavatest;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by bryan on 2017/9/6.
 */

public class ApiServer {

    private final static String TAG = ApiServer.class.getSimpleName();
//    private final static String  API_SERVER_URL = "http://52.175.216.100/WebAPI/api/";
    private final static String  API_SERVER_URL = "http://192.168.1.220/WebApi/";
    private final SexApiService myApiServer;
    public static ApiServer service = new ApiServer();

    public static ApiServer getInstance()
    {
        return service;
    }
    public ApiServer()
    {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS).connectTimeout(60, TimeUnit.SECONDS).build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_SERVER_URL).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build();
        myApiServer = retrofit.create(SexApiService.class);
    }

    private interface SexApiService
    {
        @POST("getUserWebList.ashx")
        Observable<ResultWebdata<webdata>> getweblist(
                @Query("Acc")
                        String account);


    }

    public Observable<ResultWebdata<webdata>> getweblist(String a)
    {
        Log.d(TAG, "logincation: "+a.toString());
        return myApiServer.getweblist(a);
    }
//    private interface SexApiService
//    {
//        @POST("authentication")
//        Observable<ResultData<Logindata>> login(
//                @Body acct a);
//
//
//    }
//
//    public Observable<ResultData<Logindata>> logincation(acct a)
//    {
//        Log.d(TAG, "logincation: "+a.toString());
//        return myApiServer.login(a);
//    }

}
