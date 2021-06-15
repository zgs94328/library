package com.zgs.baselibrary.util.picker

import android.app.Activity
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.engine.ImageEngine
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.listener.OnResultCallbackListener
import com.zgs.baselibrary.R
import com.zgs.baselibrary.engine.GlideEngine



/**
 * 图片选择器，整合了Matisse，uCrop和Luban。
 */
object ImagePicker {
    private val chooseMode = PictureMimeType.ofAll()
    /**
     * 从Activity中直接开启相机拍照。
     * @param isCrop 是否开启裁剪，默认false
     * @param isCircleDimmedLayer 是否开启圆形裁剪，默认false
     * @param isCompress 是否使用Luban压缩，默认是true
     * @param engine 图片加载引擎默认glide
     * @param onResultCallbackListener 图片返回回调
     */
    fun startCamera(from: Activity
                    , isCrop: Boolean = false
                    , isCircleDimmedLayer: Boolean = false
                    , isCompress: Boolean = true
                    , engine: ImageEngine = GlideEngine.createGlideEngine()
                    , onResultCallbackListener: OnResultCallbackListener<LocalMedia>) {

        PictureSelector.create(from)
                .openCamera(PictureMimeType.ofImage())
                .isEnableCrop(isCrop)
                .circleDimmedLayer(isCircleDimmedLayer)
                .isCompress(isCompress) // 是否压缩
                .loadImageEngine(engine) // 请参考Demo GlideEngine.java
                .forResult(onResultCallbackListener);


    }


    /**
     * 从Activity中直接开启相册。
     * @param type  全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
     * @param isCrop 是否开启裁剪，默认false
     * @param isCircleDimmedLayer 是否开启圆形裁剪，默认false
     * @param isCompress 是否使用Luban压缩，默认是true
     * @param engine 图片加载引擎默认glide
     * @param onResultCallbackListener 图片返回回调
     */
    fun startPicker(from: Activity
                    , type: Int = PictureMimeType.ofImage()
                    , isCrop: Boolean = false
                    , isCircleDimmedLayer: Boolean = false
                    , isCompress: Boolean = true
                    , engine: ImageEngine = GlideEngine.createGlideEngine()
                    , onResultCallbackListener:OnResultCallbackListener<LocalMedia>) {

        PictureSelector.create(from)
                .openGallery(type)
                .isEnableCrop(isCrop)
                .circleDimmedLayer(isCircleDimmedLayer)
                .isCompress(isCompress) // 是否压缩
                .loadImageEngine(engine) // 请参考Demo GlideEngine.java
                .forResult(onResultCallbackListener);


    }

    /**
     * 预览图片
     * @param style 相册风格
     * @param position 预览图片集合的下标
     * @param selectList 图片集合
     */
    fun startPreviewImg(from: Activity
                        , position: Int = 0
                        , selectList: List<LocalMedia>
                        , style: Int = R.style.picture_default_style) {

        PictureSelector.create(from)
                .themeStyle(style)
                .isNotPreviewDownload(true)
                .loadImageEngine(GlideEngine.createGlideEngine()) // 请参考Demo GlideEngine.java
                .openExternalPreview(position, selectList);


    }

    /**
     * 预览视频
     * @param video_path 视频地址
     */
    fun startExternalPictureVideo(from: Activity, video_path: String) {

        PictureSelector.create(from).externalPictureVideo(video_path);

    }


}