package com.example.free.mymvpdemo.manager;

/**
 * Created by free on 2017/8/8.
 */

import com.blankj.utilcode.util.Utils;

/**
 * 采用单例模式-饿汉式
 */
public class Init {

    private static Init mInit = new Init();

    public static Init getInstance() {
        return mInit;
    }

    private Init() {
        initAndroidUtils();
    }

    /**
     * 初始化AndroidUtils工具类
     */
    private void initAndroidUtils() {

        Utils.init(CodeApplication.applicationContext);
    }
}
