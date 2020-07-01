package com.yj.baselibrary.picture.image.callback;

import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.annotation.IntRange;
import androidx.annotation.Nullable;

/**
 * desc: 图片下载回调
 * author: thanatos
 * date: 2019/7/2 11:28
 */
public abstract class ImageLoadCallback {

    /**
     * 目标控件
     */
    private ImageView mTarget;

    /**
     * 是否开启渐进式
     */
    private boolean enableProgressive = false;

    /**
     * 开始
     */
    public void start(){}

    /**
     * 加载成功
     */
    public void finish(){}

    /**
     * 加载失败
     */
    public void error(String msg){}

    /**
     * 加载进度
     * @param progress 进度
     */
    public void progress(@IntRange(from = 0, to = 100) int progress){}


    /**
     * 绑定当前进度的目标控件
     * @param target {@link ImageView}
     */
    public void attacheTarget(@Nullable ImageView target){
        this.mTarget = target;
    }

    /**
     * 获取当前回调的目标控件
     * @return
     */
    @Nullable
    public ImageView getTarget(){
        return this.mTarget;
    }

    /**
     * 渐进式返回bitmap
     * @param bitmap 进度
     */
    public void progressive(Bitmap bitmap){}

    /**
     * 文件是否过期
     * @param id
     */
    public void expire(String id){ }

    /**
     * 是否开启渐进式加载
     * @return
     */
    public boolean enableProgressive() {
        return this.enableProgressive;
    }

    /**
     * 设置是否开启渐进式
     * @param enable
     */
    public void enableProgressive(boolean enable){
        this.enableProgressive = enable;
    }
}
