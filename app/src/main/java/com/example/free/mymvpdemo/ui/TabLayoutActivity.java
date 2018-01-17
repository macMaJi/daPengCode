package com.example.free.mymvpdemo.ui;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.free.mymvpdemo.R;
import com.example.free.mymvpdemo.adapter.MyAdapter;
import com.example.free.mymvpdemo.manager.BaseActivity;
import com.example.free.mymvpdemo.ui.fragment.LabelFragment;
import com.example.free.mymvpdemo.ui.fragment.TrackFragment;
import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
import com.shizhefei.view.viewpager.SViewPager;

import java.util.ArrayList;

public class TabLayoutActivity extends BaseActivity {

    private IndicatorViewPager indicatorViewPager;
    private View centerView;
    private FixedIndicatorView indicator;
    private ArrayList<Fragment> fragmentArrayList = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.activity_tab_layout;
    }

    @Override
    protected void initView() {

        SViewPager viewPager = (SViewPager) findViewById(R.id.tabmain_viewPager);
        indicator = (FixedIndicatorView) findViewById(R.id.tabmain_indicator);
        indicator.setOnTransitionListener(new OnTransitionTextListener().setColor(Color.RED, Color.GRAY));

        indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
        indicatorViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        // 禁止viewpager的滑动事件
        viewPager.setCanScroll(false);
        // 设置viewpager保留界面不重新加载的页面数量
        viewPager.setOffscreenPageLimit(2);

        LabelFragment labelFragment = new LabelFragment();
        TrackFragment trackFragment = new TrackFragment();
        fragmentArrayList.add(labelFragment);
        fragmentArrayList.add(trackFragment);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    private class MyAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {

        private String[] tabNames = {"标签", "足迹"};
        private LayoutInflater inflater;

        public MyAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            inflater = LayoutInflater.from(getApplicationContext());
        }

        @Override
        public int getCount() {
            return tabNames.length;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.tab_main, container, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(tabNames[position]);
            return textView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            return fragmentArrayList.get(position);
        }
    }
}
