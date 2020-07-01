package com.yj.baselibrary.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * 不可滑动ListView
 */
public class MyRecyView extends RecyclerView {

	public MyRecyView(Context context) {
		super(context);
	}

	public MyRecyView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public MyRecyView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) { 
		int expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, View.MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec); 
	}
}
