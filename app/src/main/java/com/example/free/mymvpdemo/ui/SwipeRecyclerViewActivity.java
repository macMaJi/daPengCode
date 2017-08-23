package com.example.free.mymvpdemo.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.free.mymvpdemo.R;
import com.example.free.mymvpdemo.adapter.MyAdapter;
import com.example.free.mymvpdemo.helper.Nav;
import com.example.free.mymvpdemo.manager.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class SwipeRecyclerViewActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private List<String> listData;
    private Button tipDialog;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_recycler_view;
    }

    @Override
    protected void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        tipDialog = ((Button) findViewById(R.id.button_change));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter(R.layout.item_rv_swipemenu, null);
        recyclerView.setAdapter(myAdapter);
    }

    /*
    * 下面测试点击RecyclerView的空白区域 回应点击事件
    * */

    @Override
    protected void initListener() {
        tipDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Nav.toServiceActivity2(SwipeRecyclerViewActivity.this);
            }
        });
    }
    protected void initData() {
        listData = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            listData.add("index is =" + i);
        }
        myAdapter.addData(listData);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onPause() {
        super.onPause();
        myAdapter.notifyDataSetChanged();
    }
}
