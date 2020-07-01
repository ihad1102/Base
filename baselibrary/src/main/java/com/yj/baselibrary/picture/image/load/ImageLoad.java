package com.yj.baselibrary.picture.image.load;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.yj.baselibrary.picture.image.ImageManager;
import com.yj.baselibrary.picture.image.callback.ImageLoadCallback;

import java.io.File;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * desc: 图片加载
 * author: thanatos
 * date: 2019/7/2 11:36
 */
public interface ImageLoad {

    /**
     * 图片类型
     */
    @Retention(RetentionPolicy.CLASS)
    @Target({ElementType.PARAMETER, ElementType.FIELD})
    public @interface Type{}

    /**
     * 设置 模块
     * @param module
     */
    ImageLoad setModule(@ImageManager.Module String module);

    /**
     * 设置图片id
     * @param id
     * @return
     */
    ImageLoad setImageId(String id);

    /**
     * 设置加载类型
     * @param type
     * @return
     */
    ImageLoad setType(@Type String type);

    /**
     * 设置{@link RequestOptions}
     * @param option
     * @return
     */
    ImageLoad setOption(RequestOptions option);


    /**
     * 获取缓存文件
     * @param context
     * @param type
     * @return
     */
    @Nullable
    File getCacheFile(@NonNull Context context, @NonNull @Type String type);


    /**
     * 获取缓存文件
     * @param context
     * @return
     */
    @Nullable
    File getCacheFile(@NonNull Context context);


    /**
     * 是否显示缓存
     * @return
     */
    ImageLoad enableShowCache();

    /**
     * 是否开启渐进式加载 目前图片基本是 Baseline JPEG 需要后台配合转换格式为 Progressive JPEG
     * @return
     */
    ImageLoad enableProgressive();

    /**
     * 加载（带回调）
     * @param callback 回调
     */
    void load(@NonNull Context context, @NonNull ImageView target, @Nullable ImageLoadCallback callback);

    /**
     * 加载图片
     * @param activity
     * @param target
     * @param callback
     */
    void load(@NonNull Activity activity, @NonNull ImageView target, @Nullable ImageLoadCallback callback);

    /**
     * 加载图片
     * @param fragment
     * @param target
     * @param callback
     */
    void load(@NonNull Fragment fragment, @NonNull ImageView target, @Nullable ImageLoadCallback callback);


    /**
     * 加载图片
     * @param target
     * @param callback
     */
    void load(@NonNull ImageView target, @Nullable ImageLoadCallback callback);

    /**
     * 加载gif
     * @param listener
     * @param simpleTarget
     */
    void gif(RequestListener<GifDrawable> listener, SimpleTarget<GifDrawable> simpleTarget);

    /**
     * 加载git
     * @param listener
     * @param img
     */
    void gif(RequestListener<GifDrawable> listener, ImageView img);

    /**
     * 加载文件
     * @param listener
     * @param target
     */
    void file(RequestListener<File> listener, SimpleTarget<File> target);

    /**
     * 加载drawable
     * @param listener
     * @param target
     */
    void drawable(RequestListener<Drawable> listener, SimpleTarget<Drawable> target);

    /**
     * 加载bitmap
     * @param listener
     * @param target
     */
    void bitmap(RequestListener<Bitmap> listener, SimpleTarget<Bitmap> target);



}
