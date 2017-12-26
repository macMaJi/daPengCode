package com.example.free.mymvpdemo.ui;

import android.os.Bundle;
import android.widget.TextView;

import com.example.free.mymvpdemo.R;
import com.example.free.mymvpdemo.manager.BaseActivity;
import com.example.free.mymvpdemo.manager.Nav;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HandlerActivity extends BaseActivity {

    @BindView(R.id.tv_handler_use)
    TextView tvHandlerUse;
    @BindView(R.id.tv_handler_optimize1)
    TextView tvHandlerOptimize1;
    @BindView(R.id.tv_handler_optimize2)
    TextView tvHandlerOptimize2;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_handler;
    }

    @Override
    protected void initView() {
        setTitle(R.string.handler_use_optimize);
    }

    @Override
    protected void initListener() {
    }

    @Override
    protected void initData() {
    }

    @OnClick(R.id.tv_handler_use)
    public void toUse() {
        Nav.toHandlerUseActivity(this);
    }

    @OnClick(R.id.tv_handler_optimize1)
    public void toStatic() {
        Nav.toHandlerOptimizeStaticActivity(this);
    }

    @OnClick(R.id.tv_handler_optimize2)
    public void toWeak() {
        Nav.toHandlerOptimizeWeakActivity(this);
    }

}
