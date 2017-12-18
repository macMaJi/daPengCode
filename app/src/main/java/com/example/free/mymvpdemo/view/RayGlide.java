package com.example.free.mymvpdemo.view;

import android.content.Context;
import android.widget.ImageView;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.free.mymvpdemo.R;
import com.example.free.mymvpdemo.view.glide.GlideCircleTransform;
import com.example.free.mymvpdemo.view.glide.GlideRoundTransform;

public class RayGlide {


    private static RequestOptions setRequestOptions() {
        RequestOptions options = new RequestOptions();
        options.centerCrop();
        options.placeholder(R.drawable.scan_again);
        options.error(R.drawable.errot);
        options.priority(Priority.HIGH);
        options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        return options;
    }


    //默认加载
    public static void loadPath(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).apply(setRequestOptions()).into(mImageView);
    }

    //默认加载
    public static void loadFromRes(Context mContext, int resId, ImageView mImageView) {
        Glide.with(mContext).load(resId).into(mImageView);
    }

//    //加载圆角图
//    public static void loadRoundImageViewFromResource(Context mContext, int resId, ImageView mImageView) {
//        Glide.with(mContext).load(resId)
//                .transform(new GlideRoundTransform(mContext))
//                .into(mImageView);
//    }

    //加载圆形图
    public static void loadCircleImageFromResource(Context mContext, int resId, ImageView mImageView) {
        Glide.with(mContext).load(resId)
                .into(mImageView);
    }


    public static void loadWithCircle(Context mContext, int errorImageView, String path, ImageView mImageView) {
        LogUtils.i("loadWithDefault:" + path);
        Glide.with(mContext)
                .load(path)
                .into(mImageView);
    }

    //清除磁盘缓存
    public static void GuideClearDiskCache(Context mContext) {
        //理磁盤緩存 需要在子線程中執行
        Glide.get(mContext).clearDiskCache();
    }

    //清除内存缓存
    public static void GuideClearMemory(Context mContext) {
        //清理內存緩存  可以在UI主線程中進行
        Glide.get(mContext).clearMemory();
    }
}
