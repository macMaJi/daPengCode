package com.example.free.mymvpdemo.ui;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.free.mymvpdemo.R;
import com.example.free.mymvpdemo.manager.BaseActivity;
import com.example.free.mymvpdemo.view.RayGlide;

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

        RayGlide.loadWithCircle(this,
                "http://g.hiphotos.baidu.com/image/pic/item/4610b912c8fcc3ce4d83cff99b45d688d53f20bc.jpg",
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
