package com.example.free.mymvpdemo.adapter;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.free.mymvpdemo.R;
import com.example.free.mymvpdemo.bean.MySection;
import com.example.free.mymvpdemo.bean.RecycleObject;
import com.guanaj.easyswipemenulibrary.EasySwipeMenuLayout;


import java.util.List;

/**
 * Created by free on 2017/11/20.
 */

public class RecycleBinAdapter extends BaseSectionQuickAdapter<MySection, BaseViewHolder> {

    private boolean isCheckboxShow = false;
    private boolean isLeftSwipeOut = true;
    private int checkboxNum = 0;

    public RecycleBinAdapter(int layoutResId, int sectionHeadResId, List data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, final MySection item) {
        helper.setText(R.id.tv_left_time, item.header);
        helper.setVisible(R.id.cb_head, isCheckboxShow);
        helper.setChecked(R.id.cb_head, item.t.getChecked() == 1 ? true : false);
    }

    @Override
    protected void convert(BaseViewHolder helper, MySection item) {
        RecycleObject video = item.t;
        helper.setText(R.id.tv_time, video.getFileDeleteTime());
        helper.setVisible(R.id.cb_content, isCheckboxShow);
        helper.setChecked(R.id.cb_content, video.getChecked() == 1 ? true : false);
        if (video.getChecked() == 1) {
            checkboxNum++;
        } else {
            checkboxNum--;
        }
        ((EasySwipeMenuLayout) helper.getView(R.id.com_swipe)).setCanLeftSwipe(isLeftSwipeOut);
    }
    public void setCheckBoxShow(boolean checkboxShow) {
        isCheckboxShow = checkboxShow;
    }
    public void setCanLeftSwipe(boolean isLeftSwipe) {
        isLeftSwipeOut = isLeftSwipe;
    }
    public int getCheckNum() {
        return checkboxNum;
    }
}
