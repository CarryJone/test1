package com.gao.bryan.myportsip_test1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.portsip.OnPortSIPEvent;
import com.portsip.PortSipEnumDefine;
import com.portsip.PortSipErrorcode;
import com.portsip.PortSipSdk;

import java.util.Random;

import static com.gao.bryan.myportsip_test1.MyApplication.MY_APPLICATION;

public class MainActivity extends AppCompatActivity implements OnPortSIPEvent {
    EditText username, password,name;
    TextView textview;
    String statusString;
    String LogPath;
    UserInfo userInfo;
    PortSipSdk mSipSdk;
    Context context;
    long sessionisd = 0;
    String licenseKey = "2Yh0zMDFENzgwRTVDMDk4NjFBQzI2OEUxMjg1RUU4OEQ3OEA4REMxQzEzNDRCNTkzNzlBQTRDQTkwNDAyRjNDMzM4Q0A3Q0VFMkM5NTkxMThFQzJEQjU3NDdGODVBODYwNzU4REBGMjZCODAyMUVBMzc4Mjg3REQyNzVBNDI1OTc4OTg4Ng";
    MyApplication myApplication;
    private String videoEvent = "VideoEvent";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findview();
        myApplication = MyApplication.getInstance();
        Log.d(MY_APPLICATION, "MainActivity new : " + myApplication.toString());
        mSipSdk.setOnPortSIPEvent(this);
        myApplication.setPortSipSdk(mSipSdk);

//        myApplication.setMcontext(context);

    }
