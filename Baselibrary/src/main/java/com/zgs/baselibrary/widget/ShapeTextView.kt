package com.zgs.baselibrary.widget


import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.zgs.baselibrary.R
import com.zgs.baselibrary.core.createDrawable
import com.zgs.baselibrary.core.dp2px
import com.zgs.baselibrary.core.sizeDrawable

/**
 * Description: 能设置Shape的TextView
 * Create by dance, at 2019/5/21
 */
open class ShapeTextView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0)
    : AppCompatTextView(context, attributeSet, defStyleAttr) {

    var mDrawableWidth = 0
        set(value) {
            field = value
            sizeDrawable(width = mDrawableWidth, height = mDrawableHeight)
        }
    var mDrawableHeight = 0
        set(value) {
            field = value
            sizeDrawable(width = mDrawableWidth, height = mDrawableHeight)
        }
    //背景
    var mSolid = 0 //填充色
        set(value) {
            field = value
            applySelf()
        }
    var mStroke = 0 //边框颜色
        set(value) {
            field = value
            applySelf()
        }
    var mStrokeWidth = 0 //边框大小
        set(value) {
            field = value
            applySelf()
        }
    var mCorner = 0 //圆角
        set(value) {
            field = value
            applySelf()
        }

    //是否启用水波纹
    var mEnableRipple = true
        set(value) {
            field = value
            applySelf()
        }
    var mRippleColor = Color.parseColor("#88999999")
        set(value) {
            field = value
            applySelf()
        }
    //上下分割线
    var mTopLineColor = 0
        set(value) {
            field = value
            applySelf()
        }
    var mBottomLineColor = 0
        set(value) {
            field = value
            applySelf()
        }
    var mLineSize = dp2px(1)
        set(value) {
            field = value
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

    init {
        val ta = context.obtainStyledAttributes(attributeSet, R.styleable.ShapeTextView)
        mDrawableWidth = ta.getDimensionPixelSize(R.styleable.ShapeTextView_stv_drawableWidth, mDrawableWidth)
        mDrawableHeight = ta.getDimensionPixelSize(R.styleable.ShapeTextView_stv_drawableHeight, mDrawableHeight)
        val size = ta.getDimensionPixelSize(R.styleable.ShapeTextView_stv_drawableSize, 0)
        if (size != 0) {
            mDrawableWidth = size
            mDrawableHeight = size
        }

        mSolid = ta.getColor(R.styleable.ShapeTextView_stv_solid, mSolid)
        mStroke = ta.getColor(R.styleable.ShapeTextView_stv_stroke, mStroke)
        mStrokeWidth = ta.getDimensionPixelSize(R.styleable.ShapeTextView_stv_strokeWidth, mStrokeWidth)
        mCorner = ta.getDimensionPixelSize(R.styleable.ShapeTextView_stv_corner, mCorner)

        mEnableRipple = ta.getBoolean(R.styleable.ShapeTextView_stv_enableRipple, mEnableRipple)
        mRippleColor = ta.getColor(R.styleable.ShapeTextView_stv_rippleColor, mRippleColor)

        mTopLineColor = ta.getColor(R.styleable.ShapeTextView_stv_topLineColor, mTopLineColor)
        mBottomLineColor = ta.getColor(R.styleable.ShapeTextView_stv_bottomLineColor, mBottomLineColor)
        mLineSize = ta.getDimensionPixelSize(R.styleable.ShapeTextView_stv_lineSize, mLineSize)

        mGradientStartColor = ta.getColor(R.styleable.ShapeTextView_stv_gradientStartColor, 0)
        mGradientEndColor = ta.getColor(R.styleable.ShapeTextView_stv_gradientEndColor, 0)
        val orientation = ta.getInt(R.styleable.ShapeTextView_stv_gradientOrientation, GradientDrawable.Orientation.LEFT_RIGHT.ordinal)
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
        ta.recycle()
        if (mDrawableWidth != 0 && mDrawableHeight != 0) {
            sizeDrawable(width = mDrawableWidth, height = mDrawableHeight)
        }
        applySelf()
    }


    fun applySelf() {
        if (mSolid != 0 || mStroke != 0 || mGradientEndColor!=0 || mGradientStartColor!=0) {
            val drawable = createDrawable(color = mSolid, radius = mCorner.toFloat(), strokeColor = mStroke, strokeWidth = mStrokeWidth,
                    enableRipple = mEnableRipple, rippleColor = mRippleColor, gradientStartColor = mGradientStartColor,
            gradientEndColor = mGradientEndColor, gradientOrientation = mGradientOrientation)
            setBackgroundDrawable(drawable)
        }
    }

    val topLine = Rect(0,0,0,0)
    val bottomLine = Rect(0,0,0,0)
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        topLine.right = measuredWidth
        topLine.bottom = mLineSize
        bottomLine.top = measuredHeight - mLineSize
        bottomLine.right = measuredWidth
        bottomLine.bottom = measuredHeight
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (mTopLineColor != 0) {
            paint.color = mTopLineColor
            canvas.drawRect(topLine, paint)
        }
        if (mBottomLineColor != 0) {
            paint.color = mBottomLineColor
            canvas.drawRect(bottomLine, paint)
        }
    }

    fun setup(drawableWidth: Int = mDrawableWidth, drawableHeight: Int = mDrawableHeight,
              solid: Int = mSolid, stroke: Int = mStroke, strokeWidth: Int = mStrokeWidth,
              corner: Int = mCorner, enableRipple: Boolean = mEnableRipple, rippleColor: Int = mRippleColor,
              topLineColor: Int = mTopLineColor, bottomLineColor: Int = mBottomLineColor, lineSize: Int = mLineSize){
        mDrawableWidth = drawableWidth
        mDrawableHeight = drawableHeight
        mSolid = solid
        mStroke = stroke
        mStrokeWidth = strokeWidth
        mCorner = corner
        mEnableRipple = enableRipple
        mRippleColor = rippleColor
        mTopLineColor = topLineColor
        mBottomLineColor = bottomLineColor
        mLineSize = lineSize
        applySelf()
    }
}
