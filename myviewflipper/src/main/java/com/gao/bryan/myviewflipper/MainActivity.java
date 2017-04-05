package com.gao.bryan.myviewflipper;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {
    ViewFlipper vf;
    ImageView imageView,imageView2;
    boolean isOnBack = false;
    Handler handler;
    Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(this,MyService.class);
        startService(intent);
        Button bu = (Button) findViewById(R.id.bu);

        vf = (ViewFlipper)this. findViewById(R.id.viewflipper);

        float x = vf.getScaleX();
        float y = vf.getScaleY();
        Log.d("XYsize",x+"+"+y);
        // 設定 ViewFlipper 的進出動畫配置
        vf.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.on));
        vf.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.out));

//        Animation rInAnim = AnimationUtils.loadAnimation(this, R.anim.on);
//        Animation rOutAnim = AnimationUtils.loadAnimation(this, R.anim.out);

//        imageView.setAnimation(rInAnim);
//        imageView.setAnimation(rOutAnim);
//        vf.setInAnimation(rInAnim);
//        vf.setOutAnimation(rOutAnim);
//        vf.showPrevious();

        // 動畫開始
        vf.startFlipping();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                isOnBack = false;
            }
        } ;
        //按下next button後切換到下張view
        bu.setOnClickListener(new View.OnClickListener(){
            public void onClick(View w){

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK ){
           if(!isOnBack) {
               isOnBack = true;
               Toast.makeText(this, "在點擊一次退出", Toast.LENGTH_SHORT).show();
               stopService(intent);
               handler.sendEmptyMessageDelayed(0,2000);

           }else{
               finish();
           }
            return false;
        }

        return super.onKeyDown(keyCode, event);
    }
}
