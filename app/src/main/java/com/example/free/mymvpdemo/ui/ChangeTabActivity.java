package com.example.free.mymvpdemo.ui;

import android.os.Build;

import android.view.View;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.free.mymvpdemo.R;
import com.example.free.mymvpdemo.adapter.FragmentAdapter;
import com.example.free.mymvpdemo.manager.BaseActivity;
import com.example.free.mymvpdemo.view.PageIndicator.InvitePageIndicator;

import java.util.ArrayList;

public class ChangeTabActivity extends BaseActivity {

    private ArrayList<Fragment> list = null;
    private ViewPager mViewPager;
    private TextView tvAll;
    private TextView tvReceive;
    private int currPosition = 0;
    private InvAllFragment invItemFragment1;
    private InvReceivedFragment invItemFragment2;
    private InvitePageIndicator indicator;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_tab;
    }

    @Override
    public void initView() {
        mViewPager = findView(R.id.viewpager);
        tvAll = findView(R.id.tv_all);
        tvReceive = findView(R.id.tv_received);
        indicator = findView(R.id.indicator);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int statusBarHeight1 = -1;
            //获取status_bar_height资源的ID
            int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                //根据资源ID获取响应的尺寸值
                statusBarHeight1 = getResources().getDimensionPixelSize(resourceId);
            }
            indicator.setPadding(0, statusBarHeight1, 0, 0);
        }
        initViewPager();
    }

    @Override
    public void initListener() {
        indicator.setOnPageChangeListener(new MyViewPageChangeListener());
        indicator.setViewPager(mViewPager, 1);

    }

    @Override
    public void initData() {
    }


    private void initViewPager() {
        list = new ArrayList<>();
        invItemFragment1 = new InvAllFragment();
        invItemFragment2 = new InvReceivedFragment();
        list.add(invItemFragment1);
        list.add(invItemFragment2);
        mViewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), list));
    }


    class MyViewPageChangeListener implements InvitePageIndicator.PageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    break;
                case 1:
                    break;
            }
            currPosition = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    public int getViewPageCurrItem(){
        return mViewPager.getCurrentItem();
    }

}
