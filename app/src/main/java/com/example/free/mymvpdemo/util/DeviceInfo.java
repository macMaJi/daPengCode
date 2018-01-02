/******************************************************************************
 * PROPRIETARY/CONFIDENTIAL
 * Copyright (c) 2014 QuantGroup
 * <p>
 * All rights reserved. This medium contains confidential and proprietary
 * source code and other information which is the exclusive property of
 * QuantGroup. None of these materials may be used, disclosed, transcribed,
 * stored in a retrieval system, translated into any other language or other
 * computer language, or transmitted in any form or by any means (electronic,
 * mechanical, photocopied, recorded or otherwise) without the prior written
 * permission of QuantGroup.
 *******************************************************************************/
package com.example.free.mymvpdemo.util;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import com.blankj.utilcode.util.NetworkUtils;
import com.example.free.mymvpdemo.bean.ContactsInfo;
import com.example.free.mymvpdemo.manager.CodeApplication;
import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 获取设备信息功能类
 * 功能：该类用于获取设备串码，网络状态等等功能。
 *
 * @author lixinxin
 * @version 1.0.0
 * @since 12.6 2013
 * QQ：251089003
 * PHONE：18611825220
 */
public class DeviceInfo {

    /**
     * 获取手机状态栏高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobiles) {
        /*
        移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		联通：130、131、132、152、155、156、185、186
		电信：133、153、177, 180、189、（1349卫通）
		总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
		*/
        String telRegex = "[1][34578]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) {
            return false;
        } else {
            return mobiles.matches(telRegex);
        }
    }

    /**
     * 获取网络连接状态
     *
     */
    public static boolean isNetworkAvailable() {
        return NetworkUtils.isConnected();
    }

    /**
     * 获取当前程序版本名
     *
     * @param context
     * @return 返回当前程序版本名
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * 获取客户端版本号
     *
     * @param c
     * @return 版本号
     */
    public static int getVersionCode(Context c) {
        PackageInfo pi = null;
        try {
            pi = c.getPackageManager().getPackageInfo(c.getPackageName(),
                    PackageManager.GET_ACTIVITIES);
            return pi.versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取可用存储空间大小 若存在SD卡则返回SD卡剩余空间大小 否则返回手机内存剩余空间大小
     *
     * @return
     */
    public static long getAvailableStorageSpace() {
        long externalSpace = getExternalStorageSpace();
        if (externalSpace == -1L) {
            return getInternalStorageSpace();
        }
        return externalSpace;
    }

    /**
     * 获取SD卡可用空间
     *
     * @return availableSDCardSpace 可用空间(MB)。-1L:没有SD卡
     */
    public static long getExternalStorageSpace() {
        long availableSDCardSpace = -1L;
        // 存在SD卡
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {
            StatFs sf = new StatFs(Environment.getExternalStorageDirectory()
                    .getPath());
            // 块大小,单位byte
            long blockSize = sf.getBlockSize();
            // 可用块数量
            long availCount = sf.getAvailableBlocks();
            // 可用SD卡空间，单位MB
            availableSDCardSpace = availCount * blockSize / 1024 / 1024;
        }
        return availableSDCardSpace;
    }

    /**
     * 获取机器内部可用空间
     *
     * @return availableSDCardSpace 可用空间(MB)。-1L:没有SD卡
     */
    public static long getInternalStorageSpace() {
        long availableInternalSpace = -1L;
        StatFs sf = new StatFs(Environment.getDataDirectory().getPath());
        // 块大小,单位byte
        long blockSize = sf.getBlockSize();
        // 可用块数量
        long availCount = sf.getAvailableBlocks();
        // 可用SD卡空间，单位MB
        availableInternalSpace = availCount * blockSize / 1024 / 1024;
        return availableInternalSpace;
    }

    /**
     * 获取SD卡总空间
     *
     * @return availableSDCardSpace 总空间(MB)。-1L:没有SD卡
     */
    public static long getExternalStorageTotalSpace() {
        long availableSDCardSpace = -1L;
        // 存在SD卡
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {
            StatFs sf = new StatFs(Environment.getExternalStorageDirectory()
                    .getPath());
            // 块大小,单位byte
            long blockSize = sf.getBlockSize();
            // 块总数量
            long blockCount = sf.getBlockCount();
            // 总SD卡空间，单位MB
            availableSDCardSpace = blockCount * blockSize / 1024 / 1024;
        }
        return availableSDCardSpace;
    }

    /**
     * 获取MAC地址
     */
    public static String getLocalMacAddress(Context ctx) {
        WifiManager wifi = (WifiManager) ctx.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        return info.getMacAddress();
    }

    /**
     * 获得国际移动设备身份码
     *
     * @param context
     * @return
     */
    public static String getIMEI(Context context) {

        // 版本判断。当手机系统大于 23 时，才有必要去判断权限是否获取
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE);
            if(checkCallPhonePermission == PackageManager.PERMISSION_GRANTED){
                if (TextUtils.isEmpty(((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId())) {
                    return "";
                }
                return ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
            }else{
                return "";
            }
        } else {
            if (TextUtils.isEmpty(((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId())) {
                return "";
            }
            return ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        }
    }

    public static String getOwnerPhone(Context context) {
            // 版本判断。当手机系统大于 23 时，才有必要去判断权限是否获取
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                int checkCallPhonePermission = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE);
                if(checkCallPhonePermission == PackageManager.PERMISSION_GRANTED){
                    if (TextUtils.isEmpty(((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getLine1Number())) {
                        return "";
                    }
                    return ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getLine1Number();
                }else{
                    return "";
                }
            }else{
                if (TextUtils.isEmpty(((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getLine1Number())) {
                    return "";
                }
                return ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getLine1Number();
            }
    }

    /**
     * 获取ip地址
     * @return
     */
    public static String getHostIP() {

        String hostIp = null;
        try {
            Enumeration nis = NetworkInterface.getNetworkInterfaces();
            InetAddress ia = null;
            while (nis.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) nis.nextElement();
                Enumeration<InetAddress> ias = ni.getInetAddresses();
                while (ias.hasMoreElements()) {
                    ia = ias.nextElement();
                    if (ia instanceof Inet6Address) {
                        continue;// skip ipv6
                    }
                    String ip = ia.getHostAddress();
                    if (!"127.0.0.1".equals(ip)) {
                        hostIp = ia.getHostAddress();
                        break;
                    }
                }
            }
        } catch (SocketException e) {
            ULog.i("yao", "SocketException");
            e.printStackTrace();
        }
        return hostIp;

    }

    /**
     * 获取手机品牌
     * @return
     */
    public static String getPhoneBrand(){
        return Build.BRAND;
    }

    /**
     * 获取手机型号
     *
     * @return
     */
    public static String getPhoneType() {
        return Build.MODEL;
    }

    /**
     * 获得系统版本
     */
    public static String getSystemVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 获取联系人json
     */
    public static String getContactsInfoJson(Context context) {
        Set<ContactsInfo> set = getContactsInfo(context);
        List<ContactsInfo> lists = new ArrayList<>();
        for (ContactsInfo contactsInfo : set) {
            lists.add(contactsInfo);
        }
        Gson gson = new Gson();
        String string = gson.toJson(lists);
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        return string;
    }

    /**
     * 获取联系人Set集合
     */
    public static Set<ContactsInfo> getContactsInfo(Context context) {
        Set<ContactsInfo> set = new HashSet<>();
        set.addAll(getLocalContactsInfos(context));
        set.addAll(getSIMContactsInfos(context));
        return set;
    }

    /**
     * 得到本地联系人
     */
    private static Set<ContactsInfo> getLocalContactsInfos(Context context) {
        Set<ContactsInfo> set = new HashSet<>();
        ContentResolver cr = context.getContentResolver();
        String str[] = {ContactsContract.CommonDataKinds.Phone.CONTACT_ID, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.PHOTO_ID};
        Cursor cur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, str, null, null, null);
        if (cur != null) {
            ContactsInfo contactsInfo = null;
            while (cur.moveToNext()) {
                contactsInfo = new ContactsInfo();
                List<String> phones = new ArrayList<>();
                phones.add(cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                contactsInfo.setPhone(phones);
                contactsInfo.setName(cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));
                set.add(contactsInfo);
            }
            cur.close();
        }
        return set;
    }

    /**
     * 得到sim卡联系人
     */
    private static Set<ContactsInfo> getSIMContactsInfos(Context context) {
        Set<ContactsInfo> set = new HashSet();
        ContentResolver cr = context.getContentResolver();
        final String SIM_URI_ADN = "content://icc/adn";// SIM卡
        Uri uri = Uri.parse(SIM_URI_ADN);
        Cursor cursor = cr.query(uri, null, null, null, null);
        ContactsInfo contactsInfo = null;
        if (cursor != null) {
            while (cursor.moveToNext()) {
                contactsInfo = new ContactsInfo();
                contactsInfo.setName(cursor.getString(cursor.getColumnIndex("name")));
                List<String> phones = new ArrayList<>();
                phones.add(cursor.getString(cursor.getColumnIndex("number")));
                contactsInfo.setPhone(phones);
                set.add(contactsInfo);
            }
            cursor.close();
        }
        return set;
    }

    public static boolean isSoftShowing(Activity activity) {
        //获取当前屏幕内容的高度
        int screenHeight = activity.getWindow().getDecorView().getHeight();
        //获取View可见区域的bottom
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return screenHeight - rect.bottom- getSoftButtonsBarHeight(activity) > 0;
    }

    /**
     * 底部虚拟按键栏的高度
     * @return
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private static int getSoftButtonsBarHeight(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        //这个方法获取可能不是真实屏幕的高度
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int usableHeight = metrics.heightPixels;
        //获取当前屏幕的真实高度
        activity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        int realHeight = metrics.heightPixels;
        if (realHeight > usableHeight) {
            return realHeight - usableHeight;
        } else {
            return 0;
        }
    }

    /**
     * androidid  64位唯一标示
     * @param context
     * @return
     */
    public static String getAndroidId(Context context){
        return Settings.Secure.getString(context.getContentResolver(),Settings.Secure.ANDROID_ID);
    }

    /**
     * 获取uid uid为应用唯一标示
     * @param context
     * @return
     */
    public static String getUId(Context context){
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ApplicationInfo appinfo = context.getApplicationInfo();
        List<ActivityManager.RunningAppProcessInfo> run = am.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo runningProcess : run) {
            if ((runningProcess.processName != null) && runningProcess.processName.equals(appinfo.processName)) {
               return String.valueOf(runningProcess.uid);
            }

        }
        return null;
    }

    /**
     * 获取pid pid为应用进程唯一标示
     * @param context
     * @return
     */
    public static String getPid(Context context){
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ApplicationInfo appinfo = context.getApplicationInfo();
        List<ActivityManager.RunningAppProcessInfo> run = am.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo runningProcess : run) {
            if ((runningProcess.processName != null) && runningProcess.processName.equals(appinfo.processName)) {
                return String.valueOf(runningProcess.pid);
            }
        }
        return null;
    }


    /**
     * 获得国际移动设备身份码
     *
     * @param context
     * @return
     */
    public static String getIMSI(Context context) {

        // 版本判断。当手机系统大于 23 时，才有必要去判断权限是否获取
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE);
            if(checkCallPhonePermission == PackageManager.PERMISSION_GRANTED){
                if (TextUtils.isEmpty(((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getSubscriberId())) {
                    return "";
                }
                return ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getSubscriberId();
            }else{
                return "";
            }
        } else {
            if (TextUtils.isEmpty(((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getSubscriberId())) {
                return "";
            }
            return ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getSubscriberId();
        }
    }

    /**
     * sim序号
     * @return
     */
    public static String getSimSerialNumber() {
        // 版本判断。当手机系统大于 23 时，才有必要去判断权限是否获取
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(CodeApplication.applicationContext, Manifest.permission.READ_PHONE_STATE);
            if(checkCallPhonePermission == PackageManager.PERMISSION_GRANTED){
                if (TextUtils.isEmpty(((TelephonyManager) CodeApplication.applicationContext.getSystemService(
                        Context.TELEPHONY_SERVICE)).getSimSerialNumber())) {
                    return "";
                }
                return ((TelephonyManager) CodeApplication.applicationContext.getSystemService(
                        Context.TELEPHONY_SERVICE)).getSimSerialNumber();
            }else{
                return "";
            }
        } else {
            if (TextUtils.isEmpty(((TelephonyManager) CodeApplication.applicationContext.getSystemService(
                    Context.TELEPHONY_SERVICE)).getSimSerialNumber())) {
                return "";
            }
            return ((TelephonyManager) CodeApplication.applicationContext.getSystemService(
                    Context.TELEPHONY_SERVICE)).getSimSerialNumber();
        }
    }
}
