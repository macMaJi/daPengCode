package com.example.free.mymvpdemo.ui;

import android.os.Bundle;
import android.widget.TextView;

import com.example.free.mymvpdemo.R;
import com.example.free.mymvpdemo.manager.BaseActivity;
import com.example.free.mymvpdemo.util.TimeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TimeActivity extends BaseActivity {

    @BindView(R.id.tv_time)
    TextView tvTime;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_time;
    }

    @Override
    protected void initView() {
        String timeString = "Fri Sep 11 17:19:59 +0800 2017";
        tvTime.setText(TimeUtils.getMicroBlogShowTime(timeString));
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

}
