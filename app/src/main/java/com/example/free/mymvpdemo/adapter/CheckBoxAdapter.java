package com.example.free.mymvpdemo.adapter;

import android.support.annotation.LayoutRes;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.free.mymvpdemo.ui.CheckBoxBean;

/**
 * Created by free on 2017/10/29.
 */

public class CheckBoxAdapter extends BaseQuickAdapter<CheckBoxBean, BaseViewHolder> {


    public CheckBoxAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, CheckBoxBean item) {

    }
}
