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

    public static RequestOptions options = new RequestOptions()


    private static void setRequestOptions(boolean circleTransform) {
        RequestOptions options = new RequestOptions();
        options.centerCrop();
        options.placeholder(R.drawable.scan_again);
        options.error(R.drawable.errot);
        options.priority(Priority.HIGH);
        if (circleTransform) {
            options.transform();

        }
        options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
    }


    //默认加载
    public static void loadPath(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).apply(options).into(mImageView);
    }

    //默认加载
    public static void loadFromRes(Context mContext, int resId, ImageView mImageView) {
        Glide.with(mContext).load(resId).into(mImageView);
    }

    //加载圆角图
    public static void loadRoundImageViewFromResource(Context mContext, int resId, ImageView mImageView) {
        Glide.with(mContext).load(resId)
                .transform(new GlideRoundTransform(mContext))
                .into(mImageView);
    }

    //加载圆形图
    public static void loadCircleImageFromResource(Context mContext, int resId, ImageView mImageView) {
        Glide.with(mContext).load(resId)
                .transform(new GlideCircleTransform(mContext))
                .into(mImageView);
    }

    //加载指定大小的图片
    public static void loadImageViewSize(Context mContext, String path, int width, int height, ImageView mImageView) {
        Glide.with(mContext).load(path).preload(width, height).into(mImageView);
    }

    //设置加载中的默认图片
    public static void loadWithDefault(Context mContext, int errorImageView, String path, ImageView mImageView) {
        LogUtils.i("loadWithDefault:" + path);
        Glide.with(mContext).load(path).placeholder(errorImageView).error(errorImageView).into(mImageView);
    }

    public static void loadWithCircle(Context mContext, int errorImageView, String path, ImageView mImageView) {
        LogUtils.i("loadWithDefault:" + path);
        Glide.with(mContext)
                .load(path)
                .placeholder(errorImageView)
                .error(errorImageView)
                .transform(new GlideCircleTransform(mContext))
                .into(mImageView);
    }

    //设置加载中的默认图片
    public static void loadImageViewLoding(Context mContext, String path, ImageView mImageView, int loadImage, int errorImageView) {
        Glide.with(mContext).load(path).placeholder(loadImage).error(errorImageView).into(mImageView);
    }

    //設置加載中以及加載失敗圖片並且指定大小
    public static void loadImageViewLodingSize(Context mContext, String path, int width, int height, ImageView mImageView, int lodingImage, int errorImageView) {
        Glide.with(mContext).load(path).override(width, height).placeholder(lodingImage).error(errorImageView).into(mImageView);
    }

    //跳过内存缓存
    public static void loadImageViewCache(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).skipMemoryCache(true).into(mImageView);
    }

    //设置下载优先级
    public static void loadImageViewPriority(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).priority(Priority.NORMAL).into(mImageView);
    }


    //设置缓存策略
    public static void loadImageViewDiskCache(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).diskCacheStrategy(DiskCacheStrategy.ALL).into(mImageView);
    }


    //设置加载动画
    public static void loadImageViewAnim(Context mContext, String path, int anim, ImageView mImageView) {
        Glide.with(mContext).load(path).animate(anim).into(mImageView);
    }


    //设置缩略图支持
    public static void loadImageViewThumbnail(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).thumbnail(0.1f).into(mImageView);
    }

    //设置动态转换
    public static void loadImageViewCrop(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).centerCrop().into(mImageView);
    }

    //设置动态Gif图的加载
    public static void loadImageViewDynamicGif(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).asGif().into(mImageView);
    }

    //设置静态Gif图的加载
    public static void loadImageViewStaticGif(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).asBitmap().into(mImageView);
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
