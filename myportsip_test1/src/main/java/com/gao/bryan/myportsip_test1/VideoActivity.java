package com.gao.bryan.myportsip_test1;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.portsip.PortSipErrorcode;
import com.portsip.PortSipSdk;
import com.portsip.Renderer;

import static com.gao.bryan.myportsip_test1.MyApplication.MY_APPLICATION;

public class VideoActivity extends AppCompatActivity {
    PortSipSdk mportsdk;
    private ImageButton imgSwitchCamera = null;
    private LinearLayout mLlRemoteSurface = null;
    private LinearLayout mLlLocalSurface = null;
    private SurfaceView remoteSurfaceView = null;
    private SurfaceView localSurfaceView = null;
    MyApplication myApplication;
    Context context;
    long session;
    private boolean useFrontCamera = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
//        Bundle b = getIntent().getBundleExtra("sdk");
//        myApplication =(MyApplication) b.getSerializable("s");
        myApplication =MyApplication.getInstance();
        Log.d(MY_APPLICATION, "VideoActivity : " + myApplication.toString());
        context = this;
        mportsdk = myApplication.getPortSipSdk();
        imgSwitchCamera = (ImageButton) findViewById(R.id.imageView);
        session = myApplication.getSessionId();
        mLlLocalSurface = (LinearLayout) findViewById(R.id.local);
        mLlRemoteSurface = (LinearLayout) findViewById(R.id.remote);

        //本地端接口實例
        localSurfaceView = Renderer.CreateLocalRenderer(context);
        //遠端接口實例
        remoteSurfaceView = Renderer.CreateRenderer(context, true);


        mLlLocalSurface.addView(localSurfaceView);
        mLlRemoteSurface.addView(remoteSurfaceView);

        getLayoutSize();

        if(session != PortSipErrorcode.INVALID_SESSION_ID) {

            //顯示本地端video
            mportsdk.displayLocalVideo(true);
            //設置本地端窗口
            mportsdk.setLocalVideoWindow(localSurfaceView);
            //發送遠視頻
            mportsdk.sendVideo(session, true);
            //設置遠端窗口
            mportsdk.setRemoteVideoWindow(session, remoteSurfaceView);
        }
        else{
            Toast.makeText(context,"id不符",Toast.LENGTH_SHORT).show();
        }
    }
    public void onClick(View view){
        useFrontCamera = !useFrontCamera;
        mportsdk.displayLocalVideo(false);
        if (useFrontCamera) {
            mportsdk.setVideoDeviceId(1);
            mportsdk.setVideoOrientation(270);
        } else {
            mportsdk.setVideoDeviceId(0);
            mportsdk.setVideoOrientation(90);
        }
        mportsdk.displayLocalVideo(true);
    }

    public void getLayoutSize() {
        localSurfaceView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    boolean bIsFirstCall = true;

                    @Override
                    public void onGlobalLayout() {
                        if (bIsFirstCall) {
                            bIsFirstCall = false;
                            int w = localSurfaceView.getMeasuredWidth();
                            int h = localSurfaceView.getMeasuredHeight();
                            Log.d("localSurfaceView",w+"+"+h);
                            //mportsdk.setVideoResolution(w, h);
                        }
                    }
                });
    }
}
