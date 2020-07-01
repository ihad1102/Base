package com.yj.baselibrary.picture;

import android.app.Activity;
import android.content.DialogInterface;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.blankj.utilcode.util.PermissionUtils;
import com.yj.baselibrary.R;
import com.yj.baselibrary.utils.AppUtils;
import com.yj.baselibrary.base.BaseApplication;

/**
 * desc: 弹窗辅助类
 * author: thanatos
 * update date: 2019/7/23 14:58
 */
public class DialogHelper {

    public static void showRationaleDialog(final PermissionUtils.OnRationaleListener.ShouldRequest shouldRequest) {
        showRationaleDialog(null, BaseApplication.getAppContext().getString(R.string.permission_rationale_message), shouldRequest);
    }

    public static void showRationaleDialog(@Nullable Activity activity, final PermissionUtils.OnRationaleListener.ShouldRequest shouldRequest) {
        showRationaleDialog(activity, BaseApplication.getAppContext().getString(R.string.permission_rationale_message), shouldRequest);
    }

    public static void showRationaleDialog(@Nullable Activity activity, String message, final PermissionUtils.OnRationaleListener.ShouldRequest shouldRequest) {
        if (AppUtils.isBackground()){
            return;
        }
        if (activity == null){
            activity = AppUtils.getCurrentActivity();
        }
        if (!AppUtils.isActivityRunning(activity)){
            return;
        }
        KIMAlertDialog alertDialog=  new KIMAlertDialog.Builder(activity)
                .setTitle(android.R.string.dialog_alert_title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        shouldRequest.again(true);
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        shouldRequest.again(false);
                    }
                })
                .setCancelable(false)
                .create();
        alertDialog.show();
    }

    /**
     * 弹窗
     * @param message 提示的消息
     * @param ok 确认按钮回调
     * @param cancel 取消按钮回调
     */
    public static void showDialog(String message, DialogInterface.OnClickListener ok, DialogInterface.OnClickListener cancel){
        showDialog(message, ok, cancel, true);
    }

    /**
     * 弹窗
     * @param message 提示的消息
     * @param ok 确认按钮回调
     * @param cancel 取消按钮回调
     * @param showCancel 是否显示取消按钮
     */
    public static void showDialog(String message, DialogInterface.OnClickListener ok, DialogInterface.OnClickListener cancel, boolean showCancel){
        if (AppUtils.isBackground()){
            return;
        }
        Activity topActivity = AppUtils.getCurrentActivity();
        if (topActivity == null) return;
        KIMAlertDialog.Builder builder = new KIMAlertDialog.Builder(topActivity);
        builder.setTitle(android.R.string.dialog_alert_title);
        builder.setMessage(message);
        builder.setPositiveButton(android.R.string.ok, ok);
        if (showCancel){
            builder.setNegativeButton(android.R.string.cancel, cancel == null? new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            } : cancel);
        }
        builder.setCancelable(false);
        AlertDialog alertDialog = builder.create();
        try {
            alertDialog.show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void showOpenAppSettingDialog() {
        showOpenAppSettingDialog(null,BaseApplication.getAppContext().getString(R.string.permission_denied_forever_message));
    }

    public static void showOpenAppSettingDialog(@Nullable Activity activity) {
        showOpenAppSettingDialog(activity,   BaseApplication.getAppContext().getString(R.string.permission_denied_forever_message));
    }

    public static void showOpenAppSettingDialog(@Nullable Activity activity, String message){
//        if (AppUtils.isBackground()){
//            LogUtils.e("showOpenAppSettingDialog","---进来了222");
//            return;
//        }
        if (activity == null){
            activity = AppUtils.getCurrentActivity();
        }
        if (!AppUtils.isActivityRunning(activity)){
            return;
        }
        new KIMAlertDialog.Builder(activity)
                .setTitle(android.R.string.dialog_alert_title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PermissionUtils.launchAppDetailsSettings();
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }
}