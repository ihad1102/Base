package com.yj.baselibrary.picture.image.load;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yj.baselibrary.picture.image.options.ImageOptions;

import java.io.File;

/**
 * desc: 加载本地图片
 * author: thanatos
 * date: 2019/7/2 14:07
 */
public class LocalLoad {

    private RequestOptions options;


    /**
     * 设置{@link RequestOptions}
     * @param option
     * @return
     */
    public LocalLoad setOption(RequestOptions option){
        this.options = option;
        return this;
    }

    /**
     * 加载file
     * @param context
     * @param target
     * @param file
     */
    public void loadFile(Context context, ImageView target, File file){
        checkOptions();
        Glide.with(context)
                .load(file)
                .apply(this.options)
                .into(target);
    }

    /**
     * 加载文件路径
     * @param context
     * @param target
     * @param path
     */
    public void loadPath(Context context, ImageView target, String path){
        checkOptions();
        Glide.with(context)
                .load(path)
                .apply(this.options)
                .into(target);
    }

    /**
     * 加载uri
     * @param context
     * @param target
     * @param uri
     */
    public void loadUri(Context context, ImageView target, Uri uri){
        checkOptions();
        Glide.with(context)
                .load(uri)
                .apply(this.options)
                .into(target);
    }

    /**
     * 加载字节
     * @param context
     * @param target
     * @param data
     */
    public void loadByte(Context context, ImageView target, byte[] data){
        checkOptions();
        Glide.with(context)
                .load(data)
                .apply(this.options)
                .into(target);
    }

    /**
     * 加载bitmap
     * @param context
     * @param target
     * @param bitmap
     */
    public void loadBitmap(Context context, ImageView target, Bitmap bitmap){
        checkOptions();
        Glide.with(context)
                .load(bitmap)
                .apply(this.options)
                .into(target);
    }

    /**
     * 加载drawable
     * @param context
     * @param target
     * @param drawable
     */
    public void loadDrawable(Context context, ImageView target, Drawable drawable){
        checkOptions();
        Glide.with(context)
                .load(drawable)
                .apply(this.options)
                .into(target);
    }

    /**
     * 加载资源
     * @param context
     * @param target
     * @param drawableId
     */
    public void loadDrawableId(Context context, ImageView target, @DrawableRes int drawableId){
        checkOptions();
        Glide.with(context)
                .load(drawableId)
                .apply(this.options)
                .into(target);

    }


    /**
     * 检查option
     */
    private void checkOptions(){
        if (this.options == null){
            this.options = ImageOptions.normalOption;
        }
    }


}
