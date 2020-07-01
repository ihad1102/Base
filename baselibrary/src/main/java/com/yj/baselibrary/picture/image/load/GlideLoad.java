package com.yj.baselibrary.picture.image.load;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.yj.baselibrary.base.BaseApplication;
import com.yj.baselibrary.picture.image.ImageManager;
import com.yj.baselibrary.picture.image.callback.ImageLoadCallback;
import com.yj.baselibrary.picture.image.options.ImageOptions;
import com.yj.baselibrary.utils.BitmapUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Objects;

/**
 * 如果要使用缓存，请最好不要设置占位图，否则会出现先显示缓存，再显示占位图
 * <code>
 * {@link ImageLoad#getCacheFile(Context)} 获取当前文件存在的所有类型缓存
 * {@link ImageLoad#getCacheFile(Context, String)} 获取当前文件存的对应类型缓存
 * </code>
 * desc: Glide 加载
 * author: thanatos
 * date: 2019/7/2 13:45
 */
public class GlideLoad implements ImageLoad {

    @ImageManager.Module
    private String module;

    private String imageId;

    @Type
    private String type;

    @Nullable
    private RequestOptions options;

    private String url = "";

    /**
     * 是否开启显示缓存
     */
    private boolean enableCache = false;
    /**
     * 是否开启渐进式加载
     */
    private boolean progressive = false;

    private ImageLoadCallback mCallback;

    @Override
    public ImageLoad setModule(@NonNull @ImageManager.Module String module) {
        this.module = module;
        return this;
    }

    @Override
    public ImageLoad setImageId(@NonNull String id) {
        this.imageId = id;
        return this;
    }

    @Override
    public ImageLoad setType(@NonNull @Type String type) {
        this.type = type;
        return this;
    }

    @Override
    public ImageLoad setOption(@Nullable RequestOptions options) {
        this.options = options;
        return this;
    }

    @Nullable
    @Override
    public File getCacheFile(@NonNull Context context, @NonNull String type) {
        return null;
    }

    @Nullable
    @Override
    public File getCacheFile(@NonNull Context context) {
        return null;
    }

    @Override
    public void load(@NonNull ImageView target, @Nullable ImageLoadCallback callback) {
        if (checkAndBuildDefaultValue(callback, target)) {
            innerLoad(Glide.with(target), target, url, Objects.requireNonNull(options));
        }
    }

    @SuppressLint("CheckResult")
    @Override
    public void load(@NonNull Context context, @NonNull ImageView target, @Nullable ImageLoadCallback callback) {
        if (checkAndBuildDefaultValue(callback, target)) {
            innerLoad(Glide.with(context), target, url, Objects.requireNonNull(options));
        }
    }

    @Override
    public void load(@NonNull Activity activity, @NonNull ImageView target, @Nullable ImageLoadCallback callback) {
        if (checkAndBuildDefaultValue(callback, target)) {
            innerLoad(Glide.with(activity), target, url, Objects.requireNonNull(options));
        }
    }

    @Override
    public void load(@NonNull Fragment fragment, @NonNull ImageView target, @Nullable ImageLoadCallback callback) {
        if (checkAndBuildDefaultValue(callback, target)) {
            innerLoad(Glide.with(fragment), target, url, Objects.requireNonNull(options));
        }
    }

    @Override
    public void gif(RequestListener<GifDrawable> listener, SimpleTarget<GifDrawable> target) {
        if (checkAndBuildDefaultValue(null, null)) {
            Glide.with(BaseApplication.getAppContext()).asGif().load(url).apply(Objects.requireNonNull(options))
                    .listener(listener).into(target);
        } else {
            if (listener != null) listener.onLoadFailed(null, null, null, false);
        }
    }

    @Override
    public void gif(RequestListener<GifDrawable> listener, ImageView img) {
        if (checkAndBuildDefaultValue(null, null)) {
            Glide.with(BaseApplication.getAppContext()).asGif().load(url).apply(Objects.requireNonNull(options))
                    .listener(listener).into(img);
        } else {
            if (listener != null) listener.onLoadFailed(null, null, null, false);
        }
    }

    @Override
    public void file(RequestListener<File> listener, SimpleTarget<File> target) {
        if (checkAndBuildDefaultValue(null, null)) {
            Glide.with(BaseApplication.getAppContext()).asFile().load(url).apply(Objects.requireNonNull(options))
                    .listener(listener).into(target);
        } else {
            if (listener != null) listener.onLoadFailed(null, null, null, false);
        }

    }

