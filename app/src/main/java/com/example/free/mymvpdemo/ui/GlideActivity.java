package com.example.free.mymvpdemo.ui;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.free.mymvpdemo.R;
import com.example.free.mymvpdemo.manager.BaseActivity;
import com.example.free.mymvpdemo.view.LunaGlide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GlideActivity extends BaseActivity {

    @BindView(R.id.iv_icon)
    ImageView ivIcon;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_glide;
    }

    @Override
    protected void initView() {

        LunaGlide.loadWithCircle(this,
                "https://wx4.sinaimg.cn/crop.0.0.640.640.640/006g0DdOly1fmdxskt6kbj30yi0ykgst.jpg",
                ivIcon);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
