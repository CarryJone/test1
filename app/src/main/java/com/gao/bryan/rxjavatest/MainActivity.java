package com.gao.bryan.rxjavatest;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getSimpleName();
    private Context c;
    private TextView textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c = this;
        textview = (TextView) findViewById(R.id.textview);

        Observer<ArrayList<String>> observer = new Observer<ArrayList<String>>() {
            @Override
            public void onCompleted() {

                Log.d(TAG, "Completed!");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ArrayList<String> strings) {
                for (String s :strings){
                    Log.d(TAG, "onNext: "+s);
                }
            }
        };


        ArrayList<String> list = new ArrayList<>();
        list.add("Hi");
        list.add("Hello");
        list.add("Java");
        list.add("Android");
        list.add("ASPNET");


        Subscriber<String> subdcriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext: "+s.toString());
                Toast.makeText(c,s.toString(),Toast.LENGTH_SHORT).show();
            }
        };
        Studen a1 = new Studen("大寶","15");
        Studen a2 = new Studen("中寶","18");
        Studen a3 = new Studen("小寶","19");
        Studen a4 = new Studen("胖寶","14");

        Studen[] studens = new Studen[]{a1,a2,a3,a4};

        Observable.from(studens)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<Studen, Observable<String>>() {
                    @Override
                    public Observable<String> call(Studen s) {
                        return Observable.just(s.toString());
                    }
                }).subscribe(subdcriber);




        Observable.from(list)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {
                        return Observable.just(s);
                    }
                }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, "call: "+s);
            }
        });


        ArrayList<Integer> count = new ArrayList<>();
        count.add(5);
        count.add(56);
        count.add(8);
        count.add(9);
        count.add(7);
        count.add(8);


        Collections.sort(count);


        Observable.from(count)
                .flatMap(new Func1<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(Integer integer) {
                        integer += integer;
                        return Observable.just(integer);
                    }
                }).flatMap(new Func1<Integer, Observable<String>>() {
            @Override
            public Observable<String> call(Integer integer) {
                String countst = String.valueOf(integer);
                return Observable.just(countst+123);
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String integer) {
                Log.d(TAG, "onNext: "+integer);
            }
        });

      o1("123").subscribeOn(Schedulers.newThread())
              .map(new Func1<String, String>() {
                  @Override
                  public String call(String s) {
                      return  s+s+s;
                  }
              })
              .observeOn(Schedulers.newThread())
            .subscribe(new Action1<String>() {
          @Override
          public void call(String s) {
            textview.setText(s);
          }
      });

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("acct","bryan");
            jsonObject.put("pwd","sec@1234");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ApiServer.getInstance().logincation(new acct("bryan","sec@1234")).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).flatMap(new Func1<ResultData<Logindata>, Observable<ArrayList<Logindata>>>()
        {
            @Override
            public Observable<ArrayList<Logindata>> call(ResultData<Logindata> logindataResultData) {
                Log.d(TAG, "call: "+logindataResultData.getResult());
                if (logindataResultData.getResult().equals("1"))
                {
                    return Observable.just(logindataResultData.getData());
                } else
                {
                    return Observable.error(new Exception(logindataResultData.getMessage()));
                }
            }
        }).subscribe(new Observer<ArrayList<Logindata>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ArrayList<Logindata> logindatas) {
                for (Logindata data : logindatas)
                    Log.d(TAG, "onNext: " + data);

            }
        });
    }
    public Observable<String> o1(final String s){

        new Thread(){
            @Override
            public void run() {
                super.run();
               textview.setText(s);
            }

        };
        return Observable.just(s);

    }




}
class Studen {
    String name;
    String age;

    public Studen(String name, String age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Studen{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
