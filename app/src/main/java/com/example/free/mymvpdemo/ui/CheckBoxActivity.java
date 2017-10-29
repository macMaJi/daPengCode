package com.example.free.mymvpdemo.ui;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.free.mymvpdemo.R;
import com.example.free.mymvpdemo.adapter.CheckBoxAdapter;
import com.example.free.mymvpdemo.manager.BaseActivity;
import com.example.free.mymvpdemo.view.recyclerview.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CheckBoxActivity extends BaseActivity {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private CheckBoxAdapter checkBoxAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_check_box;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    private LinearLayoutManager mLayoutManager;
    @Override
    protected void initData() {
        List<CheckBoxBean> checkBoxBeanList = new ArrayList();
        for (int i = 0; i < 30; i++) {
            CheckBoxBean checkBoxBean = new CheckBoxBean(0, "lixiang" + i);
            checkBoxBeanList.add(checkBoxBean);
        }

        mLayoutManager = new LinearLayoutManager(this);
        checkBoxAdapter = new CheckBoxAdapter(R.layout.checkbox_item);
        initRecyclerData(mLayoutManager);

        checkBoxAdapter.addData(checkBoxBeanList);
    }

    private void initRecyclerData(RecyclerView.LayoutManager layout) {
        recyclerView.setLayoutManager(layout);
        recyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());         // 设置item动画
        recyclerView.setAdapter(checkBoxAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
