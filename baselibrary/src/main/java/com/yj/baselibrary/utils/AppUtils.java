package com.yj.baselibrary.utils;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;

import java.io.File;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * desc: 程序工具类
 * author: thanatos
 * date: 2019/8/5 10:48
 */
public final class AppUtils {

    private static Application sApplication;

    //判断在前台后台
    private static final LinkedList<Activity> activitiesList = new LinkedList<>();
    //程序未销毁的activity
    private static final LinkedList<Activity> appActivitiesList = new LinkedList<>();
    //页面生命周期回调
    private static final HashMap<Activity, Set<OnActivityStateListener>> mDestroyedListenerMap = new HashMap<>();
    //程序状态回调
    private static final HashMap<Object, OnAppStatusChangedListener> mStatusListenerMap = new HashMap<>();

    public static void init(Application application) {
        sApplication = application;
        sApplication.registerActivityLifecycleCallbacks(new AppLifeCallback());
    }

    /**
     * 程序在前台运行
     *
     * @return
     */
    public static boolean isForeground() {
        return !activitiesList.isEmpty();
    }

    /**
     * 程序在后台运行
     *
     * @return
     */
    public static boolean isBackground() {
        return activitiesList.isEmpty();
    }

    /**
     * 获取当前显示的ui
     *
     * @return
     */
    @Nullable
    public static Activity getCurrentActivity() {
        if (isForeground()) {
            return activitiesList.getFirst();
        }
        return null;
    }

    /**
     * 关闭所有的页面不退出
     */
    public static void finishAllActivity() {
        if (appActivitiesList.isEmpty()) {
            return;
        }
        for (Activity activity : appActivitiesList) {
            activity.finish();

        }
        appActivitiesList.clear();
    }

    /**
     * 关闭某个页面
     *
     * @param activity
     */
    public static void finishActivity(Activity activity) {
        if (activity == null) {
            return;
        }
        appActivitiesList.remove(activity);
        activity.finish();
    }

    /**
     * 关闭一些activity
     *
     * @param activities
     */
    public static void finishActivities(Activity... activities) {
        if (activities == null || activities.length == 0) {
            return;
        }
        for (Activity activity : activities) {
            appActivitiesList.remove(activity);
            activity.finish();
        }
    }

    /**
     * 关闭除过忽略的activity外所有的
     *
     * @param ignore
     */
    public static void finishActivitiesIgnoreWho(Activity ignore) {
        if (appActivitiesList.isEmpty()) {
            return;
        }

        LinkedList<Activity> removes = new LinkedList<>();
        for (Activity activity : appActivitiesList) {
            if (activity != ignore) {
                removes.add(activity);
            }
        }
        appActivitiesList.removeAll(removes);
        for (Activity remove : removes) {
            remove.finish();
        }
        removes.clear();
    }

    /**
     * 退出程序
     */
    public static void exit() {
        if (appActivitiesList.isEmpty()) {
            System.exit(0);
            return;
        }
        for (Activity activity : appActivitiesList) {
            activity.finish();
        }
        System.exit(0);

    }

    /**
     * 获取程序当前栈数量
     * @return
     */
    public static int getStackSize(){
        return appActivitiesList.size();
    }

    /**
     * 获取app栈
     * @return
     */
    public static LinkedList<Activity> getStackActivity(){
        return appActivitiesList;
    }

    /**
     * 获取手机型号
     */
    public static String getSystemModel() {
        return Build.MODEL;
    }


    /**
     * 获取设备唯一标识，使用此方法
     *
     * @return deviceId
     */
    @SuppressLint("HardwareIds")
    public static String compatDeviceId() {

        String androidId = Settings.System.getString(sApplication.getContentResolver(), Settings.Secure.ANDROID_ID);
        if (androidId != null && !androidId.isEmpty() && !androidId.equals("9774d56d682e549c")) {
            return androidId;
        }
        String serial = null;
        String m_szDevIDShort = "35" +
                Build.BOARD.length() % 10 + Build.BRAND.length() % 10 +
                Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10 +
                Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 +
                Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 +
                Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10 +
                Build.TAGS.length() % 10 + Build.TYPE.length() % 10 +
                Build.USER.length() % 10;
        //13位设备编码
        serial = "serial" + UUID.randomUUID();
        String deviceId = null;
        SharedPreferences sharedPreferences = sApplication.getSharedPreferences("sp_device_info",
                Context.MODE_PRIVATE);
        deviceId = sharedPreferences.getString("device_id", "");
        if (deviceId == null || deviceId.isEmpty()) {
            deviceId = new UUID(m_szDevIDShort.hashCode(),
                    serial.hashCode()).toString().replace("-", "");
            sharedPreferences.edit().putString("device_id", deviceId).apply();
        }
        return deviceId;
    }


