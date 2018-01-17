package com.example.free.mymvpdemo.ui;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.example.free.mymvpdemo.R;
import com.example.free.mymvpdemo.manager.BaseActivity;
import com.example.free.mymvpdemo.manager.Nav;
import com.example.free.mymvpdemo.util.ULog;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.common.Constants;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        LogUtils.e("myDream :" + ActivityUtils.getTopActivity());
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        XGPushManager.registerPush(getApplicationContext(), new XGIOperateCallback() {
            @Override
            public void onSuccess(Object data, int flag) {
                ULog.w(Constants.LogTag, "+++ register push sucess. token:" + data + "flag" + flag);
            }

            @Override
            public void onFail(Object data, int errCode, String msg) {
                ULog.w(Constants.LogTag,
                        "+++ register push fail. token:" + data + ", errCode:" + errCode + ",msg:" + msg);
            }
        });
        // 获取token
        XGPushConfig.getToken(this);
    }

    @Override
    public boolean showTitleBar() {
        return false;
    }

    @OnClick(R.id.button_enter_layout)
    public void enterLayout() {
        Nav.toMyLayoutActivity(this);
    }

    @OnClick(R.id.button_picasso)
    public void enterPicasso() {
        Nav.toPicassoActivity(this);
    }

    @OnClick(R.id.button_recycler)
    public void buttonRecyclerView() {
        Nav.toRecyclerViewActivity(this);
    }

    @OnClick(R.id.swipe_recycler)
    public void swipeRecyclerView() {
        Nav.toSwipeRecyclerViewActivity(this);
    }

    @OnClick(R.id.button_waterfall)
    public void waterFall() {
        Nav.toStaggeredGridLayoutActivity(this);
    }

    @OnClick(R.id.enter_handler)
    public void handlerControl() {
        Nav.toHandlerActivity(this);
    }

    @OnClick(R.id.serviceControl)
    public void serViceControl() {
        Nav.toServiceActivity1(this);
    }

    @OnClick(R.id.aidl_control)
    public void aidlControl() {
        Nav.toAIDLActivity(this);
    }

    @OnClick(R.id.jni_control)
    public void jniControl() {
        Nav.toJNIActivity(this);
    }

    @OnClick(R.id.eventBus)
    public void eventBus() {
        Nav.toEventBusActivity(this);
    }

    @OnClick(R.id.nine_patch)
    public void ninePatch() {
        Nav.toMy9PatchActivity(this);
    }

    @OnClick(R.id.time_util)
    public void timeUtil() {
        Nav.toTimeActivity(this);
    }

    @OnClick(R.id.light_control)
    public void lightControl() {
        Nav.toLightControlActivity(this);
    }

    @OnClick(R.id.checkbox)
    public void checkbox() {
        Nav.toCheckBoxActivity(this);
    }

    @OnClick(R.id.recycle_bin)
    public void recycleBin() {
        Nav.toRecycleBinActivity(this);
    }

    @OnClick(R.id.checkbox_delete)
    public void checkBoxDelete() {
        Nav.toCheckBoxDeleteActivity(this);
    }

    @OnClick(R.id.user_dialog)
    public void toUserDialog() {
        Nav.toUserDialogActivity(this);
    }

    @OnClick(R.id.immersion_jump)
    public void toImmersionJump() {
        Nav.toImmersionActivity(this);
    }

    @OnClick(R.id.text_watcher)
    public void toTextWatcher() {
        Nav.toTextWatcherActivity(this);
    }

    @OnClick(R.id.change_fragment)
    public void toChangeFragment() {
        Nav.toChangeTabActivity(this);
    }

    @OnClick(R.id.load_glide)
    public void toLoadGlide() {
        Nav.toGlideActivity(this);
    }

    @OnClick(R.id.ok_http)
    public void toOkHttp() {
        Nav.toOkHttp3Activity(this);
    }

    @OnClick(R.id.text_view)
    public void toTextView() {
        Nav.toTextViewActivity(this);
    }

    @OnClick(R.id.multiple_item)
    public void toMultipleItem() {
        Nav.toMultipleItemUseActivity(this);
    }

    @OnClick(R.id.button_tab_layout)
    public void toTabLayout() {
        Nav.toTabLayoutActivity(this);
    }

}