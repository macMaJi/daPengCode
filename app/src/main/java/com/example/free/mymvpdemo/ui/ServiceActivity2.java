package com.example.free.mymvpdemo.ui;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.widget.Button;

import com.blankj.utilcode.util.LogUtils;
import com.example.free.mymvpdemo.R;
import com.example.free.mymvpdemo.manager.Nav;
import com.example.free.mymvpdemo.manager.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 测试多个activity启动同一个Service出现的问题
 */
public class ServiceActivity2 extends BaseActivity {

    @BindView(R.id.start_service)
    Button startService;
    @BindView(R.id.stop_service)
    Button stopService;
    @BindView(R.id.bind_service)
    Button bindService;
    @BindView(R.id.unbind_service)
    Button unbindService;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_service2;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.start_service)
    public void startService() {
        Nav.startMyService(ServiceActivity2.this);
    }


    @OnClick(R.id.stop_service)
    public void stopService() {
        Nav.stopMyService(ServiceActivity2.this);
    }


    @OnClick(R.id.bind_service)
    public void bindService() {
        Nav.bindMyService(ServiceActivity2.this, serviceConnection);
    }


    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogUtils.e("componentName:" + name + ", service:" + service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            LogUtils.e("componentName:" + name);
        }
    };

    @OnClick(R.id.unbind_service)
    public void unBindService() {
        Nav.unBindMyService(ServiceActivity2.this, serviceConnection);
    }

    @Override
    protected void onDestroy() {
        LogUtils.e("onDestroy");
        super.onDestroy();

        //不添加下面的方法的话 在onDestroy的时候会引起内存泄漏
        Nav.unBindMyService(ServiceActivity2.this, serviceConnection);
    }
}