    /**
     * 获取系统版本
     *
     * @return
     */
    public static String getVersion() {
        return "Android " + Build.VERSION.RELEASE + "";
    }


    /**
     * 获取本地Mac地址
     *
     * @return MacAddress
     */
    public static String getLocalMacAddr() {
        String macAddress;
        StringBuilder buf = new StringBuilder();
        NetworkInterface networkInterface;
        try {
            networkInterface = NetworkInterface.getByName("eth1");
            if (networkInterface == null) {
                networkInterface = NetworkInterface.getByName("wlan0");
            }
            if (networkInterface == null) {
                return "";
            }
            byte[] address = networkInterface.getHardwareAddress();

            for (byte b : address) {
                buf.append(String.format("%02X:", b));
            }
            if (buf.length() > 0) {
                buf.deleteCharAt(buf.length() - 1);
            }
            macAddress = buf.toString();
        } catch (SocketException e) {
            e.printStackTrace();
            return "";
        }
        return macAddress;
    }

    /**
     * 获取版本号
     *
     * @return
     */
    public static long getVersionCode() {
        PackageInfo info = null;
        try {
            info = sApplication.getPackageManager().getPackageInfo(sApplication.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (info == null) {
            return -1;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            return info.getLongVersionCode();
        } else {
            return info.versionCode;
        }
    }

    /**
     * 获取版本名称
     *
     * @return
     */
    public static String getVersionName() {
        try {
            return sApplication.getPackageManager().getPackageInfo(sApplication.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }


    /**
     * 判断某个app是否安装
     *
     * @param packageName
     * @return
     */
    public static boolean isAppInstalled(@NonNull final String packageName) {
        PackageManager packageManager = sApplication.getPackageManager();
        try {
            return packageManager.getApplicationInfo(packageName, 0) != null;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 是否是Android Q以上
     * @return
     */
    public static boolean isAndroidQ(){
        return Build.VERSION.SDK_INT >= 29;
    }


    /**
     * 是否是androidN以上
     * @return
     */
    public static boolean isAndroidN(){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N;
    }

    /**
     * 页面生命周期回调
     */
    private static final class AppLifeCallback implements Application.ActivityLifecycleCallbacks {

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            appActivitiesList.addFirst(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {
            activitiesList.addFirst(activity);
            postStatus(!activitiesList.isEmpty());
        }

        @Override
        public void onActivityResumed(Activity activity) {
            consumeOnActivityResumedListener(activity);
        }

        @Override
        public void onActivityPaused(Activity activity) {
            consumeOnActivityPausedListener(activity);
        }

        @Override
        public void onActivityStopped(Activity activity) {
            activitiesList.remove(activity);
            postStatus(!activitiesList.isEmpty());
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            appActivitiesList.remove(activity);
        }
    }


    /**
     * 当前acitivity是否正在运行
     *
     * @param activity
     * @return
     */
    public static boolean isActivityRunning(Activity activity) {
        if (activity == null) {
            return false;
        }
        if (activity.isFinishing() || activity.isDestroyed()) {
            return false;
        }
        if (activity instanceof FragmentActivity) {
            FragmentActivity fragmentActivity = (FragmentActivity) activity;
            FragmentManager manager = fragmentActivity.getSupportFragmentManager();
            if (manager == null || manager.isDestroyed()) {
                return false;
            }
            Lifecycle.State state = fragmentActivity.getLifecycle().getCurrentState();
            return state != Lifecycle.State.DESTROYED;
        }
        return true;
    }

    /**
     * 添加程序状态变化
     *
     * @param object
     * @param listener
     */
    public static void addOnAppStatusChangedListener(final Object object,
                                                     final OnAppStatusChangedListener listener) {
        mStatusListenerMap.put(object, listener);
    }

    /**
     * 移除程序状态变化监听
     *
     * @param object
     */
    public static void removeOnAppStatusChangedListener(final Object object) {
        mStatusListenerMap.remove(object);
    }

    /**
     * 移除页面状态变化监听
     *
     * @param activity
     */
    private static void removeOnActivityStateListener(final Activity activity) {
        if (activity == null) return;
        mDestroyedListenerMap.remove(activity);
    }

    /**
     * 添加页面状态变化监听
     *
     * @param activity
     * @param listener
     */
    public static void addOnActivityStateListener(final Activity activity,
                                                  final OnActivityStateListener listener) {
        if (activity == null || listener == null) return;
        Set<OnActivityStateListener> listeners;
        if (!mDestroyedListenerMap.containsKey(activity)) {
            listeners = new HashSet<>();
            mDestroyedListenerMap.put(activity, listeners);
        } else {
            listeners = mDestroyedListenerMap.get(activity);
            if (listeners.contains(listener)) return;
        }
        listeners.add(listener);
    }

    private static void postStatus(final boolean isForeground) {
        if (mStatusListenerMap.isEmpty()) return;
        for (OnAppStatusChangedListener onAppStatusChangedListener : mStatusListenerMap.values()) {
            if (onAppStatusChangedListener == null) return;
            if (isForeground) {
                onAppStatusChangedListener.onForeground();
            } else {
                onAppStatusChangedListener.onBackground();
            }
        }
    }

    private static void consumeOnActivityResumedListener(Activity activity) {
        Set<Map.Entry<Activity, Set<OnActivityStateListener>>> entries = mDestroyedListenerMap.entrySet();
        for (Map.Entry<Activity, Set<OnActivityStateListener>> entry : entries) {
            if (entry.getKey() == activity) {
                Set<OnActivityStateListener> value = entry.getValue();
                for (OnActivityStateListener listener : value) {
                    listener.onActivityResumed(activity);
                }
            }
        }
    }

    private static void consumeOnActivityPausedListener(Activity activity) {
        Set<Map.Entry<Activity, Set<OnActivityStateListener>>> entries = mDestroyedListenerMap.entrySet();
        for (Map.Entry<Activity, Set<OnActivityStateListener>> entry : entries) {
            if (entry.getKey() == activity) {
                Set<OnActivityStateListener> value = entry.getValue();
                for (OnActivityStateListener listener : value) {
                    listener.onActivityPaused(activity);
                }
                removeOnActivityStateListener(activity);
            }
        }
    }

    /**
     * 程序状态回调监听
     */
    public interface OnAppStatusChangedListener {
        void onForeground();

        void onBackground();
    }

    /**
     * 页面状态变化监听
     */
    public interface OnActivityStateListener {

        void onActivityResumed(Activity activity);

        void onActivityPaused(Activity activity);
    }
    /**
     * Md5加密
     * @param string
     * @return
     */
    public static String md5(String string) {
        if (TextUtils.isEmpty(string)){
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 设置状态栏颜色
     */

    public static void setActivityStatus(Window window){
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView =window.getDecorView();
            //设置全屏和状态栏透明
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            // 设置状态栏为主题色
            window.setStatusBarColor(Color.parseColor("#2190ce"));
        }
    }
    /**
     * 定位，读写权限
     */
    public static final String[] PERMISSIONS = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public static boolean checkPermissions(Context context){
        for (String p : PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(context, p) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
    public static void requestPermisssion(Activity context){
        ActivityCompat.requestPermissions(context,
                PERMISSIONS,
                1);
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
    public static final String APP_FOLDER_NAME = "BNSDKS";
    private static String mSDCardPath = null;
    public   static boolean initDirs() {
        mSDCardPath = getSdcardDir();
        if (mSDCardPath == null) {
            return false;
        }
        File f = new File(mSDCardPath, APP_FOLDER_NAME);
        if (!f.exists()) {
            try {
                f.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
    public static String getSdcardDir() {
        if (Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED)) {
            return Environment.getExternalStorageDirectory().toString();
        }
        return null;
    }
    public static String getTTSAppID() {
        return "18410416";
    }
}
