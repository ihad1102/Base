package com.yj.baselibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

/**
 * Created by tianchen on 2017/7/20.
 */

public class SharedPreferencesUtils {
    /**
     * 初始化一个叫vqs_shared_data的偏好设置
     */
    public static void initSharedPreferences(Context context) {
        sp = context.getSharedPreferences("shared_data", Context.MODE_PRIVATE);
    }

    private static SharedPreferences sp;

    /**
     * 保存单条数据
     */
    public static void setStringDate(String key, String value) {
        sp.edit().putString(key, value).commit();
    }

    public static void setBooleanDate(String key, boolean value) {
        sp.edit().putBoolean(key, value).commit();
    }

    public static void setIntDate(String key, int value) {
        sp.edit().putInt(key, value).commit();
    }

    /**
     * 批量保存数据
     */
    public static void setAllDate(ArrayList<String> keys,
                                  ArrayList<String> values){
        for (int i = 0; i < keys.size(); i++) {
            sp.edit().putString(keys.get(i), values.get(i)).commit();
        }
    }
    /**
     * 批量载入数据
     */
    public static ArrayList<String> getAllDate(ArrayList<String> keys) {
        ArrayList<String> values = new ArrayList<String>();
        for (String key : keys) {
            String value = sp.getString(key, "");
            values.add(value);
        }
        return values;
    }

    /**
     * 获取字符串数据
     */
    public static String getStringDate(String key) {
        return getStringDate(key, "");
    }
    /**
     * 获取字符串数据
     */
    public static String getStringDate(String key, String def) {
        return sp.getString(key, def);
    }

    public static boolean getBooleanDate(String key) {
        return sp.getBoolean(key, false);
    }

    public static int getIntDate(String key) {
        return sp.getInt(key, 1);
    }

    public static void setLongDate(String key, long value) {
        sp.edit().putLong(key, value).commit();

    }

    public static long getLongDate(String showRecommentInstallTime){
        return sp.getLong(showRecommentInstallTime, 0);
    }

    public static void clearUser() {
//		sp.edit().remove("isnew").remove("unionid").commit();
        sp.edit().clear().commit();
    }
}
