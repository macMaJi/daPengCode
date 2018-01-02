package com.example.free.mymvpdemo.manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;

import com.blankj.utilcode.util.ToastUtils;
import com.example.free.mymvpdemo.service.AIDLService;
import com.example.free.mymvpdemo.service.MyService;
import com.example.free.mymvpdemo.ui.AIDLActivity;
import com.example.free.mymvpdemo.ui.ChangeTabActivity;
import com.example.free.mymvpdemo.ui.CheckBoxActivity;
import com.example.free.mymvpdemo.ui.CheckBoxDeleteActivity;
import com.example.free.mymvpdemo.ui.EventBusActivity;
import com.example.free.mymvpdemo.ui.GlideActivity;
import com.example.free.mymvpdemo.ui.HandlerOptimizeStaticActivity;
import com.example.free.mymvpdemo.ui.HandlerOptimizeWeakActivity;
import com.example.free.mymvpdemo.ui.HandlerUseActivity;
import com.example.free.mymvpdemo.ui.ImmersionActivity;
import com.example.free.mymvpdemo.ui.JNIActivity;
import com.example.free.mymvpdemo.ui.LightControlActivity;
import com.example.free.mymvpdemo.ui.My9PatchActivity;
import com.example.free.mymvpdemo.ui.OkHttp3Activity;
import com.example.free.mymvpdemo.ui.RecycleBinActivity;
import com.example.free.mymvpdemo.ui.ServiceActivity1;
import com.example.free.mymvpdemo.ui.ServiceActivity2;
import com.example.free.mymvpdemo.ui.TextWatcherActivity;
import com.example.free.mymvpdemo.ui.TimeActivity;
import com.example.free.mymvpdemo.ui.UserDialogActivity;
import com.example.free.mymvpdemo.ui.EventBus1;
import com.example.free.mymvpdemo.ui.EventBus2;

/**
 * Created by free on 2017/7/30.
 */

public class Nav {

    public static void toServiceActivity1(Activity activity) {
        activity.startActivity(new Intent(activity, ServiceActivity1.class));
    }

    public static void toServiceActivity2(Activity activity) {
        activity.startActivity(new Intent(activity, ServiceActivity2.class));
    }

    public static void startMyService(Activity activity) {
        activity.startService(new Intent(activity, MyService.class));
    }

    public static void stopMyService(Activity activity) {
        activity.stopService(new Intent(activity, MyService.class));
    }

    public static void bindMyService(Activity activity, ServiceConnection serviceConnection) {
        MyService.isBind = activity.bindService(new Intent(activity, MyService.class), serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public static void unBindMyService(Activity activity, ServiceConnection serviceConnection) {
        if (MyService.isBind) {
            activity.unbindService(serviceConnection);
            MyService.isBind = false;
        } else {
            ToastUtils.showShort("无控件绑定 MyService ");
        }
    }

    public static void toAIDLActivity(Activity activity) {
        activity.startActivity(new Intent(activity, AIDLActivity.class));
    }

    public static void toJNIActivity(Activity activity) {
        activity.startActivity(new Intent(activity, JNIActivity.class));
    }

    public static void bindAIDLService(Activity activity, ServiceConnection serviceConnection) {
        activity.bindService(new Intent(activity, AIDLService.class), serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public static void toEventBusActivity(Activity activity) {
        activity.startActivity(new Intent(activity, EventBusActivity.class));
    }

    public static void toMy9PatchActivity(Activity activity) {
        activity.startActivity(new Intent(activity, My9PatchActivity.class));
    }

    public static void toEventBus1(Activity activity) {
        activity.startActivity(new Intent(activity, EventBus1.class));
    }

    public static void toEventBus2(Activity activity) {
        activity.startActivity(new Intent(activity, EventBus2.class));
    }

    public static void toTimeActivity(Activity activity) {
        activity.startActivity(new Intent(activity, TimeActivity.class));
    }

    public static void toLightControlActivity(Activity activity) {
        activity.startActivity(new Intent(activity, LightControlActivity.class));
    }

    public static void toCheckBoxActivity(Activity activity) {
        activity.startActivity(new Intent(activity, CheckBoxActivity.class));
    }

    public static void toRecycleBinActivity(Activity activity) {
        activity.startActivity(new Intent(activity, RecycleBinActivity.class));

    }

    public static void toCheckBoxDeleteActivity(Activity activity) {
        activity.startActivity(new Intent(activity, CheckBoxDeleteActivity.class));
    }

    public static void toUserDialogActivity(Activity activity) {
        activity.startActivity(new Intent(activity, UserDialogActivity.class));
    }

    public static void toImmersionActivity(Activity activity) {
        activity.startActivity(new Intent(activity, ImmersionActivity.class));
    }

    public static void toTextWatcherActivity(Activity activity) {
        activity.startActivity(new Intent(activity, TextWatcherActivity.class));
    }


    public static void toChangeTabActivity(Activity activity) {
        activity.startActivity(new Intent(activity, ChangeTabActivity.class));
    }

    public static void toGlideActivity(Activity activity) {
        activity.startActivity(new Intent(activity, GlideActivity.class));
    }


    public static void toOkHttp3Activity(Activity activity) {
        activity.startActivity(new Intent(activity, OkHttp3Activity.class));
    }

    public static void toHandlerUseActivity(Activity activity) {
        activity.startActivity(new Intent(activity, HandlerUseActivity.class));
    }

    public static void toHandlerOptimizeStaticActivity(Activity activity) {
        activity.startActivity(new Intent(activity, HandlerOptimizeStaticActivity.class));
    }

    public static void toHandlerOptimizeWeakActivity(Activity activity) {
        activity.startActivity(new Intent(activity, HandlerOptimizeWeakActivity.class));
    }

}
