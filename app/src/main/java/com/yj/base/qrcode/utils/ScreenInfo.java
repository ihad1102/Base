package com.yj.base.qrcode.utils;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * @Title: ScreenInfo.java
 * @Package com.insthub.zoushop.util
 * @Description:用于获取手机的屏幕大小
 * @author Yj
 * @date 2015年4月10日 上午11:06:15
 * @version V1.0
 */
public class ScreenInfo {
	private Activity activity;
	/** 屏幕宽度（像素） */
	private int width;
	/** 屏幕高度（像素） */
	private int height;
	/** 屏幕密度�?.75 / 1.0 / 1.5�? */
	private float density;
	/** 屏幕密度DPI�?20 / 160 / 240�? */
	private int densityDpi;

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getDensity() {
		return density;
	}

	public void setDensity(float density) {
		this.density = density;
	}

	public int getDensityDpi() {
		return densityDpi;
	}

	public void setDensityDpi(int densityDpi) {
		this.densityDpi = densityDpi;
	}

	public ScreenInfo(Activity activity) {
		this.activity = activity;
		ini();
	}

	private void ini() {
		DisplayMetrics metric = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
		width = metric.widthPixels;
		height = metric.heightPixels;
		density = metric.density;
		densityDpi = metric.densityDpi;
	}

	public static int MinWidth(int weigth) {
		if (rangeInDefined1(weigth)) {
			return 240;
		}
		if (rangeInDefined2(weigth)) {
			return 240;
		}
		if (rangeInDefined3(weigth)) {
			return 240;
		}
		if (rangeInDefined4(weigth)) {
			return 500;
		}
		return 360;
	}
	public static int MaxWidth(int weigth){
		if (rangeInDefined1(weigth)) {
			return 330;
		}
		if (rangeInDefined2(weigth)) {
			return 330;
		}
		if (rangeInDefined3(weigth)) {
			return 330;
		}
		if (rangeInDefined4(weigth)){
			return 750;
		}
		return 480;
	}
     
	// 屏幕宽度在240到320之间
	public static boolean rangeInDefined1(int current){
		return Math.max(240, current) == Math.min(current,479);
	}

	// 屏幕宽度在480到600之间
	public static boolean rangeInDefined2(int current){
		return Math.max(480, current) == Math.min(current,719);
	}

	// 屏幕宽度在720到800之间
	public static boolean rangeInDefined3(int current){
		return Math.max(720, current) == Math.min(current,890);
	}

	// 屏幕宽度在1080到1920之间
	public static boolean rangeInDefined4(int current) {
		return Math.max(1080, current) == Math.min(current, 1920);
	}
}
