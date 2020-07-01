package com.yj.baselibrary.utils;

import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;

import com.blankj.utilcode.util.Utils;

/**
 * Description: 系统资源工具类
 */
public final class ResUtils {

    public static Drawable getDrawable(int resId) {
        if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
            return Utils.getApp().getApplicationContext().getResources().getDrawable(resId,
                Utils.getApp().getApplicationContext().getTheme());
        } else {
            return Utils.getApp().getApplicationContext().getResources().getDrawable(resId);
        }
    }

    public static int getColor(int resId) {
        if (VERSION.SDK_INT >= VERSION_CODES.M) {
            return Utils.getApp().getApplicationContext().getResources().getColor(resId,
                Utils.getApp().getApplicationContext().getTheme());
        } else {
            return Utils.getApp().getApplicationContext().getResources().getColor(resId);
        }
    }

    public static String getString(int resId) {
        return Utils.getApp().getApplicationContext().getString(resId);
    }

}
