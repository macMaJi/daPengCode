package com.example.free.mymvpdemo.ui;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.free.mymvpdemo.R;
import com.example.free.mymvpdemo.adapter.CheckBoxAdapter;
import com.example.free.mymvpdemo.adapter.CheckBoxDeleteAdapter;
import com.example.free.mymvpdemo.manager.BaseActivity;
import com.example.free.mymvpdemo.view.TitleBar;
import com.example.free.mymvpdemo.view.recyclerview.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CheckBoxDeleteActivity extends BaseActivity {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.addData)
    Button addData;
    @BindView(R.id.setNewData)
    Button setNewData;

    private CheckBoxDeleteAdapter checkBoxAdapter;
    private List<CheckBoxBean> checkBoxBeanList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_check_box;
    }

    @Override
    protected void initView() {
        setRightBtn("显示和隐藏");
    }

    @Override
    protected void initListener() {
        setRightClick(new TitleBar.TitleBarRightClick() {
            @Override
            public void onRightClick(View v) {
                ToastUtils.showShort("点击了显示");
                setCheckBoxVisible();
            }
        });

        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (checkBoxBeanList.get(position).isChecked == 0) {
                    checkBoxBeanList.get(position).isChecked = 1;
                } else {
                    checkBoxBeanList.get(position).isChecked = 0;
                }
                checkBoxAdapter.notifyItemChanged(position);
            }
        });

        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Iterator<CheckBoxBean> iterator = checkBoxBeanList.iterator();
//                while (iterator.hasNext()) {
//                    checkBoxBeanList.remove(3);
//                }
                for (int i = 0; i < checkBoxBeanList.size(); i++) {

                }

                checkBoxAdapter.notifyDataSetChanged();
            }
        });


    }

    private void removeData() {

    }

    private LinearLayoutManager mLayoutManager;

    @Override
    protected void initData() {
        checkBoxBeanList = new ArrayList();
        for (int i = 0; i < 7; i++) {
            CheckBoxBean checkBoxBean = new CheckBoxBean(0, "lixiang" + i);
            checkBoxBeanList.add(checkBoxBean);
        }
        mLayoutManager = new LinearLayoutManager(this);
        checkBoxAdapter = new CheckBoxDeleteAdapter(R.layout.checkbox_item);
        initRecyclerData(mLayoutManager);
        checkBoxAdapter.addData(checkBoxBeanList);
    }

    private void initRecyclerData(RecyclerView.LayoutManager layout) {
        recyclerView.setLayoutManager(layout);
        recyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());         // 设置item动画
        recyclerView.setAdapter(checkBoxAdapter);
    }

    private void setCheckBoxVisible() {
        checkBoxAdapter.setCheckBoxVisibleListener(new CheckBoxDeleteAdapter.CheckboxVisibleListener() {
            @Override
            public void checkBoxGlobalPosition(boolean isCheckBoxVisible) {
                if (!isCheckBoxVisible) {
                    for (CheckBoxBean checkBoxBean : checkBoxBeanList) {
                        checkBoxBean.isChecked = 0;
                    }
                }
            }
        });


    }
}
