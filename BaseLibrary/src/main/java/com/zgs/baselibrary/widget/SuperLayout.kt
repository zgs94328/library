package com.zgs.baselibrary.widget


import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.widget.LinearLayout
import com.zgs.baselibrary.R
import com.zgs.baselibrary.core.*
import kotlinx.android.synthetic.main._ktx_super_layout.view.*


/**
 * Description: 超级布局，用来实现常见的横向图文布局
 * Create by dance, at 2019/5/21
 */
class SuperLayout @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0)
    : LinearLayout(context, attributeSet, defStyleAttr) {

    //左边图片
    var mleftImage: Drawable? = null
        set(value) {
            field = value
            applyAttr()
            applySelf()
        }
    var mleftImageSize = dp2px(34)
        set(value) {
            field = value
            applyAttr()
            applySelf()
        }

    //左边文字
    var mleftText: CharSequence = ""
        set(value) {
            field = value
            applyAttr()
            applySelf()
        }
    var mleftTextColor = Color.parseColor("#222222")
        set(value) {
            field = value
            applyAttr()
            applySelf()
        }
    var mleftTextSize = sp2px(16)
        set(value) {
            field = value
            applyAttr()
            applySelf()
        }
    var mleftTextMarginLeft = dp2px(8)
        set(value) {
            field = value
            applyAttr()
            applySelf()
        }
    var mleftTextMarginRight = dp2px(8)
        set(value) {
            field = value
            applyAttr()
            applySelf()
        }
    var mleftTextMarginTop = 0
        set(value) {
            field = value
            applyAttr()
            applySelf()
        }
    var mleftTextMarginBottom = 0
        set(value) {
            field = value
            applyAttr()
            applySelf()
        }

    //左边子文字
    var mleftSubText: CharSequence = ""
        set(value) {
            field = value
            applyAttr()
            applySelf()
        }
    var mleftSubTextColor = Color.parseColor("#777777")
        set(value) {
            field = value
            applyAttr()
            applySelf()
        }
    var mleftSubTextSize = sp2px(13)
        set(value) {
            field = value
            applyAttr()
            applySelf()
        }

    //中间文字
    var mcenterText: CharSequence = ""
        set(value) {
            field = value
            applyAttr()
            applySelf()
        }
    var mcenterTextColor = Color.parseColor("#222222")
        set(value) {
            field = value
            applyAttr()
            applySelf()
        }
    var mcenterTextSize = sp2px(15)
        set(value) {
            field = value
            applyAttr()
            applySelf()
        }
    var mcenterTextBg: Drawable? = null
        set(value) {
            field = value
            applyAttr()
            applySelf()
        }

    //右边文字
    var mrightText: CharSequence = ""
        set(value) {
            field = value
            applyAttr()
            applySelf()
        }
    var mrightTextColor = Color.parseColor("#777777")
        set(value) {
            field = value
            applyAttr()
            applySelf()
        }
    var mrightTextSize = sp2px(15)
        set(value) {
            field = value
            applyAttr()
            applySelf()
        }
    var mrightTextBg: Drawable? = null
        set(value) {
            field = value
            applyAttr()
            applySelf()
        }
    var mrightTextBgColor = 0
        set(value) {
            field = value
            applyAttr()
            applySelf()
        }
    var mrightTextWidth = 0
        set(value) {
            field = value
            applyAttr()
            applySelf()
        }
    var mrightTextHeight = 0
        set(value) {
            field = value
            applyAttr()
            applySelf()
        }

    //右边图片
    var mrightImage: Drawable? = null
        set(value) {
            field = value
            applyAttr()
            applySelf()
        }
    var mrightImageSize = dp2px(20)
        set(value) {
            field = value
            applyAttr()
            applySelf()
        }
    var mrightImageMarginLeft = dp2px(10)
        set(value) {
            field = value
            applyAttr()
            applySelf()
        }

    //右边图片2
    var mrightImage2: Drawable? = null
        set(value) {
            field = value
            applyAttr()
            applySelf()
        }
    var mrightImage2Size = dp2px(55)
        set(value) {
            field = value
            applyAttr()
            applySelf()
        }
    var mrightImage2MarginLeft = dp2px(10)
        set(value) {
            field = value
            applyAttr()
            applySelf()
        }

    //背景
    var msolid = 0 //填充色
        set(value) {
            field = value
            applyAttr()
            applySelf()
        }
    var mstroke = 0 //边框颜色
        set(value) {
            field = value
            applyAttr()
            applySelf()
        }
    var mstrokeWidth = 0 //边框大小
        set(value) {
            field = value
            applyAttr()
            applySelf()
        }
    var mcorner = 0 //圆角
        set(value) {
            field = value
            applyAttr()
            applySelf()
        }

    //上下分割线
    var mtopLineColor = 0
        set(value) {
            field = value
            applyAttr()
            applySelf()
        }
    var mlineMargin = 0
        set(value) {
            field = value
            applyAttr()
            applySelf()
        }


    var mbottomLineColor = 0
        set(value) {
            field = value
            applyAttr()
            applySelf()
        }
    var mlineSize = dp2px(1)
        set(value) {
            field = value
            applyAttr()
            applySelf()
        }

    //是否启用水波纹
    var menableRipple = true
        set(value) {
            field = value
            applyAttr()
            applySelf()
        }
    var mrippleColor = Color.parseColor("#88999999")
        set(value) {
            field = value
            applyAttr()
            applySelf()
        }
    var mGradientStartColor = 0
        set(value) {
            field = value
            applySelf()
        }
    var mGradientEndColor = 0
        set(value) {
            field = value
            applySelf()
        }
    var mGradientOrientation = GradientDrawable.Orientation.LEFT_RIGHT  //从左到右
        set(value) {
            field = value
            applySelf()
        }

    var leftTextBold = false
    var centerTextBold = false
    var rightTextBold = false

    init {
        val ta = context.obtainStyledAttributes(attributeSet, R.styleable.SuperLayout)
        mleftImage = ta.getDrawable(R.styleable.SuperLayout_sl_leftImageSrc)
        mleftImageSize = ta.getDimensionPixelSize(R.styleable.SuperLayout_sl_leftImageSize, mleftImageSize)

        mleftText = ta.getString(R.styleable.SuperLayout_sl_leftText) ?: ""
        mleftTextColor = ta.getColor(R.styleable.SuperLayout_sl_leftTextColor, mleftTextColor)
        mleftTextSize = ta.getDimensionPixelSize(R.styleable.SuperLayout_sl_leftTextSize, mleftTextSize)
        mleftTextMarginLeft = ta.getDimensionPixelSize(R.styleable.SuperLayout_sl_leftTextMarginLeft, mleftTextMarginLeft)
        mleftTextMarginRight = ta.getDimensionPixelSize(R.styleable.SuperLayout_sl_leftTextMarginRight, mleftTextMarginRight)
        mleftTextMarginTop = ta.getDimensionPixelSize(R.styleable.SuperLayout_sl_leftTextMarginTop, mleftTextMarginTop)
        mleftTextMarginBottom = ta.getDimensionPixelSize(R.styleable.SuperLayout_sl_leftTextMarginBottom, mleftTextMarginBottom)

        mleftSubText = ta.getString(R.styleable.SuperLayout_sl_leftSubText) ?: ""
        mleftSubTextColor = ta.getColor(R.styleable.SuperLayout_sl_leftSubTextColor, mleftSubTextColor)
        mleftSubTextSize = ta.getDimensionPixelSize(R.styleable.SuperLayout_sl_leftSubTextSize, mleftSubTextSize)

        mcenterText = ta.getString(R.styleable.SuperLayout_sl_centerText) ?: ""
        mcenterTextColor = ta.getColor(R.styleable.SuperLayout_sl_centerTextColor, mcenterTextColor)
        mcenterTextSize = ta.getDimensionPixelSize(R.styleable.SuperLayout_sl_centerTextSize, mcenterTextSize)
        mcenterTextBg = ta.getDrawable(R.styleable.SuperLayout_sl_centerTextBg)

        mrightText = ta.getString(R.styleable.SuperLayout_sl_rightText) ?: ""
        mrightTextColor = ta.getColor(R.styleable.SuperLayout_sl_rightTextColor, mrightTextColor)
        mrightTextSize = ta.getDimensionPixelSize(R.styleable.SuperLayout_sl_rightTextSize, mrightTextSize)
        mrightTextBg = ta.getDrawable(R.styleable.SuperLayout_sl_rightTextBg)
        mrightTextBgColor = ta.getColor(R.styleable.SuperLayout_sl_rightTextBgColor, mrightTextBgColor)
        mrightTextWidth = ta.getDimensionPixelSize(R.styleable.SuperLayout_sl_rightTextWidth, mrightTextWidth)
        mrightTextHeight = ta.getDimensionPixelSize(R.styleable.SuperLayout_sl_rightTextHeight, mrightTextHeight)

        mrightImage = ta.getDrawable(R.styleable.SuperLayout_sl_rightImageSrc)
        mrightImageSize = ta.getDimensionPixelSize(R.styleable.SuperLayout_sl_rightImageSize, mrightImageSize)
        mrightImageMarginLeft = ta.getDimensionPixelSize(R.styleable.SuperLayout_sl_rightImageMarginLeft, mrightImageMarginLeft)

        mrightImage2 = ta.getDrawable(R.styleable.SuperLayout_sl_rightImage2Src)
        mrightImage2Size = ta.getDimensionPixelSize(R.styleable.SuperLayout_sl_rightImage2Size, mrightImage2Size)
        mrightImage2MarginLeft = ta.getDimensionPixelSize(R.styleable.SuperLayout_sl_rightImage2MarginLeft, mrightImage2MarginLeft)

        msolid = ta.getColor(R.styleable.SuperLayout_sl_solid, msolid)
        mstroke = ta.getColor(R.styleable.SuperLayout_sl_stroke, mstroke)
        mstrokeWidth = ta.getDimensionPixelSize(R.styleable.SuperLayout_sl_strokeWidth, mstrokeWidth)
        mcorner = ta.getDimensionPixelSize(R.styleable.SuperLayout_sl_corner, mcorner)

        mtopLineColor = ta.getColor(R.styleable.SuperLayout_sl_topLineColor, mtopLineColor)
        mbottomLineColor = ta.getColor(R.styleable.SuperLayout_sl_bottomLineColor, mbottomLineColor)
        mlineSize = ta.getDimensionPixelSize(R.styleable.SuperLayout_sl_lineSize, mlineSize)
        menableRipple = ta.getBoolean(R.styleable.SuperLayout_sl_enableRipple, menableRipple)
        mrippleColor = ta.getColor(R.styleable.SuperLayout_sl_rippleColor, mrippleColor)

        mGradientStartColor = ta.getColor(R.styleable.SuperLayout_sl_gradientStartColor, 0)
        mGradientEndColor = ta.getColor(R.styleable.SuperLayout_sl_gradientEndColor, 0)
        val orientation = ta.getInt(R.styleable.SuperLayout_sl_gradientOrientation, GradientDrawable.Orientation.LEFT_RIGHT.ordinal)
        mGradientOrientation = when(orientation){
            0 -> GradientDrawable.Orientation.TOP_BOTTOM
            1 -> GradientDrawable.Orientation.TR_BL
            2 -> GradientDrawable.Orientation.RIGHT_LEFT
            3 -> GradientDrawable.Orientation.BR_TL
            4 -> GradientDrawable.Orientation.BOTTOM_TOP
            5 -> GradientDrawable.Orientation.BL_TR
            6 -> GradientDrawable.Orientation.LEFT_RIGHT
            else -> GradientDrawable.Orientation.TL_BR
        }
        leftTextBold = ta.getBoolean(R.styleable.SuperLayout_sl_leftTextBold, leftTextBold)
        centerTextBold = ta.getBoolean(R.styleable.SuperLayout_sl_centerTextBold, centerTextBold)
        rightTextBold = ta.getBoolean(R.styleable.SuperLayout_sl_rightTextBold, rightTextBold)

        ta.recycle()
        inflate(context, R.layout._ktx_super_layout, this)
        applyAttr()
        applySelf()
    }

    fun setup(leftImageRes: Int = 0,
              leftText: CharSequence = mleftText,
              leftSubText: CharSequence = mleftSubText,
              centerText: CharSequence = mcenterText,
              rightText: CharSequence = mrightText,
              rightImageRes: Int = 0,
              rightImage2Res: Int = 0) {
        if (leftImageRes != 0) mleftImage = drawable(leftImageRes)
        if (rightImageRes != 0) mrightImage = drawable(rightImageRes)
        if (rightImage2Res != 0) mrightImage2 = drawable(rightImage2Res)
        this.mleftText = leftText
        this.mleftSubText = leftSubText
        this.mcenterText = centerText
        this.mrightText = rightText
        applyAttr()
        applySelf()
    }

    fun applySelf() {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL

        if (msolid != 0 || mstroke != 0 || mGradientEndColor!=0 || mGradientStartColor!=0) {
            val drawable = createDrawable(color = msolid, radius = mcorner.toFloat(), strokeColor = mstroke, strokeWidth = mstrokeWidth,
                    enableRipple = menableRipple, rippleColor = mrippleColor, gradientStartColor = mGradientStartColor,
                    gradientEndColor = mGradientEndColor, gradientOrientation = mGradientOrientation)
            setBackgroundDrawable(drawable)
        }
    }

    fun applyAttr() {
        if(childCount==0)return
        //左边图片
        if (mleftImage == null) {
            ivLeftImage.gone()
        } else {
            ivLeftImage.visible()
            ivLeftImage.setImageDrawable(mleftImage)
            ivLeftImage.widthAndHeight(mleftImageSize, mleftImageSize)
        }

        //左边文字
        if (mleftText.isEmpty()) {
            tvLeftText.gone()
        } else {
            tvLeftText.visible()
            tvLeftText.text = mleftText
            tvLeftText.setTextColor(mleftTextColor)
            tvLeftText.setTextSize(TypedValue.COMPLEX_UNIT_PX, mleftTextSize.toFloat())
            tvLeftText.margin(bottomMargin = mleftTextMarginBottom, topMargin = mleftTextMarginTop)
            llLeft.margin(leftMargin = mleftTextMarginLeft, rightMargin = mleftTextMarginRight)
            if(leftTextBold) tvLeftText.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
        }

        //左边子文字
        if (mleftSubText.isEmpty()) {
            tvLeftSubText.gone()
        } else {
            tvLeftSubText.visible()
            tvLeftSubText.text = mleftSubText
            tvLeftSubText.setTextColor(mleftSubTextColor)
            tvLeftSubText.setTextSize(TypedValue.COMPLEX_UNIT_PX, mleftSubTextSize.toFloat())
        }

        //中间文字
        if (mcenterText.isEmpty()) {
            tvCenterText.invisible()
        } else {
            tvCenterText.visible()
            tvCenterText.text = mcenterText
            tvCenterText.setTextColor(mcenterTextColor)
            tvCenterText.setTextSize(TypedValue.COMPLEX_UNIT_PX, mcenterTextSize.toFloat())
            if (mcenterTextBg != null) tvCenterText.setBackgroundDrawable(mcenterTextBg)
            if(centerTextBold) tvCenterText.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
        }

        //右边文字
        if (mrightText.isEmpty()) {
            tvRightText.gone()
        } else {
            tvRightText.visible()
            tvRightText.text = mrightText
            tvRightText.setTextColor(mrightTextColor)
            tvRightText.setTextSize(TypedValue.COMPLEX_UNIT_PX, mrightTextSize.toFloat())
            if (mrightTextBg != null) tvRightText.setBackgroundDrawable(mrightTextBg)
            if(mrightTextWidth!=0)tvRightText.width(mrightTextWidth)
            if(mrightTextHeight!=0)tvRightText.height(mrightTextHeight)
            if (mrightTextBgColor != 0) tvRightText.setBackgroundColor(mrightTextBgColor)
            if(rightTextBold) tvRightText.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
        }

        //右边图片
        if (mrightImage == null) {
            ivRightImage.gone()
        } else {
            ivRightImage.visible()
            ivRightImage.setImageDrawable(mrightImage)
            ivRightImage.widthAndHeight(mrightImageSize, mrightImageSize)
            ivRightImage.margin(leftMargin = mrightImageMarginLeft)
        }

        //右边图片2
        if (mrightImage2 == null) {
            ivRightImage2.gone()
        } else {
            ivRightImage2.visible()
            ivRightImage2.setImageDrawable(mrightImage2)
            ivRightImage2.widthAndHeight(mrightImage2Size, mrightImage2Size)
            ivRightImage2.margin(leftMargin = mrightImage2MarginLeft)
        }
    }

    private val paint = Paint()
    override fun dispatchDraw(canvas: Canvas) {
        super.dispatchDraw(canvas)
        if (mtopLineColor != 0) {
            paint.color = mtopLineColor
            canvas.drawRect(Rect(0, 0, measuredWidth, mlineSize), paint)
        }
        if (mbottomLineColor != 0) {
            paint.color = mbottomLineColor
            canvas.drawRect(Rect(0, measuredHeight - mlineSize, measuredWidth, measuredHeight), paint)
        }
    }

    fun leftImageView() = ivLeftImage
    fun leftTextView() = tvLeftText
    fun leftSubTextView() = tvLeftSubText
    fun rightTextView() = tvRightText
    fun centerTextView() = tvCenterText
    fun rightImageView() = ivRightImage
    fun rightImageView2() = ivRightImage2
}