package com.example.free.mymvpdemo.ui;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.free.mymvpdemo.R;
import com.example.free.mymvpdemo.adapter.MultipleItemQuickAdapter;
import com.example.free.mymvpdemo.bean.MultipleItem;
import com.example.free.mymvpdemo.manager.BaseActivity;

import java.util.ArrayList;
import java.util.List;


public class MultipleItemUseActivity extends BaseActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_multiple_item_use;
    }

    @Override
    protected void initView() {
        setTitle("多种布局的使用方式");
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        final List<MultipleItem> data = getMultipleItemData();
        final MultipleItemQuickAdapter multipleItemAdapter = new MultipleItemQuickAdapter(this, data);
        final GridLayoutManager manager = new GridLayoutManager(this, 4);
        mRecyclerView.setLayoutManager(manager);
        multipleItemAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return data.get(position).getSpanSize();
            }
        });
        mRecyclerView.setAdapter(multipleItemAdapter);
    }

    @Override
    protected void initData() {

    }

    public static List<MultipleItem> getMultipleItemData() {
        List<MultipleItem> list = new ArrayList<>();
        for (int i = 0; i <= 4; i++) {
            list.add(new MultipleItem(MultipleItem.ONE_TEXT, MultipleItem.IMG_TEXT_SPAN_SIZE, "这是理想"));
            list.add(new MultipleItem(MultipleItem.ONE_IMAGE, MultipleItem.IMG_TEXT_SPAN_SIZE, "这是理想"));
            list.add(new MultipleItem(MultipleItem.TWO_IMAGE, MultipleItem.IMG_TEXT_SPAN_SIZE));
            list.add(new MultipleItem(MultipleItem.THREE_IMAGE, MultipleItem.IMG_TEXT_SPAN_SIZE, "这是第三条数据"));
        }

        return list;
    }

    @Override
    protected void initListener() {

    }
}
