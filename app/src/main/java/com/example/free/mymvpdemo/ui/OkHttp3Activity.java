package com.example.free.mymvpdemo.ui;

import com.example.free.mymvpdemo.R;
import com.example.free.mymvpdemo.manager.BaseActivity;
import com.example.free.mymvpdemo.util.HttpUtils.HttpResult;
import com.example.free.mymvpdemo.util.HttpUtils.RequestManager;

public class OkHttp3Activity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_okhttp3;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        RequestManager.getAuthCodeAndPhoneDouble(this, new HttpResult() {
            @Override
            public void onError(int errorCode, String error) {

            }

            @Override
            public void onResponse(Object response) {

            }
        });
    }
}