    @Override
    public void drawable(RequestListener<Drawable> listener, SimpleTarget<Drawable> target) {
        if (checkAndBuildDefaultValue(null, null)) {
            Glide.with(BaseApplication.getAppContext()).asDrawable().load(url).apply(Objects.requireNonNull(options))
                    .listener(listener).into(target);
        } else {
            if (listener != null) listener.onLoadFailed(null, null, null, false);
        }
    }

    @Override
    public void bitmap(RequestListener<Bitmap> listener, SimpleTarget<Bitmap> target) {
        if (checkAndBuildDefaultValue(null, null)) {
            Glide.with(BaseApplication.getAppContext()).asBitmap().load(url).apply(Objects.requireNonNull(options))
                    .listener(listener).into(target);
        } else {
            if (listener != null) listener.onLoadFailed(null, null, null, false);
        }
    }

    @Nullable
    private String buildUrl() {
        return buildUrl(type);
    }

    private String buildUrl(String type) {
        if (imageId != null && !imageId.isEmpty()) {
            return String.format(type, this.module, this.imageId);
        }
        return null;
    }


    /**
     * 设置参数
     *
     * @param callback 回调
     * @param target   目标控件
     */
    private boolean checkAndBuildDefaultValue(@Nullable ImageLoadCallback callback, @Nullable ImageView target) {
        //构建默认回调
        if (callback == null) {
            this.mCallback = new DefaultImageLoadCallback();
            this.mCallback.attacheTarget(target);
            this.mCallback.enableProgressive(this.progressive);
        } else {
            this.mCallback = callback;
        }
        //校验id
        if (this.imageId == null) {
            this.mCallback.error("imageId 不能为null");
//            LogUtils.e("checkAndBuildDefaultValue","imageId 不能为null");
            return false;
        }
        url = this.imageId;
        //校验option
        if (this.options == null) {
            if (enableCache && target != null) {
                this.options = new RequestOptions().placeholder(new BitmapDrawable(target.getResources(),
                        fileConvertBitmap(getCacheFile(target.getContext()), target)));
            } else {
                this.options = ImageOptions.normalOption;
            }
        } else {
            //可能不会生效，如果没有设置占位图会显示，如果设置了占位图，会先显示缓存再显示占位图（这边无法知道逻辑部分是否必须需要展位图，所以没有做修改）
            if (target != null && this.enableCache) {
                this.options = this.options.placeholder(new BitmapDrawable(target.getResources(),
                        fileConvertBitmap(getCacheFile(target.getContext()), target)));
            }
        }
        return true;
    }

    /**
     * 内部加载
     *
     * @param manager
     * @param url
     * @param options
     */
    private void innerLoad(@NonNull RequestManager manager, @NonNull ImageView target, @NonNull String url,
                           @NonNull RequestOptions options) {
        this.mCallback.start();
        //清除当前view缓存
        manager.clear(target);
        manager.load(url).apply(options)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        if (mCallback != null) {
//                            LogUtils.e("onLoadFailed","加载失败");
                        }
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        if (mCallback != null) {
//                            LogUtils.e("onResourceReady","加载成功");
                        }
                        return false;
                    }
                }).into(target);
    }


    @Override
    public ImageLoad enableShowCache() {
        this.enableCache = true;
        return this;
    }

    @Override
    public ImageLoad enableProgressive() {
        this.progressive = true;
        return this;
    }

    /**
     * 渐进式图片加载回调和默认图片加载回调
     */
    private static final class DefaultImageLoadCallback extends ImageLoadCallback {

        @Override
        public void progressive(Bitmap bitmap) {
            super.progressive(bitmap);
            if (getTarget() != null) {
                Glide.with(getTarget())
                        .load(bitmap)
                        .into(getTarget());
//                LogManager.d("显示渐进式图片");
            }
        }

        @Override
        public void expire(String id) {
            super.expire(id);

        }
    }


    /**
     * 通过文件获取适合控件尺寸的bitmap
     *
     * @param file 文件
     * @param view 目标view
     * @return bitmap
     */
    public static Bitmap fileConvertBitmap(File file, ImageView view) {
        if (file == null || !file.exists()) {
            return null;
        }
        try {
            InputStream stream = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[8 * 1024];
            int len;
            while ((len = stream.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            stream.close();
            bos.flush();
            bos.close();
            return BitmapUtils.adapterBitmap(bos.toByteArray(), view);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
