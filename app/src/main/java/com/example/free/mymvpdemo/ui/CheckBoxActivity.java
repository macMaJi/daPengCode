package com.example.free.mymvpdemo.ui;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.free.mymvpdemo.R;
import com.example.free.mymvpdemo.adapter.CheckBoxAdapter;
import com.example.free.mymvpdemo.manager.BaseActivity;
import com.example.free.mymvpdemo.view.TitleBar;
import com.example.free.mymvpdemo.view.recyclerview.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CheckBoxActivity extends BaseActivity {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private CheckBoxAdapter checkBoxAdapter;
    private List<CheckBoxBean> checkBoxBeanList;
    private boolean isSingleSelect = true;


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
                if (isSingleSelect) {
                    for (int i = 0; i < checkBoxAdapter.getData().size(); i++) {
                        if (i == position) {
                            if (checkBoxAdapter.getData().get(position).isChecked == 0) {
                                checkBoxAdapter.getData().get(position).isChecked = 1;
                            } else {
                                checkBoxAdapter.getData().get(position).isChecked = 0;
                            }
                            continue;
                        }
                        checkBoxAdapter.getData().get(i).isChecked = 0;
                    }
                    checkBoxAdapter.notifyDataSetChanged();
                } else {
                    if (checkBoxBeanList.get(position).isChecked == 0) {
                        checkBoxBeanList.get(position).isChecked = 1;
                    } else {
                        checkBoxBeanList.get(position).isChecked = 0;
                    }
                    checkBoxAdapter.notifyItemChanged(position);
                }
            }
        });
    }

    private LinearLayoutManager mLayoutManager;

    @Override
    protected void initData() {
        checkBoxBeanList = new ArrayList();
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

    private void setCheckBoxVisible() {
        checkBoxAdapter.setCheckBoxVisibleListener(new CheckBoxAdapter.CheckboxVisibleListener() {
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
