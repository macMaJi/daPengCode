package com.example.free.mymvpdemo.view.PageIndicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.blankj.utilcode.util.ConvertUtils;
import com.example.free.mymvpdemo.R;
import com.example.free.mymvpdemo.util.ULog;

import java.util.List;


/**
 * http://blog.csdn.net/lmj623565791/article/details/42160391
 * @author zhy
 *
 */
public class ViewPagerIndicator extends LinearLayout
{
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
	private static final int COUNT_DEFAULT_TAB = 3;
	/**
	 * tab数量
	 */
	private int mTabVisibleCount = COUNT_DEFAULT_TAB;

	/**
	 * tab上的内容
	 */
	private List<String> mTabTitles;

	private List<String> mTabTitles2;
	/**
	 * 与之绑定的ViewPager
	 */
	public ViewPager mViewPager;

	/**
	 * 标题正常时的颜色
	 */
	private static int COLOR_TEXT_NORMAL = 0x77999999;
	/**
	 * 标题选中时的颜色
	 */
	private static int COLOR_TEXT_HIGHLIGHTCOLOR = 0xFF33aaff;

	/**
	 * 选中线条的颜色
	 */
	private int lineColor;
	/**
	 * 背景线条的颜色
	 */
	private int backlineColor;

	public TextView t;

	public ViewPagerIndicator(Context context)
	{
		this(context, null);
	}

