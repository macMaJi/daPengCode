package com.example.free.mymvpdemo.ui;

import android.database.ContentObserver;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.free.mymvpdemo.R;
import com.example.free.mymvpdemo.manager.BaseActivity;
import com.gyf.barlibrary.ImmersionBar;
import com.gyf.barlibrary.OSUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 沉浸式
 */
public class ImmersionActivity extends BaseActivity {

    private ImmersionBar immersionBar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_immersion;
    }

    @Override
    protected void initView() {

        immersionBar = ImmersionBar.with(this);
        immersionBar.init();
        if (OSUtils.isEMUI3_1()) {
            getContentResolver().registerContentObserver(
                    Settings.System.getUriFor("navigationbar_is_min"),
                    true, mNavigationStatusObserver);
        }
        if (OSUtils.isFlymeOS()) {
            FlyMeSetStatusBarLightMode(getWindow(), true);
        } else if (OSUtils.isMIUI()) {
            MIUISetStatusBarLightMode(getWindow(), true);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    public static boolean FlyMeSetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            try {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                window.setAttributes(lp);
                result = true;
            } catch (Exception e) {

            }
        }
        return result;
    }

    private ContentObserver mNavigationStatusObserver = new ContentObserver(new Handler()) {
        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            int navigationBarIsMin = Settings.System.getInt(getContentResolver(),
                    "navigationbar_is_min", 0);
            if (navigationBarIsMin == 1) {
                //导航键隐藏了
                immersionBar.transparentNavigationBar().init();
            } else {
                //导航键显示了
                immersionBar.navigationBarColor(android.R.color.black)
                        .fullScreen(false)
                        .init();
            }
        }
    };

    public static boolean MIUISetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            Class clazz = window.getClass();
            try {
                int darkModeFlag = 0;
                Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                darkModeFlag = field.getInt(layoutParams);
                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                if (dark) {
                    extraFlagField.invoke(window, darkModeFlag, darkModeFlag);//状态栏透明且黑色字体
                } else {
                    extraFlagField.invoke(window, 0, darkModeFlag);//清除黑色字体
                }
                result = true;
            } catch (Exception e) {

            }
        }
        return result;
    }
}
