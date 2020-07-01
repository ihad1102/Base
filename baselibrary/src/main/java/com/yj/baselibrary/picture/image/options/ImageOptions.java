package com.yj.baselibrary.picture.image.options;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.yj.baselibrary.base.BaseApplication;
import com.yj.baselibrary.utils.RoundedCornersTransform;


/**
 * desc: {@link com.bumptech.glide.request.RequestOptions}
 * author: thanatos
 * date: 2019/7/2 12:40
 */
public interface ImageOptions {

    /**
     * 普通Options
     */
    RequestOptions normalOption = new RequestOptions();

    /**
     * 圆图片
     */
    RequestOptions circleOption = RequestOptions.bitmapTransform(new CircleCrop());


    /**
     * 获取圆角options
     *
     * @param radius
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    static RequestOptions getRoundOption(int radius) {
        return getRoundOption(radius, true, true, true, true);
    }


    /**
     * 获取圆角option 指定圆角位置
     *
     * @param radius
     * @param leftTop
     * @param rightTop
     * @param leftBottom
     * @param rightBottom
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    static RequestOptions getRoundOption(int radius, boolean leftTop, boolean rightTop, boolean leftBottom, boolean rightBottom) {
        RoundedCornersTransform transform = new RoundedCornersTransform(BaseApplication.getAppContext(), radius);
        transform.setNeedCorner(leftTop, rightTop, leftBottom, rightBottom);
        return RequestOptions.bitmapTransform(transform);
    }

}
