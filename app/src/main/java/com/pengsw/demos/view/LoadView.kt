package com.pengsw.demos.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import kotlin.math.min


/**
 * @date      2021/5/6
 * @author    Pengshuwen
 * @describe  加载动画
 */
class LoadView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var mWidth = 0
    private var mHeight = 0

    private val whiteColor = Color.parseColor("#ffffff")
    private val transparentBlackColor = Color.parseColor("#33000000")
    private var outerRingColor = Color.parseColor("#97D4A9")

    private var angle = 0
    private var progress = 40
    private var max = 100

    init {
        setBackgroundColor(Color.parseColor("#44C670"))

        start()
    }

    val mPaint = Paint().apply {
        color = whiteColor
        style = Paint.Style.STROKE
    }

    val mBgPaint = Paint().apply {
        color = transparentBlackColor
        style = Paint.Style.FILL
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)



        canvas?.drawCircle((mWidth / 2).toFloat(), (mHeight / 2).toFloat(), (mWidth / 2).toFloat(), mBgPaint)

        mPaint.strokeWidth = mWidth / 50f
        mPaint.color = outerRingColor
        canvas?.drawCircle((mWidth / 2).toFloat(), (mHeight / 2).toFloat(), (mWidth / 2).toFloat() - (mPaint.strokeWidth / 2), mPaint)

        mPaint.strokeWidth = mWidth / 30f
        mPaint.color = whiteColor
//        canvas?.drawArc((mWdith / 2).toFloat(), (mHeight / 2).toFloat(), (mWdith / 2).toFloat() - ((mWdith / 50f) + 20f), mPaint)


        canvas?.save()
        canvas?.rotate(angle.toFloat(), (mWidth / 2).toFloat(), (mHeight / 2).toFloat())
        val margin = (mWidth / 50f) * 2.5f
        val endAngle = progress * (360 / max)
        canvas?.drawArc(margin, margin, mWidth - margin, mHeight - margin, 0f, endAngle.toFloat(), false, mPaint)
        canvas?.restore()

    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = measuredWidth
        mHeight = measuredHeight
        if (mWidth > 0 && mHeight > 0) {
            mWidth = min(mHeight, mWidth)
            mHeight = min(mHeight, mWidth)
            setMeasuredDimension(mWidth, mHeight)
        }
    }


    fun start() {

        val anim = ValueAnimator.ofInt(0, 360)
        anim.duration = 900L
        anim.repeatCount = ValueAnimator.INFINITE
        anim.addUpdateListener {
            angle = it.animatedValue as Int
            Log.d("", "旋转角度$angle °c")

            if (angle>=360){
                progress += 40
                if (progress >= 360) {
                    progress = 350
                }
                Log.d("", "绘制角度$progress °c")
            }
            invalidate()

        }
        anim.start()
    }

}