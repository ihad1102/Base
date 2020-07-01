package com.yj.baselibrary.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

/**
 * Bitmap 工具类
 * @author thanatos <a href="mailto:lxf523028638@outlook.com">contact me</a>
 * @since 2019-09-06 13:30
 * @version 1.0
 */
public final class BitmapUtils {

    private BitmapUtils(){}

    /**
     * 智能缩放bitmap
     * @param buffer
     * @param target
     * @return
     */
    public static Bitmap adapterBitmap(byte[] buffer, ImageView target) {
        int width = 100;
        int height = 100;
        if (target.getWidth() > 0){
            width = target.getWidth();
        }
        if (width == 100 && target.getMeasuredWidth() > 0){
            width = target.getMeasuredWidth();
        }
        if (target.getHeight() > 0){
            height = target.getHeight();
        }
        if (height == 100 && target.getMeasuredHeight() > 0){
            height = target.getMeasuredHeight();
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(buffer, 0 , buffer.length, options);
        int realW = options.outWidth;
        int realH = options.outHeight;
        int l = Math.max(realW, realH);
        int t = Math.min(width, height);
        int sampleSize = l/t;
        options.inJustDecodeBounds = false;
        options.inSampleSize = sampleSize;
        return BitmapFactory.decodeByteArray(buffer, 0, buffer.length, options);
    }
}
