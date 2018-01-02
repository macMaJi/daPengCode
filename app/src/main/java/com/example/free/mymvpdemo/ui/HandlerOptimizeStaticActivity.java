package com.example.free.mymvpdemo.ui;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.free.mymvpdemo.R;
import com.example.free.mymvpdemo.manager.BaseActivity;

import java.lang.ref.WeakReference;

/**
 * handler的优化方式
 */
public class HandlerOptimizeStaticActivity extends BaseActivity {

    private final MyHandler handler = new MyHandler(this);

    /**
     * 非静态内部类和匿名内部类会隐式的持有外部类的引用
     * 如果其实例的生命周期大于外部类对象的生命周期(比如随手new Runnable对象)
     * 就有可能导致内存泄漏
     */
    private static class MyHandler extends Handler {
        private final WeakReference<HandlerOptimizeStaticActivity> activityWeakReference;

        public MyHandler(HandlerOptimizeStaticActivity activity) {
            activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            HandlerOptimizeStaticActivity activity = activityWeakReference.get();
            if (activity != null) {
                switch (msg.what) {
                    case 0x001:
                        activity.finish();
                        break;
                }
            }
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_handler_optimize_static;
    }

    @Override
    protected void initView() {
        handler.sendEmptyMessageDelayed(0x001, 1000);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }
}
