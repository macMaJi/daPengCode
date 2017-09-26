package com.example.free.mymvpdemo.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.blankj.utilcode.util.LogUtils;
import com.example.free.mymvpdemo.R;
import com.example.free.mymvpdemo.manager.BaseActivity;
import com.example.free.mymvpdemo.view.ToneLayer;

import java.util.ArrayList;

public class LightControlActivity extends BaseActivity implements SeekBar.OnSeekBarChangeListener {

    private ToneLayer mToneLayer;
    private ImageView mImageView;
    private Bitmap mBitmap;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_light_control;
    }

    @Override
    protected void initView() {
        init();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    private void init() {
        mToneLayer = new ToneLayer(this);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test);
        mImageView = (ImageView) findViewById(R.id.img_view);
        mImageView.setImageBitmap(mBitmap);
        ((LinearLayout) findViewById(R.id.tone_view)).addView(mToneLayer.getParentView());

        ArrayList<SeekBar> seekBars = mToneLayer.getSeekBars();
        for (int i = 0, size = seekBars.size(); i < size; i++) {
            seekBars.get(i).setOnSeekBarChangeListener(this);
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress,
                                  boolean fromUser) {
        int flag = (Integer) seekBar.getTag();
        switch (flag) {
            case ToneLayer.FLAG_SATURATION:
                LogUtils.i("progress, 饱和度:" + progress);
                mToneLayer.setSaturation(progress);
                break;
            case ToneLayer.FLAG_LUM:
                LogUtils.i("progress, 色相:" + progress);
                mToneLayer.setLum(progress);
                break;
            case ToneLayer.FLAG_HUE:
                LogUtils.i("progress, 亮度:" + progress);
                mToneLayer.setHue(progress);
                break;
        }

        mImageView.setImageBitmap(mToneLayer.handleImage(mBitmap, flag));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
