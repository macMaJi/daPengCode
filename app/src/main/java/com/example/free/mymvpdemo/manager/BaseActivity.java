package com.example.free.mymvpdemo.manager;

import android.app.ProgressDialog;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.LogUtils;
import com.example.free.mymvpdemo.R;
import com.example.free.mymvpdemo.util.ActivityManager;
import com.example.free.mymvpdemo.util.HttpUtils.HttpUtils;
import com.example.free.mymvpdemo.util.UDialog;
import com.example.free.mymvpdemo.view.TitleBar;

import butterknife.ButterKnife;


/**
 * 功能：封装头部和沉浸式功能，方便统一管理activity
 */
public abstract class BaseActivity extends AppCompatActivity {


    public String TAG = getClass().getSimpleName();
    private LinearLayout mRootView;
    private TitleBar mTitleBar;
    private FrameLayout mlayoutView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (showTitleBar()) {
            setContentView(R.layout.activity_base);
            mRootView = ((LinearLayout) findViewById(R.id.root_view));
            mTitleBar = (TitleBar)findViewById(R.id.titleBar);
            mlayoutView = ((FrameLayout) findViewById(R.id.layout_view));
            mlayoutView.addView(getLayout());

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                int statusBarHeight1 = -1;
                //获取status_bar_height资源的ID
                int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
                if (resourceId > 0) {
                    //根据资源ID获取响应的尺寸值
                    statusBarHeight1 = getResources().getDimensionPixelSize(resourceId);
                }
                mTitleBar.setPadding(0, statusBarHeight1, 0, 0);
            }

            //实现沉浸式功能
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                // 透明状态栏
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }

        } else {
            setContentView(getLayoutId());
        }
        ButterKnife.bind(this);
        LogUtils.w("---------------------------  " + TAG + "  ---------------------------");
        isVisible = true;
        initView(savedInstanceState);
        initData();
        initListener();
        ActivityManager.getInstance().addActivity(this);
    }

    public void initView(Bundle savedInstanceState){
        initView();
    }

    /**
     * 是否显示头部
     * 不显示的话，直接采用getLayoutId中的页面
     *
     * @return
     */
    public boolean showTitleBar() {
        return true;
    }


    /**
     * 设置右边按钮
     *
     * @param resId
     */
    public void setRightBtn(int resId) {
        if (showTitleBar()) {
            mTitleBar.setRightBtnVisibility(View.VISIBLE);
            mTitleBar.setRightBtnIcon(resId);
        }
    }

    /**
     * title右侧按钮点击
     *
     * @param leftClick
     */
    public void setRightClick(TitleBar.TitleBarRightClick leftClick) {
        if (showTitleBar()) {
            mTitleBar.setRightClick(leftClick);
        }
    }

    /**
     * 设置右边按钮
     *
     * @param rightText
     */
    public void setRightBtn(String rightText) {
        if (showTitleBar()) {
            mTitleBar.setRightBtnVisibility(View.VISIBLE);
            mTitleBar.setRightBtnText(rightText);
        }
    }

    /**
     * 加载布局文件
     *
     * @return
     */
    protected abstract int getLayoutId();


    public View getLayout() {
        View v = LayoutInflater.from(this).inflate(getLayoutId(), mRootView, false);
        v.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return v;
    }


    /**
     * 初始化组件
     */
    protected abstract void initView();

    /**
     * 添加监听之间
     */
    protected abstract void initListener();

    /**
     * 初始化数据
     */
    protected abstract void initData();




    //--------------------------Activity工具 --------------------------------------//

    /**
     * findViewbyId封装，省去强转
     *
     * @param id  组件id
     * @param <T> 组件类型
     * @return 组件
     */
    public <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }

    private boolean isVisible;
    private ProgressDialog waitDialog;

    public ProgressDialog showLoadingDialog() {
        return showWaitDialog("加载中");
    }

    public ProgressDialog showWaitDialog(String message) {
        if (isVisible) {
            if (waitDialog == null) {
                waitDialog = UDialog.getWaitDialog(this, message);
            }
            if (waitDialog != null) {
                waitDialog.setMessage(message);
                waitDialog.show();
            }
            return waitDialog;
        }
        return null;
    }

    public void hideLoadingDialog() {
        if (isVisible && waitDialog != null) {
            try {
                waitDialog.dismiss();
                waitDialog = null;
                isVisible =false;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().destroyActivity(this);
        HttpUtils.getInstance().cancelRequest(this);
        if(waitDialog!=null){
            try{
                waitDialog.dismiss();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
