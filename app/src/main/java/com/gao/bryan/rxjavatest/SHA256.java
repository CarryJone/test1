package com.gao.bryan.rxjavatest;

import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by bryan on 2017/9/18.
 */

public class SHA256 {

    private static final String TAG = SHA256.class.getSimpleName();

//    public static String getDataHash(String Gkey){
//        //得到毫秒数
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
//
//        Date curDate = new Date();
//        String TimeStamp = sdf.format(curDate);
//        Log.d(TAG, "getDataHash: "+TimeStamp);
//        String dataStructure = Gkey + TimeStamp;
//
//        MessageDigest shaCode = null;
//        try {
//            shaCode = MessageDigest.getInstance("SHA-256");
//            shaCode.update(dataStructure.getBytes("UTF-8"));
//            System.out.println("dataStructure="+dataStructure);
//        }
//        catch(Exception e) {
//            e.printStackTrace();
//            return "";
//        }
//        byte[] data = shaCode.digest(dataStructure.getBytes(StandardCharsets.UTF_8));
//        Log.d(TAG, "getDataHash: "+data);
//        return byte2Hex(shaCode.digest());
//
//    }
//
//    private static String byte2Hex(byte[] data) {
//        String hexString = "";
//        String stmp = "";
//
//        for(int i = 0; i < data.length; i++) {
//            stmp = Integer.toHexString(data[i] & 0XFF);
////            stmp = String.format("%02x", data[i] & 0xFF);
//
//            if(stmp.length() == 1) {
//                hexString = hexString + "0" + stmp;
//            }
//            else {
//                hexString = hexString + stmp;
//            }
//        }
//        return hexString.toUpperCase();
//    }

    public static String shaEncrypt(String strSrc) {
        MessageDigest md = null;
        String strDes = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
        Date curDate = new Date();
        String TimeStamp = sdf.format(curDate);
        Log.d(TAG, "getDataHash: "+TimeStamp);

        String dataStructure = strSrc + TimeStamp;



        byte[] bt = dataStructure.getBytes();
        try {
            md = MessageDigest.getInstance("SHA-256");// 將此換成SHA-1、SHA-512、SHA-384等參數
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }

    /**
     * byte數組轉換為16進制字符串
     *
     * @param bts
     *            數據源
     * @return 16進制字符串
     */
    public static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }
}
