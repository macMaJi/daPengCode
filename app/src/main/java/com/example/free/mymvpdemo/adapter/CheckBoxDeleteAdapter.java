package com.example.free.mymvpdemo.adapter;

import android.support.annotation.LayoutRes;

import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.free.mymvpdemo.R;
import com.example.free.mymvpdemo.ui.CheckBoxBean;

/**
 * Created by free on 2017/10/29.
 */

public class CheckBoxDeleteAdapter extends BaseQuickAdapter<CheckBoxBean, BaseViewHolder> {

    private boolean isCheckBoxVisible = false;  //默认是不显示的


    public CheckBoxDeleteAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, CheckBoxBean item) {
        helper.setVisible(R.id.cb_item, isCheckBoxVisible);
        helper.setChecked(R.id.cb_item, item.isChecked == 0 ? false : true);
        helper.setText(R.id.tv_item, item.name);
        LogUtils.e("第几个条目:" + helper.getAdapterPosition() + ", checkStatus:" + item.isChecked);
    }

    public void setCheckBoxVisibleListener(CheckboxVisibleListener checkboxVisibleListener) {
        isCheckBoxVisible = !isCheckBoxVisible;
        notifyDataSetChanged();
        if (checkboxVisibleListener != null) {
            checkboxVisibleListener.checkBoxGlobalPosition(isCheckBoxVisible);
        }
    }

    public interface CheckboxVisibleListener {
        void checkBoxGlobalPosition(boolean isCheckBoxVisible);
    }

}