	public ViewPagerIndicator(Context context, AttributeSet attrs)
	{
		super(context, attrs);

		// 获得自定义属性，tab的数量
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.ViewPagerIndicator);
//		mTabVisibleCount = a.getInt(R.styleable.MyViewPagerIndicator_item_count, COUNT_DEFAULT_TAB);
		COLOR_TEXT_NORMAL = a.getColor(R.styleable.MyViewPagerIndicator_unselect_text_color,0x77999999);
		COLOR_TEXT_HIGHLIGHTCOLOR = a.getColor(R.styleable.MyViewPagerIndicator_select_text_color,0xFF33aaff);
		paintWidth = a.getDimension(R.styleable.MyViewPagerIndicator_line_width, ConvertUtils.dp2px(4));
		lineColor = a.getColor(R.styleable.MyViewPagerIndicator_select_line_color,0xFF33aaff);
		backlineColor = a.getColor(R.styleable.MyViewPagerIndicator_backgrpund_line_color,0xFFD9DEE1);
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
		canvas.drawLine(0,getHeight(),getWidth(),getHeight(),mPaint1);
		super.onDraw(canvas);
	}

	/**
	 * 绘制指示器
	 */
	@Override
	protected void dispatchDraw(Canvas canvas)
	{
		canvas.save();
		// 画笔平移到正确的位置
		canvas.translate(mInitTranslationX + mTranslationX, getHeight() + 1);
		canvas.drawLine(mInitTranslationX,0,mInitTranslationX+mTriangleWidth,0,mPaint);
		canvas.restore();

		super.dispatchDraw(canvas);
	}

	/**
	 * 初始化横线
	 * */
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh)
	{
		super.onSizeChanged(w, h, oldw, oldh);
		mTriangleWidth = (int) (w / mTabVisibleCount );//

		// 初始时的偏移量
		mInitTranslationX = getWidth() / mTabVisibleCount / 2 - mTriangleWidth
				/ 2;
	}

	/**
	 * 设置可见的tab的数量
	 * 
	 * @param count
	 */
	public void setVisibleTabCount(int count)
	{
		this.mTabVisibleCount = count;
	}

	/**
	 * 设置tab的标题内容 可选，可以自己在布局文件中写死
	 * 
	 * @param datas
	 */
	public void setTabItemTitles(List<String> datas)
	{
		// 如果传入的list有值，则移除布局文件中设置的view
		if (datas != null && datas.size() > 0)
		{
			this.removeAllViews();
			this.mTabTitles = datas;

			for (String title : mTabTitles)
			{
				// 添加view
				addView(generateTextView(title));
			}
			// 设置item的click事件
			setItemClickEvent();
		}

	}

	public void setTabItemTitles(List<String> datas,List<String> data1)
	{
		// 如果传入的list有值，则移除布局文件中设置的view
		if (datas != null && datas.size() > 0)
		{
			this.removeAllViews();
			this.mTabTitles = datas;
			this.mTabTitles2 = data1;
			for(int i = 0;i<datas.size();i++){
				String title = datas.get(i);
				String title1 = data1.get(i);
				// 添加view
				addView(generateTextView(title,title1));
			}
			// 设置item的click事件
			setItemClickEvent();
		}

	}

	/**
	 * 对外的ViewPager的回调接口
	 * 
	 * @author zhy
	 * 
	 */
	public interface PageChangeListener
	{
		public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels);

		public void onPageSelected(int position);

		public void onPageScrollStateChanged(int state);
	}

	// 对外的ViewPager的回调接口
	private PageChangeListener onPageChangeListener;

	// 对外的ViewPager的回调接口的设置
	public void setOnPageChangeListener(PageChangeListener pageChangeListener)
	{
		this.onPageChangeListener = pageChangeListener;
	}

	// 设置关联的ViewPager
	public void setViewPager(ViewPager mViewPager, int pos)
	{
		this.mViewPager = mViewPager;

		mViewPager.setOnPageChangeListener(new OnPageChangeListener()
		{
			@Override
			public void onPageSelected(int position)
			{
				// 设置字体颜色高亮
				resetTextViewColor();
				highLightTextView(position);

				// 回调
				if (onPageChangeListener != null)
				{
					onPageChangeListener.onPageSelected(position);
				}
			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels)
			{
				// 滚动
				scroll(position, positionOffset);

				// 回调
				if (onPageChangeListener != null)
				{
					onPageChangeListener.onPageScrolled(position,
							positionOffset, positionOffsetPixels);
				}

			}

			@Override
			public void onPageScrollStateChanged(int state)
			{
				// 回调
				if (onPageChangeListener != null)
				{
					onPageChangeListener.onPageScrollStateChanged(state);
				}

			}
		});
		// 设置当前页
		mViewPager.setCurrentItem(pos);
		// 高亮
		highLightTextView(pos);
	}

	/**
	 * 高亮文本
	 * 
	 * @param position
	 */
	public void highLightTextView(int position)
	{
		View view = getChildAt(position);
		if (view instanceof TextView)
		{
			((TextView) view).setTextColor(COLOR_TEXT_HIGHLIGHTCOLOR);
		}else if(view instanceof LinearLayout){
			for(int j = 0; j < ((LinearLayout)view).getChildCount(); j++){
				View textview = ((LinearLayout)view).getChildAt(j);
				if (textview instanceof TextView)
				{
					((TextView) textview).setTextColor(COLOR_TEXT_HIGHLIGHTCOLOR);
				}
			}
		}
	}

	/**
	 * 重置文本颜色
	 */
	private void resetTextViewColor()
	{
		for (int i = 0; i < getChildCount(); i++)
		{
			View view = getChildAt(i);
			if (view instanceof TextView)
			{
				((TextView) view).setTextColor(COLOR_TEXT_NORMAL);
			}
			if(view instanceof LinearLayout){
				for(int j = 0; j < ((LinearLayout)view).getChildCount(); j++){
					View textview = ((LinearLayout)view).getChildAt(j);
					if (textview instanceof TextView)
					{
						((TextView) textview).setTextColor(COLOR_TEXT_NORMAL);
					}
				}
			}
		}
	}

	/**
	 * 设置点击事件
	 */
	public void setItemClickEvent()
	{
		int cCount = getChildCount();
		for (int i = 0; i < cCount; i++)
		{
			final int j = i;
			View view = getChildAt(i);
			view.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if(mViewPager != null){
						mViewPager.setCurrentItem(j);
					}
				}
			});
		}
	}

	/**
	 * 根据标题生成我们的TextView
	 * 
	 * @param text
	 * @return
	 */
	private TextView generateTextView(String text)
	{
		TextView tv = new TextView(getContext());
		LayoutParams lp = new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		lp.width = getScreenWidth() / mTabVisibleCount;
		tv.setGravity(Gravity.CENTER);
		tv.setTextColor(COLOR_TEXT_NORMAL);
		tv.setText(text);
		tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
		tv.setLayoutParams(lp);
		return tv;
	}

	/**
	 * 根据标题生成我们的TextView
	 *
	 * @param text
	 * @return
	 */
	private LinearLayout generateTextView(String text,String text1)
	{
		LinearLayout ll = new LinearLayout(getContext());
		ll.setGravity(Gravity.CENTER);
		LayoutParams lp = new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		lp.width = getScreenWidth() / mTabVisibleCount;
		ll.setGravity(Gravity.CENTER);
		ll.setOrientation(LinearLayout.VERTICAL);
		ll.setLayoutParams(lp);
		TextView tv = new TextView(getContext());
		tv.setTextColor(COLOR_TEXT_NORMAL);
		tv.setText(text);
		tv.setGravity(Gravity.CENTER);
		tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
		TextView tv1 = new TextView(getContext());
		tv1.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		tv1.setTextColor(COLOR_TEXT_NORMAL);
		tv1.setText(text1);
		tv1.setGravity(Gravity.CENTER);
		tv1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
		ll.addView(tv);
		ll.addView(tv1);
		return ll;
	}


	/**
	 * 指示器跟随手指滚动，以及容器滚动
	 * 
	 * @param position
	 * @param offset
	 */
	public void scroll(int position, float offset)
	{
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
				&& getChildCount() > mTabVisibleCount)
		{
			if (mTabVisibleCount != 1)
			{
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
	protected void onFinishInflate()
	{
		ULog.e("TAG", "onFinishInflate");
		super.onFinishInflate();

		int cCount = getChildCount();

		if (cCount == 0)
			return;

		for (int i = 0; i < cCount; i++)
		{
			View view = getChildAt(i);
			LayoutParams lp = (LayoutParams) view
					.getLayoutParams();
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
	public int getScreenWidth()
	{
		WindowManager wm = (WindowManager) getContext().getSystemService(
				Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.widthPixels;
	}


}
