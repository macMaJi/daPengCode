package com.example.free.mymvpdemo.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.free.mymvpdemo.R;
import com.example.free.mymvpdemo.util.ULog;


/**
 * 自定义标题头
 *
 * @author BOB
 */
public class TitleBar extends RelativeLayout {

    private TextView btn_left, btn_right, btn_middle;
    private TextView tv_title;
    private String tv_text, left_text, right_text, middle_text;
    private Drawable left_res_id, right_res_id, middle_res_id;
    private boolean left_show, right_show, middle_show;

    private TitleBarClick titleBarClick;
    private TitleBarLeftClick leftClick;
    private TitleBarMiddleClick middleClick;
    private TitleBarRightClick rightClick;
    private TitleBarPullDownClick pullDownClick;
    private RelativeLayout rly_bg;
    private Context mContext;
    private TextView pullDownText;

    public void setLeftClick(TitleBarLeftClick leftClick) {
        this.leftClick = leftClick;
    }

    public void setMidlleClick(TitleBarMiddleClick middleClick) {
        this.middleClick = middleClick;
    }

    public void setRightClick(TitleBarRightClick rightClick) {
        this.rightClick = rightClick;
    }

    public void setPullDownClick(TitleBarPullDownClick pullDownClick) {
        this.pullDownClick = pullDownClick;
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context, attrs);
    }

    private void initViews(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.TitleBar);
        this.mContext = context;
        tv_text = a.getString(R.styleable.TitleBar_tv_text);

        left_text = a.getString(R.styleable.TitleBar_left_text);
        left_show = a.getBoolean(R.styleable.TitleBar_left_show, false);
        left_res_id = a.getDrawable(R.styleable.TitleBar_left_res_id);

        middle_text = a.getString(R.styleable.TitleBar_middle_text);
        middle_show = a.getBoolean(R.styleable.TitleBar_middle_show, false);
        middle_res_id = a.getDrawable(R.styleable.TitleBar_middle_res_id);

        right_text = a.getString(R.styleable.TitleBar_right_text);
        right_show = a.getBoolean(R.styleable.TitleBar_right_show, false);
        right_res_id = a.getDrawable(R.styleable.TitleBar_right_res_id);

        int titleColor = a.getColor(R.styleable.TitleBar_title_color, getResources().getColor(R.color.pure_white));
        int leftColor = a.getColor(R.styleable.TitleBar_left_color, getResources().getColor(R.color.pure_white));
        int middleColor = a.getColor(R.styleable.TitleBar_left_bet_title_color, getResources().getColor(R.color.pure_white));
        int rightColor = a.getColor(R.styleable.TitleBar_right_color, getResources().getColor(R.color.pure_white));


        String pulldown = a.getString(R.styleable.TitleBar_pull_down_text);
        boolean isIconFont = a.getBoolean(R.styleable.TitleBar_is_iconfont, false);

        a.recycle();

        View.inflate(context, R.layout.title_bar_view, this);
        tv_title = (TextView) findViewById(R.id.tv_title);
        btn_left = (TextView) findViewById(R.id.btn_left);
        btn_middle = ((TextView) findViewById(R.id.tv_middle));
        btn_right = (TextView) findViewById(R.id.btn_right);
        pullDownText = (TextView) findViewById(R.id.tv_pull_down);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int statusBarHeight1 = -1;
            //获取status_bar_height资源的ID
            int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                //根据资源ID获取响应的尺寸值
                statusBarHeight1 = getResources().getDimensionPixelSize(resourceId);
            }
            setPadding(0, statusBarHeight1, 0, 0);
        }

        if (pulldown != null) {
            pullDownText.setVisibility(View.VISIBLE);
            pullDownText.setText(pulldown);
        }
        if (isIconFont) {
            Typeface iconfont = Typeface.createFromAsset(mContext.getAssets(), "iconfont/iconfont.ttf");
            btn_middle.setTypeface(iconfont);
            btn_left.setTypeface(iconfont);
            btn_right.setTypeface(iconfont);
        }

        tv_title.setTextColor(titleColor);
        btn_left.setTextColor(leftColor);
        btn_middle.setTextColor(middleColor);
        btn_right.setTextColor(rightColor);

        rly_bg = (RelativeLayout) findViewById(R.id.rly_bg);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            rly_bg.setBackground(getBackground());
        }
        tv_title.setText(tv_text);

        //设置左边按钮
        btn_left.setText(left_text);
        if (left_res_id != null) {
            left_res_id.setBounds(0, 0, left_res_id.getMinimumWidth(),
                    left_res_id.getMinimumHeight());
            btn_left.setCompoundDrawables(left_res_id, null, null, null);
        }
        btn_left.setVisibility(left_show ? View.VISIBLE : View.GONE);

        //设置中间按钮
        btn_middle.setText(middle_text);
        if (middle_res_id != null) {
            middle_res_id.setBounds(0, 0, middle_res_id.getMinimumWidth(),
                    middle_res_id.getMinimumHeight());
            btn_middle.setCompoundDrawables(middle_res_id, null, null, null);
        }
        btn_middle.setVisibility(middle_show ? View.VISIBLE : View.GONE);

        //设置右边按钮
        btn_right.setText(right_text);
        if (right_res_id != null) {
            right_res_id.setBounds(0, 0, right_res_id.getMinimumWidth(),
                    right_res_id.getMinimumHeight());
            btn_right.setCompoundDrawables(null, null, right_res_id, null);
        }
        btn_right.setVisibility(right_show ? View.VISIBLE : View.GONE);

        //按钮点击事件
        btn_left.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ULog.i("titlebar", "click left");
                if (titleBarClick != null) {
                    titleBarClick.onLeftClick();
                }
                if (leftClick != null) {
                    leftClick.onLeftClick(v);
                }
                if (titleBarClick == null && leftClick == null && left_show) {
                    if (!(TextUtils.isEmpty(left_text) && left_res_id == null)) {
                        ((Activity) mContext).finish();
                    }
                }
            }
        });

        btn_middle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ULog.i("titlebar", "middle click");
                if (titleBarClick != null) {
                    titleBarClick.onMiddleClick();
                }
                if (middleClick != null) {
                    middleClick.onMiddleClick(v);
                }
            }
        });

        btn_right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ULog.i("titlebar", "click right");
                if (titleBarClick != null) {
                    titleBarClick.onRightClick();
                }
                if (rightClick != null) {
                    rightClick.onRightClick(v);
                }
            }
        });
        pullDownText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pullDownClick != null) {
                    setPullDownunSelect(true);
                    pullDownClick.onPullDownClick(v);
                }
            }
        });
    }

    public void setPullDownText(String text) {
        if (pullDownText.getVisibility() == GONE) {
            pullDownText.setVisibility(VISIBLE);
        }
        pullDownText.setText(text);
    }

    public void setPullDownunSelect(Boolean b) {
        pullDownText.setSelected(b);
    }

    public void setClick(TitleBarClick barClick) {
        this.titleBarClick = barClick;
    }

    public void setTitleText(String text) {
        tv_title.setText(text);
    }

    public void setTitleText(int resId) {
        tv_title.setText(mContext.getString(resId));
    }

    public void setLeftBtnText(String text) {
        btn_left.setText(text);
    }

    public void setMiddleBtnText(String text) {
        btn_middle.setText(text);
    }

    public void setLeftBtnVisibility(int visibility) {
        btn_left.setVisibility(visibility);
    }

    public void setRightBtnText(String text) {
        btn_right.setText(text);
    }

    public void setRightBtnIcon(int resId) {
        right_res_id = getResources().getDrawable(resId);
        right_res_id.setBounds(0, 0, right_res_id.getMinimumWidth(),
                right_res_id.getMinimumHeight());
        btn_right.setCompoundDrawables(null, null, right_res_id, null);
    }

    public void showLeft() {
        if (left_res_id != null) {
            setLeftIcon(left_res_id);
        }
    }

    public void setLeftBtnIcon(int resId) {
        left_res_id = getResources().getDrawable(resId);
        setLeftIcon(left_res_id);
    }

    public void setLeftIcon(Drawable resId) {
        left_res_id = resId;
        left_res_id.setBounds(0, 0, resId.getMinimumWidth(),
                resId.getMinimumHeight());
        btn_left.setCompoundDrawables(left_res_id, null, null, null);
    }

    public void setMiddleIcon(int resId) {
        middle_res_id = getResources().getDrawable(resId);
        middle_res_id.setBounds(0, 0, middle_res_id.getMinimumWidth(),
                middle_res_id.getMinimumHeight());
        btn_middle.setCompoundDrawables(middle_res_id, null, null, null);
    }

    public void setMiddleVisibility(int visibility) {
        btn_middle.setVisibility(visibility);
    }

    public void setRightBtnVisibility(int visibility) {
        btn_right.setVisibility(visibility);
    }

    @Override
    public void setDrawingCacheBackgroundColor(@ColorInt int color) {
        super.setDrawingCacheBackgroundColor(color);
    }

    public void setBackgroundColor(int color) {
        rly_bg.setBackgroundColor(color);
    }

    public void setBackgroundColor(String color) {
        rly_bg.setBackgroundColor(Color.parseColor(color));
        rly_bg.getBackground().setAlpha(100);
    }

    public void setBackgroundAlpha(int alpha) {
        rly_bg.getBackground().setAlpha(alpha);
    }

    public interface TitleBarClick {
        void onLeftClick();

        void onMiddleClick();

        void onRightClick();
    }

    public interface TitleBarLeftClick {
        void onLeftClick(View v);
    }

    public interface TitleBarMiddleClick {
        void onMiddleClick(View v);
    }

    public interface TitleBarRightClick {
        void onRightClick(View v);
    }

    public TextView getRightTextView() {
        return btn_right;
    }

    public TextView getPullDownText() {
        return pullDownText;
    }

    public TextView getLeftTextView() {
        return btn_left;
    }

    public TextView getMiddleTextView() {
        return btn_middle;
    }

    public TextView getTitltTextView() {
        return tv_title;
    }

    public interface TitleBarPullDownClick {
        void onPullDownClick(View v);
    }
}
