package com.example.free.mymvpdemo.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.example.free.mymvpdemo.R;
import com.example.free.mymvpdemo.adapter.RecycleBinAdapter;
import com.example.free.mymvpdemo.bean.MySection;
import com.example.free.mymvpdemo.bean.RaySectionEntity;
import com.example.free.mymvpdemo.bean.RecycleObject;
import com.example.free.mymvpdemo.manager.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecycleBinActivity extends BaseActivity implements BaseSectionQuickAdapter.RequestLoadMoreListener{


    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private RecycleBinAdapter recycleBinAdapter;
    private List<String> allAdapterData = new ArrayList<>();//所有加载的照片或者视频数据源


    @Override
    protected int getLayoutId() {
        return R.layout.activity_recycle_bin;
    }

    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        recycleBinAdapter = new RecycleBinAdapter(R.layout.item_section_content, R.layout.def_section_head, allAdapterData);
        recycleBinAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mRecyclerView.setAdapter(recycleBinAdapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

        for (int i = 0; i < 8; i++) {

        }
    }

    @Override
    public void onLoadMoreRequested() {

    }

}
