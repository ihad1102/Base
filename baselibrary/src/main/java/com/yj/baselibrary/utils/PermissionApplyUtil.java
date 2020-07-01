package com.yj.baselibrary.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.PermissionUtils;
import com.yj.baselibrary.R;
import com.yj.baselibrary.base.BaseApplication;
import com.yj.baselibrary.picture.DialogHelper;

import java.util.List;

/**
 * 拨打电话
 */
public class PermissionApplyUtil {

    private static String [] phonPermissione={ Manifest.permission.CALL_PHONE};

    public static void showCallPhonePermission(Activity activity, String phone) {
        //判断是否是华为手机
        if(android.os.Build.BRAND.toLowerCase().equals("huawei")){
            if(!checkPermissions(activity,phonPermissione)){
                requestPermisssion(activity,phonPermissione);
            }else{
                manualCallPhone(activity,phone);
            }
        }else{
        //权限提示窗口
        PermissionUtils.permission(PermissionConstants.PHONE)
                .rationale(DialogHelper::showRationaleDialog)
                .callback(new PermissionUtils.FullCallback() {
                    @Override
                    public void onGranted(List<String> permissionsGranted){
                        manualCallPhone(activity,phone);
                    }

                    @Override
                    public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied) {
                        if (!permissionsDeniedForever.isEmpty()) {
                            DialogHelper.showOpenAppSettingDialog(activity, BaseApplication.getAppContext().getString(R.string.permission_denied_forever_message));
                        } else {
                            //拒绝权限
                            ToastUitl.show("拨打电话权限被拒绝 请手动拨打");
                            manualCallPhone(activity,phone);
                        }
                    }
                })
                .request();
        }
    }

    public static void showBasePermission(Activity activity){
        //判断是否是华为手机
        if(android.os.Build.BRAND.toLowerCase().equals("huawei")){
            if(!checkPermissions(activity)){
                requestPermisssion(activity);
            }
        }else{
        //权限提示窗口
        PermissionUtils.permission(PermissionConstants.CAMERA,
                                   PermissionConstants.STORAGE,
                                   PermissionConstants.LOCATION,
                                   PermissionConstants.PHONE)
                .rationale(DialogHelper::showRationaleDialog)
                .callback(new PermissionUtils.FullCallback(){
                    @Override
                    public void onGranted(List<String> permissionsGranted) {

                    }
                    @Override
                    public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied) {
                        if (!permissionsDeniedForever.isEmpty()){
                            DialogHelper.showOpenAppSettingDialog(activity, BaseApplication.getAppContext().getString(R.string.permission_denied_forever_message));
                        } else {
                            //拒绝权限
                            ToastUitl.show("您已拒绝权限 请手动开启相应权限");

                        }
                    }
                })
                .request();
        }
    }

//    /**
//     * 拨打电话（直接拨打电话）
//     * @param phoneNum 电话号码
//     */
//    @SuppressLint("MissingPermission")
//    private static void callPhone(Activity activity, String phoneNum){
//        Intent intent = new Intent(Intent.ACTION_CALL);
//        Uri data = Uri.parse("tel:" + phoneNum);
//        intent.setData(data);
//        activity.startActivity(intent);
//    }


    /**
     * 拨打电话（跳转到拨号界面，用户手动点击拨打）
     *
     * @param phoneNum 电话号码
     */
    private static void manualCallPhone(Activity activity,String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        activity.startActivity(intent);
    }


    private static final String[] PERMISSIONS = {
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CALL_PHONE
    };

    private static boolean checkPermissions(Context context){
        for (String p : PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(context, p) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
    private static void requestPermisssion(Activity context){
        ActivityCompat.requestPermissions(context,
                PERMISSIONS,
                1);
    }

    public static boolean checkPermissions(Context context,String permission[]){
        for (String p : permission) {
            if (ContextCompat.checkSelfPermission(context, p) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
    public static void requestPermisssion(Activity context,String permission[]){
            ActivityCompat.requestPermissions(context,
                    permission,
                        1);
    }
}
