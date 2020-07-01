package com.yj.baselibrary.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/3/15.
 */
public class ConvertUtils {
    public static long parseStringToLong(String str) {
        long t = 0;
        try {
            t = Long.parseLong(str);
        } catch (NumberFormatException e) {
//			LogUtils.showErrorMessage(e);
        }
        return t;
    }

    public static String continueToHaveOneBit(double value) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(value);
    }

    public static int dip2px(Context context, float dpValue) {
        return ((int) (dpValue
                * context.getResources().getDisplayMetrics().density + 0.5f));
    }

    public static int dp2px(Context context, float dpValue) {
        return ((int) (dpValue
                * context.getResources().getDisplayMetrics().density + 0.5f));
    }

    public static int pxToDp(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static float sp2px(Context context, float sp) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return sp * scale;
    }

    public static float px2sp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return pxValue / scale;
    }

    @SuppressWarnings("unchecked")
    public static <T> T convertObject(Object obj) {
        if (obj == null) {
            return null;
        }
        return (T) obj;
    }

    public static int parseStringToInt(String str) {
        int num = 0;
        try {
            num = Integer.parseInt(str);
        } catch (NumberFormatException e) {
//            LogUtils.showErrorMessage(e);
            Log.e("错误",e+"--");
        }
        return num;
    }

    @SuppressLint("SimpleDateFormat")
    public static String getTimeString(long time) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String date = "今天" + format.format(new Date(time));
        return date;
    }

    @SuppressLint("SimpleDateFormat")
    public static String getDataTimeString(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date(time));
    }
}
