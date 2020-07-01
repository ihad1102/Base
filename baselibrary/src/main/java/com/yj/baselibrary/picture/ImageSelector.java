package com.yj.baselibrary.picture;

import android.Manifest;
import android.app.Activity;
import android.content.pm.ActivityInfo;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.language.LanguageConfig;
import com.luck.picture.lib.tools.SdkVersionUtils;
import com.yj.baselibrary.R;
import com.yj.baselibrary.picture.image.GlideCacheEngine;
import com.yj.baselibrary.picture.image.PicassoEngine;
import com.yj.baselibrary.utils.PermissionApplyUtil;
import com.yj.baselibrary.utils.ToastUitl;
import com.yj.baselibrary.base.BaseApplication;

import java.util.List;

/**
 * 图片选择打开选择页面
 */
 public class ImageSelector {
        /**
         * @param activity
         * @param maxSelectNum 最大图片选择数量
         * @param isCrop       是否裁剪
         * @param mPhotos      传入已选图片
         */
        private static final String[] PERMISSIONS ={
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
        };
        //相机

    /**
     *
     * @param activity
     * @param maxSelectNum 大图片选择数量
     * @param isCrop        是否裁剪
     * @param isComPress    是否压缩
     * @param isCircleCrop  是否圆形裁剪
     * @param mPhotos       传入已选图片
     * @param REQUEST_CAMERA  标识
     */
        public static void showCameraBack(Activity activity, int maxSelectNum, boolean isCrop, boolean isComPress, boolean isCircleCrop, List<LocalMedia> mPhotos, int REQUEST_CAMERA) {
            //判断是否是华为手机
//            if(maxSelectNum==1){
//                mPhotos.clear();
//            }
            if(android.os.Build.BRAND.toLowerCase().equals("huawei")){
                if(!PermissionApplyUtil.checkPermissions(activity,PERMISSIONS)){
                    PermissionApplyUtil.requestPermisssion(activity,PERMISSIONS);
                }else{
                    innerShowCamera(activity, maxSelectNum, isCrop,isComPress, isCircleCrop,mPhotos,REQUEST_CAMERA);
                }
            }else{
                //权限提示窗口
                PermissionUtils.permission(PermissionConstants.STORAGE,
                        PermissionConstants.LOCATION,
                        PermissionConstants.CAMERA)
                        .rationale(DialogHelper::showRationaleDialog)
                        .callback(new PermissionUtils.FullCallback(){
                            @Override
                            public void onGranted(List<String> permissionsGranted){
                                innerShowCamera(activity, maxSelectNum, isCrop,isComPress, isCircleCrop,mPhotos,REQUEST_CAMERA);
                            }
                            @Override
                            public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied) {
                                if(!permissionsDeniedForever.isEmpty()){
                                    ToastUitl.show("请开启相机权限");
                                    DialogHelper.showOpenAppSettingDialog(activity,
                                            BaseApplication.getAppContext().getString(R.string.permission_denied_forever_message));
                                }else{
                                    //拒绝权限
                                    ToastUitl.show("您已拒绝相关权限 请手动开启权限");
                                }
                            }
                        })
                        .request();
            }
        }

        private static void innerShowCamera(Activity activity, int maxSelectNum, boolean isCrop, boolean isComPress, boolean isCircleCrop, List<LocalMedia> mPhotos, int REQUEST_CAMERA){
            PictureSelector.create(activity)
                    .openCamera(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                    .imageEngine(PicassoEngine.createPicassoEngine())// 外部传入图片加载引擎，必传项
                    .theme(R.style.picture_my_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture_default_style
                    .setLanguage(LanguageConfig.CHINESE)// 设置语言，默认中文
                    .maxSelectNum(maxSelectNum)// 最大图片选择数量
                    .minSelectNum(1)// 最小选择数量
                    .imageSpanCount(4)// 每行显示个数
                    .selectionMode(PictureConfig.MULTIPLE)// 多选PictureConfig.MULTIPLE or 单选 PictureConfig.SINGLE
                    .isPreviewImage(true)// 是否可预览图片
                    .isCamera(true)// 是否显示拍照按钮
                    .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                    .synOrAsy(true)//同步true或异步false 压缩 默认同步
                    .withAspectRatio(1, 1)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                    .hideBottomControls(true)// 是否显示uCrop工具栏，true不显示  false显示
                    .isGif(false)// 是否显示gif图片
                    .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                    .circleDimmedLayer(isCircleCrop)// 是否圆形裁剪
                    .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                    .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                    .isOpenClickSound(false)// 是否开启点击声音
                    .selectionData(mPhotos)// 是否传入已选图片
                    .isEnableCrop(isCrop)// 是否裁剪
                    .isCompress(isComPress)// 是否压缩
                    .cutOutQuality(90)// 裁剪输出质量 默认100
                    .minimumCompressSize(100)// 小于100kb的图片不压缩
                    .forResult(REQUEST_CAMERA);//结果回调onActivityResult code
        }

        public static void showPictureBack(Activity activity, int maxSelectNum, boolean isCrop, boolean isComPress, boolean isCircleCrop, List<LocalMedia> mPhotos, int CHOOSE_REQUEST) {
 //         if(maxSelectNum==1){
//                mPhotos.clear();
//           }
            if(android.os.Build.BRAND.toLowerCase().equals("huawei")){
                innerShowPicture(activity, maxSelectNum, isCrop,isComPress,isCircleCrop, mPhotos,CHOOSE_REQUEST);
            }else{
                checkPermission(new PermissionUtils.SimpleCallback() {
                    @Override
                    public void onGranted() {
                        innerShowPicture(activity, maxSelectNum, isCrop,isComPress,isCircleCrop, mPhotos,CHOOSE_REQUEST);
                    }

                    @Override
                    public void onDenied() {
                        ToastUtils.showShort("您拒绝了权限，将不能使用该功能！");
                    }
                });}
        }

        private static void innerShowPicture(Activity activity, int maxSelectNum, boolean isCrop, boolean isComPress, boolean isCircleCrop, List<LocalMedia> mPhotos, int CHOOSE_REQUEST){
            PictureSelector.create(activity)
                    .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                    .imageEngine(PicassoEngine.createPicassoEngine())// 外部传入图片加载引擎，必传项
                    .theme(R.style.picture_my_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture_default_style
                    .isWeChatStyle(false)// 是否开启微信图片选择风格
                    .isUseCustomCamera(false)// 是否使用自定义相机
                    .setLanguage(LanguageConfig.CHINESE)// 设置语言，默认中文
                    .isPageStrategy(true)// 是否开启分页策略 & 每页多少条；默认开启
                    .isWithVideoImage(false)// 图片和视频是否可以同选,只在ofAll模式下有效
                    .isMaxSelectEnabledMask(true)// 选择数到了最大阀值列表是否启用蒙层效果
                    .loadCacheResourcesCallback(GlideCacheEngine.createCacheEngine())// 获取图片资源缓存，主要是解决华为10部分机型在拷贝文件过多时会出现卡的问题，这里可以判断只在会出现一直转圈问题机型上使用
                    .maxSelectNum(maxSelectNum)// 最大图片选择数量
                    .minSelectNum(1)// 最小选择数量
                    .imageSpanCount(4)// 每行显示个数
                    .isReturnEmpty(false)// 未选择数据时点击按钮是否可以返回
                    .closeAndroidQChangeWH(true)//如果图片有旋转角度则对换宽高,默认为true
                    .closeAndroidQChangeVideoWH(!SdkVersionUtils.checkedAndroid_Q())// 如果视频有旋转角度则对换宽高,默认为false
                    .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)// 设置相册Activity方向，不设置默认使用系统
                    .isSingleDirectReturn(true)// 单选模式下是否直接返回，PictureConfig.SINGLE模式下有效
                    .isPreviewImage(true)// 是否可预览图片
                    .isCamera(true)// 是否显示拍照按钮
                    .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                    .isEnableCrop(isCrop)// 是否裁剪
                    .isCompress(isComPress)// 是否压缩
                    .synOrAsy(true)//同步true或异步false 压缩 默认同步
                    .withAspectRatio(16, 9)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                    .hideBottomControls(true)// 是否显示uCrop工具栏，true不显示  false显示
                    .isGif(false)// 是否显示gif图片
                    .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                    .circleDimmedLayer(isCircleCrop)// 是否圆形裁剪
                    .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                    .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                    .isOpenClickSound(false)// 是否开启点击声音
                    .selectionData(mPhotos)// 是否传入已选图片
                    .cutOutQuality(90)// 裁剪输出质量 默认100
                    .minimumCompressSize(100)// 小于多少kb的图片不压缩
                    .forResult(CHOOSE_REQUEST);
        }
        /**
         * 检查权限
         * @param callback 权限回调
         */
        private static void checkPermission(PermissionUtils.SimpleCallback callback){
            PermissionUtils.permission(
                    PermissionConstants.STORAGE,
                    PermissionConstants.LOCATION,
                    PermissionConstants.CAMERA)
                    .rationale(DialogHelper::showRationaleDialog)
                    .callback(callback)
                    .request();
        }

 }

