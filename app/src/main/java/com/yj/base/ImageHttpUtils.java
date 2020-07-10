package com.yj.base;

import android.content.Context;
import android.os.Build;
import android.widget.ImageView;
import androidx.annotation.RequiresApi;
import com.bumptech.glide.request.RequestOptions;
import com.yj.baselibrary.picture.image.ImageManager;
import com.yj.baselibrary.picture.image.options.ImageOptions;

public class ImageHttpUtils {
    private volatile static ImageHttpUtils imageHttpUtils = null;
    private ImageHttpUtils() {}
    public static ImageHttpUtils getInstance() {
        if (imageHttpUtils == null) {
            synchronized (ImageHttpUtils.class) {
                if (imageHttpUtils == null) {
                    imageHttpUtils = new ImageHttpUtils();
                }
            }
        }
        return imageHttpUtils;
    }
    private RequestOptions option =new RequestOptions()
            .placeholder(R.drawable.default_image)
            .error(R.drawable.default_image);
    /**
     * 加载网络图片
     * @param imageView
     * @param imgUrl
     */
    public void LoadHttpImage(ImageView imageView, String imgUrl){
        if(imgUrl!=null&&imgUrl.length()>0&&!imgUrl.equals(imageView.getTag())){
            ImageManager.getInstance().net()
                    .setOption(option)
                    .setImageId(imgUrl)
                    .load(imageView, null);
            imageView.setTag(imgUrl);
        }
    }

    /**
     * 不缓存
     * @param imageView
     * @param imgUrl
     */
    public void LoadHttpNoCacheImage(ImageView imageView,String imgUrl){
        if(imgUrl!=null&&imgUrl.length()>0){
            ImageManager.getInstance().net()
                    .setOption(option)
                    .setImageId(imgUrl)
                    .load(imageView, null);
        }
    }

    /**
     * 加载带弧度得图片
     * @param imageView
     * @param imgUrl
     * @param radius
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void LoadHttpRoundImage(ImageView imageView, String imgUrl,int radius){
        RequestOptions options = ImageOptions.getRoundOption(radius)
                .placeholder(R.drawable.default_image)
                .error(R.drawable.default_image);
        if(imgUrl!=null&&imgUrl.length()>0&&!imgUrl.equals(imageView.getTag())){
            ImageManager.getInstance().net()
                    .setOption(options)
                    .setImageId(imgUrl)
                    .load(imageView, null);
            imageView.setTag(imgUrl);
        }
    }

    /**
     * 加载带弧度得图片 不缓存
     * @param imageView
     * @param imgUrl
     * @param radius
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void LoadHttpNoCacheRoundImage(ImageView imageView, String imgUrl,int radius){
        RequestOptions options = ImageOptions.getRoundOption(radius)
                .placeholder(R.drawable.default_image)
                .error(R.drawable.default_image);
        if(imgUrl!=null&&imgUrl.length()>0){
            ImageManager.getInstance().net()
                    .setOption(options)
                    .setImageId(imgUrl)
                    .load(imageView, null);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void LoadHttpNoCacheCustomRoundImage(ImageView imageView, String imgUrl,int radius){
        RequestOptions options = ImageOptions.getRoundOption(radius)
                .placeholder(R.drawable.default_image)
                .error(R.drawable.default_image);
        if(imgUrl!=null&&imgUrl.length()>0){
            ImageManager.getInstance().net()
                    .setOption(options)
                    .setImageId(imgUrl)
                    .load(imageView, null);
        }
    }
    private RequestOptions options =new RequestOptions()
            .placeholder(R.drawable.toux)
            .circleCrop()
            .error(R.drawable.toux);
    /**
     * 加载网络头像
     * @param imageView
     * @param imgUrl
     */
    public void LoadHttpHeadImage(ImageView imageView,String imgUrl){
        if(imgUrl!=null&&imgUrl.length()>0&&!imgUrl.equals(imageView.getTag())){
            ImageManager.getInstance()
                    .net()
                    .setOption(options)
                    .setImageId(imgUrl)
                    .load(imageView, null);
            imageView.setTag(imgUrl);
        }
    }
    /**
     * 加载本地头像图片
     */
    public void LoadLocalHeadImage(Context context, ImageView imageView, String imgUrl){
        if(imgUrl!=null&&imgUrl.length()>0&&!imgUrl.equals(imageView.getTag())){
            ImageManager.getInstance()
                    .local()
                    .setOption(options)
                    .loadPath(context, imageView, imgUrl);
            imageView.setTag(imgUrl);
        }
    }
}
