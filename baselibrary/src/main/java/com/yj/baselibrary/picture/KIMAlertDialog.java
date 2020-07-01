package com.yj.baselibrary.picture;

import android.content.Context;
import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;

import com.yj.baselibrary.R;

/**
 * desc:
 * author: thanatos
 * date: 2019/7/16 14:11
 */
public class KIMAlertDialog extends AlertDialog {


    protected KIMAlertDialog(@NonNull Context context) {
        super(context, R.style.AppCommonAlertDialog);
    }

    /**
     * desc: 弹窗构建类
     * author: thanatos
     * date: 2019/7/11 13:30
     */
    public static final class Builder{

        private Context context;

        private KIMAlertDialog alertDialog;


        public Builder(@NonNull Context context) {
            this.context = context;
            this.alertDialog = new KIMAlertDialog(context);
        }

        /**
         * 设置文本信息
         * @param msg
         * @return
         */
        public Builder setMessage(CharSequence msg){
            this.alertDialog.setMessage(msg);
            return this;
        }

        /**
         * 设置文本信息
         * @param id
         * @return
         */
        public Builder setMessage(@StringRes int id){
            this.alertDialog.setMessage(context.getResources().getString(id));
            return this;
        }

        /**
         * 设置标题
         * @param title
         * @return
         */
        public Builder setTitle(CharSequence title){
            this.alertDialog.setTitle(title);
            return this;
        }

        /**
         * 设置标题
         * @param id
         * @return
         */
        public Builder setTitle(@StringRes int id){
            this.alertDialog.setTitle(id);
            return this;
        }

        /**
         * 按钮点击事件
         * @param click
         * @return
         */
        public Builder setPositiveButton(OnClickListener click){
            this.alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,
                    context.getResources().getString(android.R.string.ok), click);
            return this;
        }

        /**
         * 按钮点击事件
         * @param textId
         * @param click
         * @return
         */
        public Builder setPositiveButton(@StringRes int textId, OnClickListener click){
            this.alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,
                    context.getResources().getString(textId), click);
            return this;
        }

        /**
         * 按钮点击事件
         * @param click
         * @return
         */
        public Builder setNegativeButton(OnClickListener click){
            this.alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE,
                    context.getResources().getString(android.R.string.cancel), click);
            return this;
        }

        /**
         * 按钮点击事件
         * @param textId
         * @param click
         * @return
         */
        public Builder setNegativeButton(@StringRes int textId, OnClickListener click){
            this.alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE,
                    context.getResources().getString(textId), click);
            return this;
        }

        /**
         * 按钮点击事件
         * @param textId
         * @param click
         * @return
         */
        public Builder setNeutralButton(@StringRes int textId, OnClickListener click){
            this.alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL,
                    context.getResources().getString(textId), click);
            return this;
        }

        /**
         * 是否可以取消
         * @param cancelable
         * @return
         */
        public Builder setCancelable(boolean cancelable){
            this.alertDialog.setCancelable(cancelable);
            return this;
        }

        /**
         * 创建{@link KIMAlertDialog}
         * @return
         */
        public KIMAlertDialog create() {
            return alertDialog;
        }


    }
}
