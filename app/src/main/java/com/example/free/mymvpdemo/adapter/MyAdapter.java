package com.example.free.mymvpdemo.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.free.mymvpdemo.R;
import com.guanaj.easyswipemenulibrary.EasySwipeMenuLayout;

import java.util.List;

/**
 * Created by free on 2017/8/23.
 */

public class MyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public MyAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(final BaseViewHolder helper, String item) {
        EasySwipeMenuLayout easySwipeMenuLayout = helper.getView(R.id.es);
        easySwipeMenuLayout.resetStatus();
        helper.getView(R.id.right_menu_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EasySwipeMenuLayout easySwipeMenuLayout = helper.getView(R.id.es);
                easySwipeMenuLayout.resetStatus();
            }
        });
        helper.getView(R.id.content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
