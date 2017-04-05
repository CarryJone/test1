package com.gao.bryan.myasynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyAsyncTack  myAsyncTack = new MyAsyncTack();
        myAsyncTack.execute(100);

    }
}
class MyAsyncTack extends AsyncTask<Integer,Integer,Integer>{
    private static final String TAG = "MyAsyncTack";

    @Override
    protected void onPreExecute() {
        Log.d(TAG,"onPreExecute");
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        Log.d(TAG,"onProgressUpdate"+values[0]);
        super.onProgressUpdate(values);
    }

    @Override
    protected Integer doInBackground(Integer... integers) {
        int sum = 0 ;
        for(int i =0 ;i<integers[0];i++){
            sum += 1;
            Log.d(TAG,"doInBackground"+i);
            publishProgress(i);

        }
        return sum;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        Log.d(TAG,"onPostExecute"+integer);
    }
}
