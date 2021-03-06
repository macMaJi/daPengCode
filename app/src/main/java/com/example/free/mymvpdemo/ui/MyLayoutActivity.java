package com.example.free.mymvpdemo.ui;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.free.mymvpdemo.R;
import com.example.free.mymvpdemo.manager.BaseActivity;
import com.example.free.mymvpdemo.view.MyLayout;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;

public class MyLayoutActivity extends BaseActivity {

    private MyLayout mylayout;
    private Button button1;
    private Button button2;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_layout;
    }

    @Override
    protected void initView() {

        mylayout = ((MyLayout) findViewById(R.id.my_layout));
        button1 = ((Button) findViewById(R.id.button1));
        button2 = ((Button) findViewById(R.id.button2));

        mylayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("TAG","myLayout on touch");
                return false;
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG","You clicked button1");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG","You clicked button2");
            }
        });

        HashMap hashMap = new HashMap();
        Hashtable hashtable = new Hashtable();

        HashSet hashSet = new HashSet();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }
}
