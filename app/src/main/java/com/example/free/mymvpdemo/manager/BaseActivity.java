package com.example.free.mymvpdemo.manager;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.LogUtils;
import com.example.free.mymvpdemo.util.ActivityManager;
import com.example.free.mymvpdemo.util.HttpUtils.HttpUtils;
import com.example.free.mymvpdemo.util.UDialog;

import butterknife.ButterKnife;


/**
 * 功能：封装头部和沉浸式功能，方便统一管理activity
 */
public abstract class BaseActivity extends AppCompatActivity {


    protected LinearLayout rootLayout;//内容载体
    public LinearLayout baseView;

    public String TAG = getClass().getSimpleName();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.w("---------------------------  " + TAG + "  ---------------------------");
        isVisible = true;
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initView(savedInstanceState);
        initData();
        initListener();
        //实现沉浸式功能
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
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
     * 加载布局文件
     *
     * @return
     */
    protected abstract int getLayoutId();


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
