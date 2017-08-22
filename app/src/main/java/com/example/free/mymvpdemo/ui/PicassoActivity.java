package com.example.free.mymvpdemo.ui;

import android.widget.ImageView;

import com.example.free.mymvpdemo.R;
import com.example.free.mymvpdemo.manager.BaseActivity;
import com.squareup.picasso.Picasso;

public class PicassoActivity extends BaseActivity {

    private ImageView imageView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_picasso;
    }

    @Override
    protected void initView() {
        imageView = ((ImageView) findViewById(R.id.imageView));
//        Picasso.with(this).load("http://img2.ph.126.net/cuD4cgBVIa3CWAy0IlTi1A==/6608759774282909753.jpg").into(imageView);
        Picasso.with(this).load("http://www.qiwen007.com/images/image/2017/0624/6363392528166483188259433.jpg").into(imageView);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }


}
