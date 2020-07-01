package com.yj.baselibrary.utils;

import android.util.Log;

public class LogUtils {

    //是否显示日志
    private  final static boolean DEBUG_LOG = true;


    private LogUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static void i(String tag, String msg) {
        if(DEBUG_LOG)
            Log.i(tag, msg);
    }

    public static void i_with_trace(String tag, String msg) {
        if(DEBUG_LOG)
            Log.i(tag, msg + "--" + Log.getStackTraceString(new Throwable()));
    }

    public static void e(String tag, String msg) {
        if(DEBUG_LOG)
            Log.e(tag, msg);
    }

    public static void e_with_trace(String tag, String msg) {
        if(DEBUG_LOG)
            Log.e(tag, msg + "--" + Log.getStackTraceString(new Throwable()));
    }

    public static void w(String tag, String msg) {
        if(DEBUG_LOG)
            Log.w(tag, msg);
    }

    public static void w_with_trace(String tag, String msg) {
        if(DEBUG_LOG)
            Log.w(tag, msg + "--" + Log.getStackTraceString(new Throwable()));
    }

    public static void v(String tag, String msg) {
        if(DEBUG_LOG)
            Log.v(tag, msg);
    }

    public static void v_with_trace(String tag, String msg) {
        if(DEBUG_LOG)
            Log.v(tag, msg + "--" + Log.getStackTraceString(new Throwable()));
    }

}
