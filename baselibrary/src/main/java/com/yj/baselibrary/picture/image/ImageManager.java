package com.yj.baselibrary.picture.image;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;

import com.yj.baselibrary.picture.image.load.GlideLoad;
import com.yj.baselibrary.picture.image.load.ImageLoad;
import com.yj.baselibrary.picture.image.load.LocalLoad;
import com.yj.baselibrary.utils.LogUtils;

import java.io.File;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * desc: 图片管理类
 * author: thanatos
 * date: 2019/7/2 10:04
 */
public class ImageManager {

    /**
     * 主线程{@link Handler}
     */
    private static final Handler mainHandler = new Handler(Looper.getMainLooper());

    /**
     * 类型注解
     */
    @Retention(RetentionPolicy.CLASS)
    @Target({ElementType.PARAMETER, ElementType.FIELD})
    public @interface Module {
    }


    private ImageManager() {
    }

    /**
     * {@link ImageManager}唯一对象
     */
    public static final class Builder {
        private static final ImageManager MANAGER = new ImageManager();
    }

    /**
     * 获取单例
     *
     * @return {@link ImageManager}
     */
    public static ImageManager getInstance() {
        return Builder.MANAGER;
    }

    /**
     * 加载网络图片
     *
     * @return
     */
    public ImageLoad net() {
        return new GlideLoad();
    }

    /**
     * 加载本地图片
     *
     * @return
     */
    public LocalLoad local() {
        return new LocalLoad();
    }



//    public Bitmap   loadImage(Context context,String url, int  width, int height,
//                         ImageView imageView) {
//        final Bitmap bitmap;
//        Glide.with(context)
//                .asBitmap()
//                .load(url)
//                .into(new ImageViewTarget<Bitmap>(imageView) {
//                    @Override
//                    protected void setResource(@Nullable Bitmap resource) {
//                        if (resource != null) {
//                            bitmap[0] =resource;
//                        }
//                    }
//                });
//        return null;
//    }

    public MultipartBody filesToMultipartBody(Context context,String url) {
        if(!url.contains(".png")&&!url.contains(".jpg")&&!url.contains(".jpeg")){
            url=getRealPathFromUri(context, Uri.parse(url));
            LogUtils.e("imgUrl",url+"--------");
        }
        File files=new File(url);
        if(files.exists()){
            //文件存在
            MultipartBody.Builder builder = new MultipartBody.Builder();
            // TODO: 16-4-2  这里为了简单起见，没有判断file的类型
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), files);
            builder.addFormDataPart("file", files.getName(), requestBody);
            builder.setType(MultipartBody.FORM);
            MultipartBody multipartBody = builder.build();
            return multipartBody;
        }else{
            //文件不存在
            return null;
        }
    }
    /**
     * 解决无图片格式地址转成有格式图片地址
     * @param context
     * @param contentUri
     * @return
     */
    private String getRealPathFromUri(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

}
