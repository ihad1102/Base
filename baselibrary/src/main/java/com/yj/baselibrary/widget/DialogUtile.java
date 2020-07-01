package com.yj.baselibrary.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import com.yj.baselibrary.R;

/**
 * 加载布局
 */
public class DialogUtile {
    private static Dialog dialog = null;
    /**********************************************
            * 加载数据显示的Dialog
    ***************************************************************************/
    public static Dialog showLoading(Context context){
        View view = ViewUtils.getLayout(context,
                R.layout.dialog_ly);
//        ImageView img = (ImageView) view.findViewById(R.id.jiazai1);
//        Animation anim = AnimationUtils.loadAnimation(context,
//                R.anim.dailog);
//        anim.setInterpolator(new LinearInterpolator());//不停顿
//        img.startAnimation(anim);
        return show(context, R.style.custom_dialog_style, 0, view,
                Gravity.CENTER, true);
    }

    public static Dialog showLoadingss(Context context, String content){
        View view = ViewUtils.getLayout(context,
                R.layout.dialog_ly);
//        ImageView img = (ImageView) view.findViewById(R.id.jiazai1);
//        Animation anim = AnimationUtils.loadAnimation(context,
//         R.anim.dailog);
        TextView tv=(TextView)view.findViewById(R.id.tv_content);
        tv.setVisibility(View.VISIBLE);
        tv.setText(content);
//      anim.setInterpolator(new LinearInterpolator());//不停顿
//      img.startAnimation(anim);
        return show(context, R.style.custom_dialog_style, 0, view,
                Gravity.CENTER, true);
    }
    @SuppressWarnings("deprecation")
    public static Dialog show(Context context, int dialogStyle,
                              int dialogAnimation, View view, int gravity, boolean isBackCannel) {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
        dialog = new Dialog(context, dialogStyle);
        dialog.setContentView(view);
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.FILL_PARENT;
        lp.height = WindowManager.LayoutParams.FILL_PARENT;
        Window window = dialog.getWindow();
        window.setAttributes(lp);
        window.setGravity(gravity);
        if (dialogAnimation > 0) {
            window.setWindowAnimations(dialogAnimation);
        }
        if (!(context instanceof Activity)) {
            window.setType(WindowManager.LayoutParams.TYPE_SYSTEM_DIALOG);
            window.setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        }
        dialog.show();
        if (isBackCannel) {
            //关闭
            dialog.setCancelable(true);
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface arg0, int arg1,
                                     KeyEvent arg2) {
                    if (arg2.getAction() == KeyEvent.KEYCODE_BACK) {
                        return true;
                    }
                    return false;
                }
            });
        }
        return dialog;
    }
    /*********************************************** 关闭Dialog ***********************************************************************/
    public static void closeDialog(Dialog dialog){
        if (dialog != null){
            dialog.dismiss();
            dialog = null;
        }
    }

    public static void closeDialog(){
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }
}
