package com.gao.bryan.myportsip_test1;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.portsip.OnPortSIPEvent;
import com.portsip.PortSipEnumDefine;
import com.portsip.PortSipSdk;

import java.io.Serializable;

/**
 * Created by hermes on 2016/12/9.
 */

public class MyApplication extends Application implements Serializable, OnPortSIPEvent {

    public final static String MY_APPLICATION = "Application";
    public long sessionId;
    public boolean GotoVedio = false;

    public void setGotoVedio(boolean gotoVedio) {
        GotoVedio = gotoVedio;
    }

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public MyApplication() {
    }

    public Context mcontext;
    private final static String TAG = MyApplication.class.getSimpleName();

    private static MyApplication myApplication;

    private static PortSipSdk portSipSdk;

    public static MyApplication getInstance() {
        return myApplication;
    }
    public void setportInit(){
        //設定相機
        portSipSdk.setVideoDeviceId(1);
        //視頻方向
        portSipSdk.setVideoOrientation(270);
        //提高畫質setVideoNackStatus
       // portSipSdk.setVideoNackStatus(true);


        //設置分辨率
        portSipSdk.setVideoResolution(352, 288);
        //配置動態視頻編解碼器
        portSipSdk.addVideoCodec(PortSipEnumDefine.ENUM_VIDEOCODEC_H264);
        portSipSdk.addVideoCodec(PortSipEnumDefine.ENUM_VIDEOCODEC_VP8);
        //設置音頻
        portSipSdk.addAudioCodec(PortSipEnumDefine.ENUM_AUDIOCODEC_G729);
        portSipSdk.addAudioCodec(PortSipEnumDefine.ENUM_AUDIOCODEC_PCMU);
        portSipSdk.addAudioCodec(PortSipEnumDefine.ENUM_AUDIOCODEC_PCMA);

    }

    public static PortSipSdk getPortSipSdk() {
        return portSipSdk;
    }

    public void setPortSipSdk(PortSipSdk portSipSdk) {
        this.portSipSdk = portSipSdk;
    }

    public Context getcontext() {
        return this;
    }

//    public Context getMcontext() {
//        return mcontext;
//    }
//
//    public void setMcontext(Context mcontext) {
//        this.mcontext = mcontext;
//    }

    @Override
    public void onCreate() {
        super.onCreate();
//        portSipSdk.setOnPortSIPEvent(this);
        myApplication = this;
        Log.d(MY_APPLICATION, "MyApplication : " + myApplication.toString());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onRegisterSuccess(String s, int i) {

    }

    @Override
    public void onRegisterFailure(String s, int i) {

    }

    @Override
    public void onInviteIncoming(long l, String s, String s1, String s2, String s3, String s4, String s5, boolean b, boolean b1) {

    }

    @Override
    public void onInviteTrying(long l) {

    }

    @Override
    public void onInviteSessionProgress(long l, String s, String s1, boolean b, boolean b1, boolean b2) {

    }

    @Override
    public void onInviteRinging(long l, String s, int i) {

    }

    @Override
    public void onInviteAnswered(long l, String s, String s1, String s2, String s3, String s4, String s5, boolean b, boolean b1) {

    }

    @Override
    public void onInviteFailure(long l, String s, int i) {

    }

    @Override
    public void onInviteUpdated(long l, String s, String s1, boolean b, boolean b1) {

    }

    @Override
    public void onInviteConnected(long l) {

    }

    @Override
    public void onInviteBeginingForward(String s) {

    }

    @Override
    public void onInviteClosed(long l) {

    }

    @Override
    public void onRemoteHold(long l) {

    }

    @Override
    public void onRemoteUnHold(long l, String s, String s1, boolean b, boolean b1) {

    }

    @Override
    public void onReceivedRefer(long l, long l1, String s, String s1, String s2) {

    }

    @Override
    public void onReferAccepted(long l) {

    }

    @Override
    public void onReferRejected(long l, String s, int i) {

    }

    @Override
    public void onTransferTrying(long l) {

    }

    @Override
    public void onTransferRinging(long l) {

    }

    @Override
    public void onACTVTransferSuccess(long l) {

    }

    @Override
    public void onACTVTransferFailure(long l, String s, int i) {

    }

    @Override
    public void onReceivedSignaling(long l, String s) {

    }

    @Override
    public void onSendingSignaling(long l, String s) {

    }

    @Override
    public void onWaitingVoiceMessage(String s, int i, int i1, int i2, int i3) {

    }

    @Override
    public void onWaitingFaxMessage(String s, int i, int i1, int i2, int i3) {

    }

    @Override
    public void onRecvDtmfTone(long l, int i) {

    }

    @Override
    public void onRecvOptions(String s) {

    }

    @Override
    public void onRecvInfo(String s) {

    }

    @Override
    public void onPresenceRecvSubscribe(long l, String s, String s1, String s2) {

    }

    @Override
    public void onPresenceOnline(String s, String s1, String s2) {

    }

    @Override
    public void onPresenceOffline(String s, String s1) {

    }

    @Override
    public void onRecvMessage(long l, String s, String s1, byte[] bytes, int i) {

    }

    @Override
    public void onRecvOutOfDialogMessage(String s, String s1, String s2, String s3, String s4, String s5, byte[] bytes, int i) {

    }

    @Override
    public void onSendMessageSuccess(long l, long l1) {

    }

    @Override
    public void onSendMessageFailure(long l, long l1, String s, int i) {

    }

    @Override
    public void onSendOutOfDialogMessageSuccess(long l, String s, String s1, String s2, String s3) {

    }

    @Override
    public void onSendOutOfDialogMessageFailure(long l, String s, String s1, String s2, String s3, String s4, int i) {

    }

    @Override
    public void onPlayAudioFileFinished(long l, String s) {

    }

    @Override
    public void onPlayVideoFileFinished(long l) {

    }

    @Override
    public void onReceivedRTPPacket(long l, boolean b, byte[] bytes, int i) {

    }

    @Override
    public void onSendingRTPPacket(long l, boolean b, byte[] bytes, int i) {

    }

    @Override
    public void onAudioRawCallback(long l, int i, byte[] bytes, int i1, int i2) {

    }

    @Override
    public int onVideoRawCallback(long l, int i, int i1, int i2, byte[] bytes, int i3) {
        return 0;
    }

    @Override
    public void onVideoDecodedInfoCallback(long l, int i, int i1, int i2, int i3) {

    }

    public boolean isGotoVedio() {
        return GotoVedio;
    }
}
