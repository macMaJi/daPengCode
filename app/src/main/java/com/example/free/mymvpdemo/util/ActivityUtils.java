//package com.example.free.mymvpdemo.util;
//
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.app.Application;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//
//import java.lang.ref.WeakReference;
//import java.util.LinkedList;
//import java.util.List;
//
///**
// * Created by free on 2017/12/5.
// */
//
//public class ActivityUtils {
//
//
//    @SuppressLint({"StaticFieldLeak"})
//    private static Application sApplication;
//    public static WeakReference<Activity> sTopActivityWeakRef;
//    public static List<Activity> sActivityList = new LinkedList();
//    private static Application.ActivityLifecycleCallbacks mCallbacks = new Application.ActivityLifecycleCallbacks() {
//        public void onActivityCreated(Activity activity, Bundle bundle) {
//            sActivityList.add(activity);
//            setTopActivityWeakRef(activity);
//        }
//
//        public void onActivityStarted(Activity activity) {
//            setTopActivityWeakRef(activity);
//        }
//
//        public void onActivityResumed(Activity activity) {
//            setTopActivityWeakRef(activity);
//        }
//
//        public void onActivityPaused(Activity activity) {
//        }
//
//        public void onActivityStopped(Activity activity) {
//        }
//
//        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
//        }
//
//        public void onActivityDestroyed(Activity activity) {
//            sActivityList.remove(activity);
//        }
//    };
//
//    private ActivityUtils() {
//        throw new UnsupportedOperationException("u can't instantiate me...");
//    }
//
//    public static void init(@NonNull Application app) {
//        sApplication = app;
//        app.registerActivityLifecycleCallbacks(mCallbacks);
//    }
//
//    public static Application getApp() {
//        if(sApplication != null) {
//            return sApplication;
//        } else {
//            throw new NullPointerException("u should init first");
//        }
//    }
//
//    private static void setTopActivityWeakRef(Activity activity) {
//        if(sTopActivityWeakRef == null || !activity.equals(sTopActivityWeakRef.get())) {
//            sTopActivityWeakRef = new WeakReference(activity);
//        }
//
//    }
//}
