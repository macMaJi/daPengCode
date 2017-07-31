package com.example.free.mymvpdemo.ui;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.example.free.mymvpdemo.R;
import com.example.free.mymvpdemo.service.MyService;

import butterknife.BindView;
import butterknife.OnClick;

public class ServiceActivity extends BaseActivity {

    @BindView(R.id.textView)
    TextView textView;
    private boolean isBind;

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
        return R.layout.activity_service;
    }

    @Override
    protected void initData() {
        textView.setText("serviceActivity1");
    }

    @OnClick(R.id.start_service)
    public void startService() {
        startService(new Intent(ServiceActivity.this, MyService.class));
    }


    @OnClick(R.id.stop_service)
    public void stopService() {
        stopService(new Intent(ServiceActivity.this, MyService.class));
    }


    @OnClick(R.id.bind_service)
    public void bindService() {
        isBind = bindService(new Intent(ServiceActivity.this, MyService.class), serviceConnection, BIND_AUTO_CREATE);
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
        if (isBind) {
            unbindService(serviceConnection);
        }
    }


    public void jumpAnotherActivity(View view) {
        startActivity(new Intent(this, ServiceActivity2.class));
    }
}
