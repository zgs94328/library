package com.zgs.baselibrary.widget.grid

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.text.TextUtils
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.animators.AnimationType
import com.luck.picture.lib.broadcast.BroadcastAction
import com.luck.picture.lib.broadcast.BroadcastManager
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.decoration.GridSpacingItemDecoration
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.listener.OnResultCallbackListener
import com.luck.picture.lib.style.PictureCropParameterStyle
import com.luck.picture.lib.style.PictureParameterStyle
import com.luck.picture.lib.style.PictureSelectorUIStyle
import com.luck.picture.lib.style.PictureWindowAnimationStyle
import com.luck.picture.lib.tools.ScreenUtils
import com.luck.picture.lib.tools.SdkVersionUtils
import com.luck.picture.lib.tools.ToastUtils
import com.zgs.baselibrary.R
import com.zgs.baselibrary.engine.GlideEngine
import com.zgs.baselibrary.util.Utils


/**
 * 作者　: zgs
 * 时间　: 2021/3/12
 * 描述　:
 */
class NineGridImageView : RecyclerView {
    private var mAdapter: GridImageAdapter? = null
    private val maxSelectNum = 9 //图片选择最大数
    private val aspect_ratio_x = 0 //裁剪比例
    private val aspect_ratio_y = 0
    private var themeId = R.style.picture_default_style //主题样式
    private var cropEnble = true //是否裁剪
    private var compressEnble = true //是否压缩
    val mode: Boolean = true// 选择相册还是单独拍照
    private val chooseMode = PictureMimeType.ofAll() // 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
    private val mSelectorUIStyle: PictureSelectorUIStyle = PictureSelectorUIStyle.ofDefaultStyle()
    private val isWeChatStyle = false
    private val animationMode = AnimationType.DEFAULT_ANIMATION
    private val mWindowAnimationStyle = PictureWindowAnimationStyle.ofDefaultWindowAnimationStyle()//默认启动动画
    private var onResultCallbackListener: OnResultCallbackListener<LocalMedia>? = null
    private val mPictureParameterStyle: PictureParameterStyle? = getDefaultStyle()
    private var mCropParameterStyle: PictureCropParameterStyle? = null
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr) {
        init(context,attributeSet)
    }

    private fun init(context: Context, attributeSet: AttributeSet?) {
        val manager = FullyGridLayoutManager(context,
                4, GridLayoutManager.VERTICAL, false)
        layoutManager = manager

        addItemDecoration(GridSpacingItemDecoration(4,
                ScreenUtils.dip2px(context, 8f), false))
        mAdapter = GridImageAdapter(context,onAddPicClickListener)
//        List<LocalMedia> list = new ArrayList<>();
//        LocalMedia m = new LocalMedia();
//        m.setPath("https://wx1.sinaimg.cn/mw690/006e0i7xly1gaxqq5m7t8j31311g2ao6.jpg");
//        LocalMedia m1 = new LocalMedia();
//        m1.setPath("https://ww1.sinaimg.cn/bmiddle/bcd10523ly1g96mg4sfhag20c806wu0x.gif");
//        list.add(m);
//        list.add(m1);
//        mAdapter.setList(list);
        mAdapter!!.setSelectMax(maxSelectNum)
        adapter = mAdapter

        mAdapter!!.setOnItemClickListener { v, position ->
            val selectList = mAdapter!!.data
            if (selectList.size > 0) {
                val media = selectList[position]
                val mimeType = media.mimeType
                val mediaType = PictureMimeType.getMimeType(mimeType)
                when (mediaType) {
                    PictureConfig.TYPE_VIDEO ->                         // 预览视频
                        PictureSelector.create(Utils.getActivityFromContext(context))
                                .themeStyle(R.style.picture_default_style)
                                .setPictureStyle(mPictureParameterStyle) // 动态自定义相册主题
                                .externalPictureVideo(if (TextUtils.isEmpty(media.androidQToPath)) media.path else media.androidQToPath)
                    PictureConfig.TYPE_AUDIO ->                         // 预览音频
                        PictureSelector.create(Utils.getActivityFromContext(context))
                                .externalPictureAudio(if (PictureMimeType.isContent(media.path)) media.androidQToPath else media.path)
                    else ->                         // 预览图片 可自定长按保存路径
//                        PictureWindowAnimationStyle animationStyle = new PictureWindowAnimationStyle();
//                        animationStyle.activityPreviewEnterAnimation = R.anim.picture_anim_up_in;
//                        animationStyle.activityPreviewExitAnimation = R.anim.picture_anim_down_out;
                        PictureSelector.create(Utils.getActivityFromContext(context))
                                .themeStyle(R.style.picture_default_style) // xml设置主题
                                .setPictureStyle(mPictureParameterStyle) // 动态自定义相册主题
                                //.setPictureWindowAnimationStyle(animationStyle)// 自定义页面启动动画
                                .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED) // 设置相册Activity方向，不设置默认使用系统
                                .isNotPreviewDownload(true) // 预览图片长按是否可以下载
                                //.bindCustomPlayVideoCallback(new MyVideoSelectedPlayCallback(getContext()))// 自定义播放回调控制，用户可以使用自己的视频播放界面
                                .imageEngine(GlideEngine.createGlideEngine()) // 外部传入图片加载引擎，必传项
                                .openExternalPreview(position, selectList)
                }
            }
        }
        // 注册广播

        // 注册广播
        BroadcastManager.getInstance(getContext()).registerReceiver(broadcastReceiver,
                BroadcastAction.ACTION_DELETE_PREVIEW_POSITION)
    }
    private val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            if (TextUtils.isEmpty(action)) {
                return
            }
            if (BroadcastAction.ACTION_DELETE_PREVIEW_POSITION == action) {
                // 外部预览删除按钮回调
                val extras = intent.extras
                if (extras != null) {
                    val position = extras.getInt(PictureConfig.EXTRA_PREVIEW_DELETE_POSITION)
                    ToastUtils.s(getContext(), "delete image index:$position")
                    mAdapter!!.remove(position)
                    mAdapter!!.notifyItemRemoved(position)
                }
            }
        }
    }
    fun setResultCallback(onResultCallbackListener: OnResultCallbackListener<LocalMedia>): NineGridImageView {
        this.onResultCallbackListener = onResultCallbackListener
        return this
    }
    fun setList(selectList:MutableList<LocalMedia>){
        mAdapter!!.setList(selectList)
        mAdapter!!.notifyDataSetChanged()
    }
    private val onAddPicClickListener: GridImageAdapter.onAddPicClickListener = GridImageAdapter.onAddPicClickListener {
        if (mode) {
            // 进入相册 以下是例子：不需要的api可以不写
            PictureSelector.create(Utils.getActivityFromContext(context))
                    .openGallery(chooseMode) // 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                    .imageEngine(GlideEngine.createGlideEngine()) // 外部传入图片加载引擎，必传项
                    //.theme(themeId)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style v2.3.3后 建议使用setPictureStyle()动态方式
                    .setPictureUIStyle(mSelectorUIStyle) //.setPictureStyle(mPictureParameterStyle)// 动态自定义相册主题
                    //.setPictureCropStyle(mCropParameterStyle)// 动态自定义裁剪主题
                    .setPictureWindowAnimationStyle(mWindowAnimationStyle) // 自定义相册启动退出动画
                    .isWeChatStyle(isWeChatStyle) // 是否开启微信图片选择风格
                    .isUseCustomCamera(false) // 是否使用自定义相机
//                    .setLanguage(language) // 设置语言，默认中文
                    .isPageStrategy(true) // 是否开启分页策略 & 每页多少条；默认开启
                    .setRecyclerAnimationMode(animationMode) // 列表动画效果
                    .isWithVideoImage(true) // 图片和视频是否可以同选,只在ofAll模式下有效
                    .isMaxSelectEnabledMask(true) // 选择数到了最大阀值列表是否启用蒙层效果
                    //.isAutomaticTitleRecyclerTop(false)// 连续点击标题栏RecyclerView是否自动回到顶部,默认true
                    //.loadCacheResourcesCallback(GlideCacheEngine.createCacheEngine())// 获取图片资源缓存，主要是解决华为10部分机型在拷贝文件过多时会出现卡的问题，这里可以判断只在会出现一直转圈问题机型上使用
                    //.setOutputCameraPath(createCustomCameraOutPath())// 自定义相机输出目录
                    //.setButtonFeatures(CustomCameraView.BUTTON_STATE_BOTH)// 设置自定义相机按钮状态
                    .maxSelectNum(maxSelectNum) // 最大图片选择数量
                    .minSelectNum(1) // 最小选择数量
                    .maxVideoSelectNum(1) // 视频最大选择数量
                    //.minVideoSelectNum(1)// 视频最小选择数量
                    //.closeAndroidQChangeVideoWH(!SdkVersionUtils.checkedAndroid_Q())// 关闭在AndroidQ下获取图片或视频宽高相反自动转换
                    .imageSpanCount(4) // 每行显示个数
                    .isReturnEmpty(false) // 未选择数据时点击按钮是否可以返回
                    .closeAndroidQChangeWH(true) //如果图片有旋转角度则对换宽高,默认为true
                    .closeAndroidQChangeVideoWH(!SdkVersionUtils.checkedAndroid_Q()) // 如果视频有旋转角度则对换宽高,默认为false
                    //.isAndroidQTransform(true)// 是否需要处理Android Q 拷贝至应用沙盒的操作，只针对compress(false); && .isEnableCrop(false);有效,默认处理
                    .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED) // 设置相册Activity方向，不设置默认使用系统
                    .isOriginalImageControl(true) // 是否显示原图控制按钮，如果设置为true则用户可以自由选择是否使用原图，压缩、裁剪功能将会失效
                    //.bindCustomPlayVideoCallback(new MyVideoSelectedPlayCallback(getContext()))// 自定义视频播放回调控制，用户可以使用自己的视频播放界面
                    //.bindCustomPreviewCallback(new MyCustomPreviewInterfaceListener())// 自定义图片预览回调接口
                    //.bindCustomCameraInterfaceListener(new MyCustomCameraInterfaceListener())// 提供给用户的一些额外的自定义操作回调
                    //.cameraFileName(System.currentTimeMillis() +".jpg")    // 重命名拍照文件名、如果是相册拍照则内部会自动拼上当前时间戳防止重复，注意这个只在使用相机时可以使用，如果使用相机又开启了压缩或裁剪 需要配合压缩和裁剪文件名api
                    //.renameCompressFile(System.currentTimeMillis() +".jpg")// 重命名压缩文件名、 如果是多张压缩则内部会自动拼上当前时间戳防止重复
                    //.renameCropFileName(System.currentTimeMillis() + ".jpg")// 重命名裁剪文件名、 如果是多张裁剪则内部会自动拼上当前时间戳防止重复
                    .selectionMode(if (true) PictureConfig.MULTIPLE else PictureConfig.SINGLE) // 多选 or 单选
                    .isSingleDirectReturn(false) // 单选模式下是否直接返回，PictureConfig.SINGLE模式下有效
                    .isPreviewImage(true) // 是否可预览图片
                    .isPreviewVideo(true) // 是否可预览视频
                    //.querySpecifiedFormatSuffix(PictureMimeType.ofJPEG())// 查询指定后缀格式资源
                    .isEnablePreviewAudio(true) // 是否可播放音频
                    .isCamera(true) // 是否显示拍照按钮
                    //.isMultipleSkipCrop(false)// 多图裁剪时是否支持跳过，默认支持
                    //.isMultipleRecyclerAnimation(false)// 多图裁剪底部列表显示动画效果
                    .isZoomAnim(true) // 图片列表点击 缩放效果 默认true
                    //.imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg,Android Q使用PictureMimeType.PNG_Q
                    .isEnableCrop(cropEnble) // 是否裁剪
                    //.basicUCropConfig()//对外提供所有UCropOptions参数配制，但如果PictureSelector原本支持设置的还是会使用原有的设置
                    .isCompress(compressEnble) // 是否压缩
                    //.compressQuality(80)// 图片压缩后输出质量 0~ 100
                    .synOrAsy(false) //同步true或异步false 压缩 默认同步
                    //.queryMaxFileSize(10)// 只查多少M以内的图片、视频、音频  单位M
                    //.compressSavePath(getPath())//压缩图片保存地址
                    //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效 注：已废弃
                    //.glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度 注：已废弃
                    .withAspectRatio(aspect_ratio_x, aspect_ratio_y) // 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
//                    .hideBottomControls(!cb_hide.isChecked()) // 是否显示uCrop工具栏，默认不显示
                    .isGif(true) // 是否显示gif图片
                    //.isWebp(false)// 是否显示webp图片,默认显示
                    //.isBmp(false)//是否显示bmp图片,默认显示
                    .freeStyleCropEnabled(true) // 裁剪框是否可拖拽
                    .circleDimmedLayer(false) // 是否圆形裁剪
                    //.setCropDimmedColor(ContextCompat.getColor(getContext(), R.color.app_color_white))// 设置裁剪背景色值
                    //.setCircleDimmedBorderColor(ContextCompat.getColor(getApplicationContext(), R.color.app_color_white))// 设置圆形裁剪边框色值
                    //.setCircleStrokeWidth(3)// 设置圆形裁剪边框粗细
                    .showCropFrame(true) // 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                    .showCropGrid(true) // 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                    .isOpenClickSound(false) // 是否开启点击声音
                    .selectionData(mAdapter!!.data) // 是否传入已选图片
                    //.isDragFrame(false)// 是否可拖动裁剪框(固定)
                    //.videoMinSecond(10)// 查询多少秒以内的视频
                    //.videoMaxSecond(15)// 查询多少秒以内的视频
                    //.recordVideoSecond(10)//录制视频秒数 默认60s
                    //.isPreviewEggs(true)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                    //.cropCompressQuality(90)// 注：已废弃 改用cutOutQuality()
                    .cutOutQuality(90) // 裁剪输出质量 默认100
                    .minimumCompressSize(100) // 小于多少kb的图片不压缩
                    //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                    //.cropImageWideHigh()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                    //.rotateEnabled(false) // 裁剪是否可旋转图片
                    //.scaleEnabled(false)// 裁剪是否可放大缩小图片
                    //.videoQuality()// 视频录制质量 0 or 1
                    //.forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
                    .forResult(onResultCallbackListener)
        }
    }
    private fun getDefaultStyle(): PictureParameterStyle? {
        // 相册主题
        val mPictureParameterStyle = PictureParameterStyle()
        // 是否改变状态栏字体颜色(黑白切换)
        mPictureParameterStyle.isChangeStatusBarFontColor = false
        // 是否开启右下角已完成(0/9)风格
        mPictureParameterStyle.isOpenCompletedNumStyle = false
        // 是否开启类似QQ相册带数字选择风格
        mPictureParameterStyle.isOpenCheckNumStyle = false
        // 相册状态栏背景色
        mPictureParameterStyle.pictureStatusBarColor = Color.parseColor("#393a3e")
        // 相册列表标题栏背景色
        mPictureParameterStyle.pictureTitleBarBackgroundColor = Color.parseColor("#393a3e")
        // 相册父容器背景色
        mPictureParameterStyle.pictureContainerBackgroundColor = Color.parseColor("#000000")
        // 相册列表标题栏右侧上拉箭头
        mPictureParameterStyle.pictureTitleUpResId = R.drawable.picture_icon_arrow_up
        // 相册列表标题栏右侧下拉箭头
        mPictureParameterStyle.pictureTitleDownResId = R.drawable.picture_icon_arrow_down
        // 相册文件夹列表选中圆点
        mPictureParameterStyle.pictureFolderCheckedDotStyle = R.drawable.picture_orange_oval
        // 相册返回箭头
        mPictureParameterStyle.pictureLeftBackIcon = R.drawable.picture_icon_back
        // 标题栏字体颜色
        mPictureParameterStyle.pictureTitleTextColor = ContextCompat.getColor(context, R.color.picture_color_white)
        // 相册右侧取消按钮字体颜色  废弃 改用.pictureRightDefaultTextColor和.pictureRightDefaultTextColor
        mPictureParameterStyle.pictureCancelTextColor = ContextCompat.getColor(context, R.color.picture_color_white)
        // 选择相册目录背景样式
        mPictureParameterStyle.pictureAlbumStyle = R.drawable.picture_new_item_select_bg
        // 相册列表勾选图片样式
        mPictureParameterStyle.pictureCheckedStyle = R.drawable.picture_checkbox_selector
        // 相册列表底部背景色
        mPictureParameterStyle.pictureBottomBgColor = ContextCompat.getColor(context, R.color.picture_color_grey)
        // 已选数量圆点背景样式
        mPictureParameterStyle.pictureCheckNumBgStyle = R.drawable.picture_num_oval
        // 相册列表底下预览文字色值(预览按钮可点击时的色值)
        mPictureParameterStyle.picturePreviewTextColor = ContextCompat.getColor(context, R.color.picture_color_fa632d)
        // 相册列表底下不可预览文字色值(预览按钮不可点击时的色值)
        mPictureParameterStyle.pictureUnPreviewTextColor = ContextCompat.getColor(context, R.color.picture_color_white)
        // 相册列表已完成色值(已完成 可点击色值)
        mPictureParameterStyle.pictureCompleteTextColor = ContextCompat.getColor(context, R.color.picture_color_fa632d)
        // 相册列表未完成色值(请选择 不可点击色值)
        mPictureParameterStyle.pictureUnCompleteTextColor = ContextCompat.getColor(context, R.color.picture_color_white)
        // 预览界面底部背景色
        mPictureParameterStyle.picturePreviewBottomBgColor = ContextCompat.getColor(context, R.color.picture_color_grey)
        // 外部预览界面删除按钮样式
        mPictureParameterStyle.pictureExternalPreviewDeleteStyle = R.drawable.picture_icon_delete
        // 原图按钮勾选样式  需设置.isOriginalImageControl(true); 才有效
        mPictureParameterStyle.pictureOriginalControlStyle = R.drawable.picture_original_wechat_checkbox
        // 原图文字颜色 需设置.isOriginalImageControl(true); 才有效
        mPictureParameterStyle.pictureOriginalFontColor = Color.parseColor("#ffffff")
        // 外部预览界面是否显示删除按钮
        mPictureParameterStyle.pictureExternalPreviewGonePreviewDelete = true
        // 设置NavBar Color SDK Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP有效
        mPictureParameterStyle.pictureNavBarColor = Color.parseColor("#393a3e")
        // 文件夹字体颜色
        mPictureParameterStyle.folderTextColor = Color.parseColor("#4d4d4d")
        // 文件夹字体大小
        mPictureParameterStyle.folderTextSize = 16
        //        // 自定义相册右侧文本内容设置
//        mPictureParameterStyle.pictureRightDefaultText = "";
//        // 自定义相册未完成文本内容
//        mPictureParameterStyle.pictureUnCompleteText = "";
//        // 自定义相册完成文本内容
//        mPictureParameterStyle.pictureCompleteText = "";
//        // 自定义相册列表不可预览文字
//        mPictureParameterStyle.pictureUnPreviewText = "";
//        // 自定义相册列表预览文字
//        mPictureParameterStyle.picturePreviewText = "";
//
//        // 自定义相册标题字体大小
//        mPictureParameterStyle.pictureTitleTextSize = 18;
//        // 自定义相册右侧文字大小
//        mPictureParameterStyle.pictureRightTextSize = 14;
//        // 自定义相册预览文字大小
//        mPictureParameterStyle.picturePreviewTextSize = 14;
//        // 自定义相册完成文字大小
//        mPictureParameterStyle.pictureCompleteTextSize = 14;
//        // 自定义原图文字大小
//        mPictureParameterStyle.pictureOriginalTextSize = 14;

        // 裁剪主题
        mCropParameterStyle = PictureCropParameterStyle(
                Color.parseColor("#393a3e"),
                Color.parseColor("#393a3e"),
                Color.parseColor("#393a3e"),
                Color.parseColor("#ffffff"),
                mPictureParameterStyle.isChangeStatusBarFontColor)
        return mPictureParameterStyle
    }
}