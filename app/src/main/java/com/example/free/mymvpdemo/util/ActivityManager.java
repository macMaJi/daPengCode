package com.example.free.mymvpdemo.util;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;


public class ActivityManager {


    private static ActivityManager activityManager;
    private List<Activity> activityList = new LinkedList<>();

    private ActivityManager(){

    }

    public static ActivityManager getInstance(){
        if(activityManager == null){
            activityManager = new ActivityManager();
        }
        return activityManager;
    }

    public  List<Activity> getActivityList(){
        return activityList;
    }

    /**
     * 保存Activity到现有列表中
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public void exit() {
//        MobclickAgent.onKillProcess(CodeApplication.applicationContext);
        try {
            for (Activity activity : activityList) {
                if (activity != null)
                    activity.finish();
            }
            activityList = null;
            System.exit(0);
        } catch (Exception e) {
            System.exit(1);
            e.printStackTrace();
        }
    }

    public void exitActivity() {
        for (Activity activity : activityList) {
            if (activity != null && !activity.isFinishing())
                activity.finish();
        }
    }
    public void exitOtherActivity(Class clazz) {
        for (Activity activity : activityList) {
            if (activity != null && !activity.isFinishing()){
                if(!activity.getClass().equals(clazz)){
                    activity.finish();
                    activity = null;
                }
            }
        }
    }



    /**
     * 结束指定的Activity
     */
    public void finishActivity(Class clazz) {
        for(Activity activity:activityList){
            if(activity.getClass().equals(clazz)){
                activity.finish();
                activity = null;
            }
        }
    }

    public void destroyActivity(Activity activity){
        if(activityList.contains(activity)){
            activityList.remove(activity);
            activity = null;
        }
    }


}
