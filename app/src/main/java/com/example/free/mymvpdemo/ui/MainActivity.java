package com.example.free.mymvpdemo.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.ArrayMap;
import android.widget.Button;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;
import com.example.free.mymvpdemo.R;
import com.example.free.mymvpdemo.manager.BaseActivity;
import com.example.free.mymvpdemo.manager.Nav;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @BindView(R.id.button_enter_layout)
    Button buttonEnterLayout;
    @BindView(R.id.button_picasso)
    Button buttonPicasso;
    @BindView(R.id.button_recyclerview)
    Button buttonRecyclerview;
    @BindView(R.id.button_waterfall)
    Button buttonWaterfall;
    @BindView(R.id.enter_handler)
    Button enterHandler;
    @BindView(R.id.serviceControl)
    Button serviceControl;
    @BindView(R.id.aidl_control)
    Button aidlControl;
    @BindView(R.id.jni_control)
    Button jniControl;
    @BindView(R.id.eventBus)
    Button eventBus;
    @BindView(R.id.nine_patch)
    Button ninePatch;
    @BindView(R.id.swipe_recyclerview)
    Button swipeRecyclerview;
    @BindView(R.id.time_util)
    Button timeUtil;
    @BindView(R.id.light_control)
    Button lightControl;
    @BindView(R.id.checkbox)
    Button checkbox;
    @BindView(R.id.recycle_bin)
    Button recycleBin;
    @BindView(R.id.checkbox_delete)
    Button checkboxDelete;
    @BindView(R.id.user_dialog)
    Button userDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
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

    @OnClick(R.id.button_enter_layout)
    public void enterLayout() {
        startActivity(new Intent(MainActivity.this, MyLayoutActivity.class));
    }

    @OnClick(R.id.button_picasso)
    public void enterPicasso() {
        startActivity(new Intent(MainActivity.this, PicassoActivity.class));
    }

    @OnClick(R.id.button_recyclerview)
    public void buttonRecyclerView() {
        startActivity(new Intent(MainActivity.this, RecyclerViewActivity.class));
    }

    @OnClick(R.id.swipe_recyclerview)
    public void swipenRecyclerView() {
        startActivity(new Intent(MainActivity.this, SwipeRecyclerViewActivity.class));
    }

    @OnClick(R.id.button_waterfall)
    public void waterFall() {
        startActivity(new Intent(MainActivity.this, StaggeredGridLayoutActivity.class));
    }

    @OnClick(R.id.enter_handler)
    public void handlerControl() {
        startActivity(new Intent(MainActivity.this, HandlerActivity.class));
    }

    @OnClick(R.id.serviceControl)
    public void serViceControl() {
        Nav.toServiceActivity1(this);
    }

    @OnClick(R.id.aidl_control)
    public void aidlControl() {
        Nav.toAIDLActivity(this);
    }

    @OnClick(R.id.jni_control)
    public void jniControl() {
        Nav.toJNIActivity(this);
    }

    @OnClick(R.id.eventBus)
    public void eventBus() {
        Nav.toEventBusActivity(this);
    }

    @OnClick(R.id.nine_patch)
    public void ninePatch() {
        Nav.toMy9PatchActivity(this);
    }

    @OnClick(R.id.time_util)
    public void timeUtil() {
        Nav.toTimeActivity(this);
    }

    @OnClick(R.id.light_control)
    public void lightControl() {
        Nav.toLightControlActivity(this);
    }

    @OnClick(R.id.checkbox)
    public void checkbox() {
        Nav.toCheckBoxActivity(this);
    }

    @OnClick(R.id.recycle_bin)
    public void recycleBin() {
        Nav.toRecycleBinActivity(this);
    }

    @OnClick(R.id.checkbox_delete)
    public void checkBoxDelete() {
        Nav.toCheckBoxDeleteActivity(this);
    }

    @OnClick(R.id.user_dialog)
    public void toUserDialog() {
        Nav.toUserDialogActivity(this);
    }


    @Override
    public boolean showTitleBar() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        LogUtils.e("hahhaha:" + ActivityUtils.getTopActivity());
    }

}