public void findview(){
    context = this;
    password = (EditText) findViewById(R.id.passedit);
    username = (EditText) findViewById(R.id.useredit);
    name = (EditText) findViewById(R.id.name);
    userInfo = new UserInfo();
    mSipSdk = new PortSipSdk();
    textview = (TextView) findViewById(R.id.showinif);

}
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.online:
                onlinefc();
                break;
            case R.id.offline:
                offlinefc();
                break;
            case R.id.call:
                callfc();
                break;
            case R.id.account1:
                username.setText("hzn.sip1");
                password.setText("hzn@sip1");
                name.setText("hzn.sip2");
                break;
            case R.id.account2:
                username.setText("hzn.sip2");
                password.setText("hzn@sip2");
                name.setText("hzn.sip1");
                break;
            case R.id.vedio:
                if(myApplication.isGotoVedio()) {
                    Intent intent = new Intent(context, VideoActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(context,"等等",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void onlinefc() {
        Environment.getExternalStorageDirectory();
        LogPath = Environment.getExternalStorageDirectory().getAbsolutePath() + '/';
        userInfo.setUserName(username.getText().toString());
        userInfo.setUserPwd(password.getText().toString());
        setUserInfo();


    }

    int setUserInfo() {
        //得到路徑目錄
        Environment.getExternalStorageDirectory();
        LogPath = Environment.getExternalStorageDirectory().getAbsolutePath() + '/';
        Log.d("初始化", "第一步");
        Log.d("初始化", userInfo.getUserName().toString());
//		String localIP = myApplication.getLocalIP(false);// ipv4
        String localIP = "0.0.0.0";
        int localPort = new Random().nextInt(4940) + 5060;
        UserInfo info = userInfo;
        //資料是否可用
        if (userInfo.isAvailable()) {
            Log.d("初始化", "第二步");
            //創建處裡程式
            mSipSdk.CreateCallManager(context.getApplicationContext());// step 1
            //初始化
            int result = mSipSdk.initialize(info.getTransType(),
                    PortSipEnumDefine.ENUM_LOG_LEVEL_NONE, LogPath,
                    8, "PortSIP VoIP SDK for Android",
                    0, 0);// step 2
            Log.d("初始化", "初始化中");
            //初始化失敗
            if (result != PortSipErrorcode.ECoreErrorNone) {
                statusString = "init Sdk Failed";
                Log.d("初始化", "初始畫失敗");
                return result;
            }
            //設置SRTP
            mSipSdk.setSrtpPolicy(info.getSrtpType());
            Log.d("初始化", "設置SRTP");
            //設置Licensekey (在199 交接 portsip內 有KEY)
            int nSetKeyRet = mSipSdk.setLicenseKey(licenseKey);// step 3
            Log.d("初始化", "Licensekey");
            //判斷KEY是否可用
            if (nSetKeyRet == PortSipErrorcode.ECoreTrialVersionLicenseKey) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                Log.d("初始化", "判斷KEY不能用");
                builder.setTitle("Prompt").setMessage(R.string.trial_version_tips);
                builder.setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder.create().show();
            } else if (nSetKeyRet == PortSipErrorcode.ECoreWrongLicenseKey) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle("Prompt").setMessage("This sample was built base on Release PortSIP VoIP SDK, You set a error License Key. Feel free contact us at: support@portsip.com to check the License Key.");
                builder.setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder.create().show();
                return -1;
            }
            //設置帳戶 成功返回0  失敗返回特定代碼
            result = mSipSdk.setUser(info.getUserName(), info.getUserDisplayName(), info.getAuthName(), info.getUserPassword(),
                    localIP, localPort, info.getUserdomain(), info.getSipServer(), info.getSipPort(),
                    info.getStunServer(), info.getStunPort(), null, 5060);// step 4
            Log.d("資訊", info.toString());
            if (result != PortSipErrorcode.ECoreErrorNone) {
                statusString = "setUser resource failed";
                Log.d("初始化", "帳戶設置失敗");
                textview.setText(statusString);
                return result;
            }
            statusString = "帳戶設置成功";
            result = mSipSdk.registerServer(90, 3);
            Log.d("註冊SIP",result+"");
        } else {
            return -1;
        }
        //設置Av參數
        textview.setText(statusString);
        myApplication.setportInit();
        return PortSipErrorcode.ECoreErrorNone;
    }

    public void offlinefc() {
        //註銷註冊
        mSipSdk.unRegisterServer();
        //釋放處理程序
        mSipSdk.DeleteCallManager();
        myApplication.setGotoVedio(false);
        textview.setText("登出成功");
    }

    public void callfc() {
        String obname = name.getText().toString();
        //視頻方向
        mSipSdk.setVideoOrientation(270);
        //設定相機
        mSipSdk.setVideoDeviceId(1);
        if (mSipSdk.isAudioCodecEmpty()) {
            Toast.makeText(context,"音頻未設定",Toast.LENGTH_SHORT).show();
            return;
        }
        long callid = mSipSdk.call(obname,true,true);
        sessionisd = callid;
        if(callid<=0){
            Toast.makeText(context,"撥打失敗",Toast.LENGTH_SHORT).show();
            return;
        }

        Log.d("初始化","撥打成功");
        textview.setText("撥打中...");

    }

    @Override
    public void onRegisterSuccess(String s, int i) {
        textview.setText("登入成功");
    }

    @Override
    public void onRegisterFailure(String s, int i) {
        textview.setText("登入失敗");
    }
    //當有來電時觸發
    @Override
    public void onInviteIncoming(final long sessionId, String s, String s1, String s2, String s3, String s4, String s5, boolean b, boolean b1) {
        Toast.makeText(context,"來電觸發",Toast.LENGTH_SHORT).show();

        Log.d(videoEvent,sessionisd+"\n呼叫者的顯示名稱"+s+"\n調用者"+s1+"\n被叫的顯示名稱"+s2+"\n被叫"+s3+"\n音頻編解碼器"+s4+"\n視頻編解碼器"+s5);
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Incoming Video Call");

        alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL,"Video",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        //Answer Video call
                        Log.d("接聽","點擊確認");
//                        mSipSdk.joinToConference(l);
//                        long video = mSipSdk.sendVideo(sessionisd,true);
                        int video = mSipSdk.answerCall(sessionId, true);
                            Log.d("接聽",video+"");

//                        Intent intent = new Intent(context, VideoActivity.class);
//                        intent.putExtra("id",sessionisd);
//                        Bundle bundle = new Bundle();
//                        bundle.putSerializable("sdk",myApplication);
//                        intent.putExtra("s",bundle);
//                        intent.putExtra("s",myApplication);
//                        Log.d(MY_APPLICATION, "MainActivity put : " + myApplication.toString());
//                        startActivity(intent);
                    }
                });
        alertDialog.show();
    }

    @Override
    public void onInviteTrying(long l) {

    }

    @Override
    public void onInviteSessionProgress(long l, String s, String s1, boolean b, boolean b1, boolean b2) {

    }

    @Override
    public void onInviteRinging(long l, String s, int i) {
        Log.d(videoEvent,"撥打"+l+"\n"+s+"\n"+i);
//        if(!myApplication.isGotoVedio()) {
//            Intent intent = new Intent(context, VideoActivity.class);
//            startActivity(intent);
//        }
//        myApplication.setGotoVedio(true);

    }

    @Override
    public void onInviteAnswered(long l, String s, String s1, String s2, String s3, String s4, String s5, boolean b, boolean b1) {
        Log.d(videoEvent,"接起"+l+"\n"+s+"\n");
    }

    @Override
    public void onInviteFailure(long l, String s, int i) {
        Log.d(videoEvent,"onInviteFailure"+l+"\n"+s+"\n"+i);
    }

    @Override
    public void onInviteUpdated(long l, String s, String s1, boolean b, boolean b1) {
        Log.d(videoEvent,"onInviteUpdated"+l+"\n"+s+"\n");
    }

    @Override
    public void onInviteConnected(long l) {
        myApplication.setSessionId(l);
        Log.d(videoEvent,"連接OK");
//        Intent intent = new Intent(context, VideoActivity.class);
//        intent.putExtra("id",sessionisd);
////                        Bundle bundle = new Bundle();
////                        bundle.putSerializable("sdk",myApplication);
////                        intent.putExtra("s",bundle);
////                        intent.putExtra("s",myApplication);
////                        Log.d(MY_APPLICATION, "MainActivity put : " + myApplication.toString());
//        startActivity(intent);
        textview.setText("撥打成功");

        myApplication.setGotoVedio(true);
    }

    @Override
    public void onInviteBeginingForward(String s) {
        Log.d(videoEvent,"onInviteBeginingForward");
    }

    @Override
    public void onInviteClosed(long l) {
        Log.d(videoEvent,"onInviteClosed");
    }

    @Override
    public void onRemoteHold(long l) {
        Log.d(videoEvent,"onRemoteHold");
    }

    @Override
    public void onRemoteUnHold(long l, String s, String s1, boolean b, boolean b1) {
        Log.d(videoEvent,"onRemoteUnHold");
    }

    @Override
    public void onReceivedRefer(long l, long l1, String s, String s1, String s2) {
        Log.d(videoEvent,"onReceivedRefer");
    }

    @Override
    public void onReferAccepted(long l) {
        Log.d(videoEvent,"onReferAccepted");
    }

    @Override
    public void onReferRejected(long l, String s, int i) {
        Log.d(videoEvent,"onReferRejected");
    }

    @Override
    public void onTransferTrying(long l) {
        Log.d(videoEvent,"onTransferTrying");
    }

    @Override
    public void onTransferRinging(long l) {
        Log.d(videoEvent,"onTransferRinging");
    }

    @Override
    public void onACTVTransferSuccess(long l) {
        Log.d(videoEvent,"onACTVTransferSuccess");
    }

    @Override
    public void onACTVTransferFailure(long l, String s, int i) {
        Log.d(videoEvent,"onACTVTransferFailure");
    }

    @Override
    public void onReceivedSignaling(long l, String s) {
        Log.d(videoEvent,l+"\n"+s);
    }

    @Override
    public void onSendingSignaling(long l, String s) {
        Log.d(videoEvent,"onSendingSignaling");
    }

    @Override
    public void onWaitingVoiceMessage(String s, int i, int i1, int i2, int i3) {
        Log.d(videoEvent,"onWaitingVoiceMessage");
    }

    @Override
    public void onWaitingFaxMessage(String s, int i, int i1, int i2, int i3) {
        Log.d(videoEvent,"onWaitingFaxMessage");
    }

    @Override
    public void onRecvDtmfTone(long l, int i) {
        Log.d(videoEvent,"onRecvDtmfTone");
    }

    @Override
    public void onRecvOptions(String s) {
        Log.d(videoEvent,"onRecvOptions");
    }

    @Override
    public void onRecvInfo(String s) {
        Log.d(videoEvent,"onRecvInfo");
    }

    @Override
    public void onPresenceRecvSubscribe(long l, String s, String s1, String s2) {
        Log.d(videoEvent,"onPresenceRecvSubscribe");
    }

    @Override
    public void onPresenceOnline(String s, String s1, String s2) {
        Log.d(videoEvent,"onPresenceOnline");
    }

    @Override
    public void onPresenceOffline(String s, String s1) {
        Log.d(videoEvent,"onPresenceOffline");
    }

    @Override
    public void onRecvMessage(long l, String s, String s1, byte[] bytes, int i) {
        Log.d(videoEvent,"onRecvMessage");
    }

    @Override
    public void onRecvOutOfDialogMessage(String s, String s1, String s2, String s3, String s4, String s5, byte[] bytes, int i) {
        Log.d(videoEvent,"onRecvOutOfDialogMessage");
    }

    @Override
    public void onSendMessageSuccess(long l, long l1) {
        Log.d(videoEvent,"onSendMessageSuccess");
    }

    @Override
    public void onSendMessageFailure(long l, long l1, String s, int i) {
        Log.d(videoEvent,"onSendMessageFailure");
    }

    @Override
    public void onSendOutOfDialogMessageSuccess(long l, String s, String s1, String s2, String s3) {
        Log.d(videoEvent,"onSendOutOfDialogMessageSuccess");
    }

    @Override
    public void onSendOutOfDialogMessageFailure(long l, String s, String s1, String s2, String s3, String s4, int i) {
        Log.d(videoEvent,"onSendOutOfDialogMessageFailure");
    }

    @Override
    public void onPlayAudioFileFinished(long l, String s) {
        Log.d(videoEvent,"onPlayAudioFileFinished");
    }

    @Override
    public void onPlayVideoFileFinished(long l) {
        Log.d(videoEvent,"onPlayVideoFileFinished");
    }

    @Override
    public void onReceivedRTPPacket(long l, boolean b, byte[] bytes, int i) {
        Log.d(videoEvent,"onReceivedRTPPacket");
    }

    @Override
    public void onSendingRTPPacket(long l, boolean b, byte[] bytes, int i) {
        Log.d(videoEvent,"onSendingRTPPacket");
    }

    @Override
    public void onAudioRawCallback(long l, int i, byte[] bytes, int i1, int i2) {
        Log.d(videoEvent,"onAudioRawCallback");
    }

    @Override
    public int onVideoRawCallback(long l, int i, int i1, int i2, byte[] bytes, int i3) {
        return 0;
    }

    @Override
    public void onVideoDecodedInfoCallback(long l, int i, int i1, int i2, int i3) {

    }
}


