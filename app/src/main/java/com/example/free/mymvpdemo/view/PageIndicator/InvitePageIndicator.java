package com.example.free.mymvpdemo.view.PageIndicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ConvertUtils;
import com.example.free.mymvpdemo.R;
import com.example.free.mymvpdemo.util.ULog;

import java.util.List;

/**
 * Created by qiaoda on 2017/1/4.
 */

public class InvitePageIndicator extends LinearLayout {
    /**
     * 绘制滑动横线的画笔
     */
    private Paint mPaint;

    /**
     * 绘制底部横线的画笔
     */
    private Paint mPaint1;

    private float paintWidth;
    /**
     * 横线的宽度
     */
    private int mTriangleWidth;

    /**
     * 初始时，横线指示器的偏移量
     */
    private int mInitTranslationX;
    /**
     * 手指滑动时的偏移量
     */
    private float mTranslationX;

    /**
     * 默认的Tab数量
     */
    private static final int COUNT_DEFAULT_TAB = 2;
    /**
     * tab数量
     */
    private int mTabVisibleCount = COUNT_DEFAULT_TAB;

    /**
     * tab上的内容
     */
    private List<View> mTabViews;

    /**
     * 与之绑定的ViewPager
     */
    public ViewPager mViewPager;

    /**
     * 选中线条的颜色
     */
    private int lineColor;
    /**
     * 背景线条的颜色
     */
    private int backlineColor;


    public InvitePageIndicator(Context context) {
        this(context, null);
    }

    public InvitePageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 获得自定义属性，tab的数量
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.MyViewPagerIndicator);
        paintWidth = a.getDimension(R.styleable.MyViewPagerIndicator_line_width, ConvertUtils.dp2px(3));
        lineColor = a.getColor(R.styleable.MyViewPagerIndicator_select_line_color, 0xFFffffff);
        backlineColor = a.getColor(R.styleable.MyViewPagerIndicator_backgrpund_line_color, 0xFFD9DEE1);
        if (mTabVisibleCount < 0)
            mTabVisibleCount = COUNT_DEFAULT_TAB;
        a.recycle();

        // 初始化画笔
        mPaint = new Paint();
        mPaint.setColor(lineColor);
        mPaint.setStrokeWidth(paintWidth);

        mPaint1 = new Paint();
        mPaint1.setColor(backlineColor);
        mPaint1.setStrokeWidth(paintWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //画出下边线
        canvas.drawLine(0, getHeight(), getWidth(), getHeight(), mPaint1);
        super.onDraw(canvas);
    }

    /**
     * 绘制指示器
     */
    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.save();
        // 画笔平移到正确的位置
        canvas.translate(mInitTranslationX + mTranslationX, getHeight() + 1);
        canvas.drawLine(mInitTranslationX, 0, mInitTranslationX + mTriangleWidth, 0, mPaint);
        canvas.restore();

        super.dispatchDraw(canvas);
    }

    /**
     * 初始化横线
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTriangleWidth = (int) (w / mTabVisibleCount);//

        // 初始时的偏移量
        mInitTranslationX = getWidth() / mTabVisibleCount / 2 - mTriangleWidth
                / 2;
    }

    /**
     * 设置可见的tab的数量
     *
     * @param count
     */
    public void setVisibleTabCount(int count) {
        this.mTabVisibleCount = count;
    }

    /**
     * 设置tab的标题内容 可选，可以自己在布局文件中写死
     */
    public void setTabItemTitles(List<View> mTabViews) {
        // 如果传入的list有值，则移除布局文件中设置的view
        if (mTabViews != null && mTabViews.size() > 0) {
            this.removeAllViews();
            this.mTabViews = mTabViews;
            for (View view : mTabViews) {
                // 添加view
                addView(view);
            }
            // 设置item的click事件
            setItemClickEvent();
        }

    }

    /**
     * 对外的ViewPager的回调接口
     *
     * @author zhy
     */
    public interface PageChangeListener {
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels);

        public void onPageSelected(int position);

        public void onPageScrollStateChanged(int state);
    }

    // 对外的ViewPager的回调接口
    private PageChangeListener onPageChangeListener;

    // 对外的ViewPager的回调接口的设置
    public void setOnPageChangeListener(PageChangeListener pageChangeListener) {
        this.onPageChangeListener = pageChangeListener;
    }

    // 设置关联的ViewPager
    public void setViewPager(ViewPager mViewPager, int pos) {
        this.mViewPager = mViewPager;

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // 回调
                if (onPageChangeListener != null) {
                    onPageChangeListener.onPageSelected(position);
                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                // 滚动
                scroll(position, positionOffset);
                // 回调
                if (onPageChangeListener != null) {
                    onPageChangeListener.onPageScrolled(position,
                            positionOffset, positionOffsetPixels);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // 回调
                if (onPageChangeListener != null) {
                    onPageChangeListener.onPageScrollStateChanged(state);
                }

            }
        });
        // 设置当前页
        mViewPager.setCurrentItem(pos);
    }


    /**
     * 设置点击事件
     */
    public void setItemClickEvent() {
        int cCount = getChildCount();
        for (int i = 0; i < cCount; i++) {
            final int j = i;
            View view = getChildAt(i);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mViewPager!= null){
                        mViewPager.setCurrentItem(j);
                    }
                }
            });
        }
    }



    /**
     * 指示器跟随手指滚动，以及容器滚动
     *
     * @param position
     * @param offset
     */
    public void scroll(int position, float offset) {
        /**
         * <pre>
         *  0-1:position=0 ;1-0:postion=0;
         * </pre>
         */
        // 不断改变偏移量，invalidate
        mTranslationX = getWidth() / mTabVisibleCount * (position + offset);

        int tabWidth = getScreenWidth() / mTabVisibleCount;

        // 容器滚动，当移动到倒数最后一个的时候，开始滚动
        if (offset > 0 && position >= (mTabVisibleCount - 2)
                && getChildCount() > mTabVisibleCount) {
            if (mTabVisibleCount != 1) {
                this.scrollTo((position - (mTabVisibleCount - 2)) * tabWidth
                        + (int) (tabWidth * offset), 0);
            } else
            // 为count为1时 的特殊处理
            {
                this.scrollTo(
                        position * tabWidth + (int) (tabWidth * offset), 0);
            }
        }

        invalidate();
    }

    /**
     * 设置布局中view的一些必要属性；如果设置了setTabTitles，布局中view则无效
     */
    @Override
    protected void onFinishInflate() {
        ULog.e("TAG", "onFinishInflate");
        super.onFinishInflate();
        int cCount = getChildCount();
        if (cCount == 0)
            return;
        for (int i = 0; i < cCount; i++) {
            View view = getChildAt(i);
            LayoutParams lp = (LayoutParams) view
                    .getLayoutParams();
//            AutoUtils.auto(view);
            lp.weight = 0;
            lp.width = getScreenWidth() / mTabVisibleCount;
            view.setLayoutParams(lp);

        }
        // 设置点击事件
        setItemClickEvent();

    }

    /**
     * 获得屏幕的宽度
     *
     * @return
     */
    public int getScreenWidth() {
        WindowManager wm = (WindowManager) getContext().getSystemService(
                Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

}
