package com.example.free.mymvpdemo.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.free.mymvpdemo.R;
import com.example.free.mymvpdemo.bean.MultipleItem;

import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 * modify by AllenCoder
 */
public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {

    public MultipleItemQuickAdapter(Context context, List data) {
        super(data);
        addItemType(MultipleItem.ONE_TEXT, R.layout.item_text_view);
        addItemType(MultipleItem.ONE_IMAGE, R.layout.item_img_view);
        addItemType(MultipleItem.TWO_IMAGE, R.layout.item_two_image_view);
        addItemType(MultipleItem.THREE_IMAGE, R.layout.item_img_text_view);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        switch (helper.getItemViewType()) {
            case MultipleItem.ONE_TEXT:
                helper.setText(R.id.tv, item.getContent());
                break;
            case MultipleItem.ONE_IMAGE:
                helper.setImageResource(R.id.iv, R.drawable.animation_img1);
                break;
            case MultipleItem.TWO_IMAGE:
                switch (helper.getLayoutPosition() % 2) {
                    case 0:
                        helper.setImageResource(R.id.iv, R.drawable.animation_img1);
                        helper.setImageResource(R.id.iv1, R.drawable.animation_img2);
                        break;
                    case 1:
                        helper.setImageResource(R.id.iv1, R.drawable.animation_img1);
                        helper.setImageResource(R.id.iv, R.drawable.animation_img2);
                        break;

                }
                break;
            case MultipleItem.THREE_IMAGE:
                helper.setImageResource(R.id.iv_head, R.drawable.animation_img1);
                helper.setImageResource(R.id.iv, R.drawable.animation_img2);
                helper.setText(R.id.tv, item.getContent());
                break;
        }
    }

}
