package com.example.free.mymvpdemo.ui;


import android.view.View;
import android.widget.TextView;

import com.example.free.mymvpdemo.R;
import com.example.free.mymvpdemo.manager.Nav;
import com.example.free.mymvpdemo.manager.BaseActivity;

import butterknife.BindView;

public class EventBusActivity extends BaseActivity {


    @BindView(R.id.click)
    TextView click;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_eventbus;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Nav.toEventBus1(EventBusActivity.this);
            }
        });
    }

}
