package com.pengsw.demos.view

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.annotation.IntRange
import com.pengsw.demos.drawTextWithCenterPoint
import kotlin.math.min


/**
 * @date      2021/5/7
 * @author    Pengshuwen
 * @describe  幸运转盘
 */
class RotaryTableView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    private var mWidth = 0
    private var mHeight = 0

    private var angle = 0f

    val paint1 = Paint().apply {
        color = Color.parseColor("#fff4d6")
        style = Paint.Style.FILL
        isAntiAlias = true
    }
    val paint2 = Paint().apply {
        color = Color.parseColor("#ffffff")
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    val paint3 = Paint().apply {
        color = Color.parseColor("#ffdc79")
        style = Paint.Style.STROKE
        strokeWidth = 10f
        isAntiAlias = true
    }

    val mTextPaint = Paint().apply {
        color = Color.parseColor("#000000")
        style = Paint.Style.FILL
        textSize = 30f
        isAntiAlias = true
    }

    val mPointerPaint = Paint().apply {
        color = Color.parseColor("#C81518")
        style = Paint.Style.FILL
        isAntiAlias = true
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)


        canvas?.save()
        canvas?.rotate(angle, mWidth / 2f, mHeight / 2f)
        drawBg(canvas)
        drawText(canvas)
        drawCircle(canvas)
        canvas?.restore()

        drawPointer(canvas)
    }


    private fun drawCircle(canvas: Canvas?) {
        val margin = paint3.strokeWidth / 2
        paint3.style = Paint.Style.STROKE
        canvas?.drawCircle(
            mWidth / 2f,
            mHeight / 2f,
            (mWidth / 2F) - margin,
            paint3
        )
        paint3.style = Paint.Style.FILL
        canvas?.drawCircle(mWidth / 2f, mHeight / 2f, 20f, paint3)
    }

    private fun drawText(canvas: Canvas?) {
        canvas?.save()
        for (i in 0..10) {
            canvas?.rotate(36f, (mWidth / 2).toFloat(), (mHeight / 2).toFloat())
            if (i % 2 == 0) {
                canvas?.drawTextWithCenterPoint(mWidth / 2, mHeight / 4, "举举", mTextPaint)
            } else {
                canvas?.drawTextWithCenterPoint(mWidth / 2, mHeight / 4, "大树", mTextPaint)
            }
        }
        canvas?.restore()
    }

    private fun drawBg(canvas: Canvas?) {
        canvas?.save()
        for (i in 0..10) {
            val paint = if (i % 2 == 0) paint1 else paint2
            canvas?.rotate(36f, (mWidth / 2).toFloat(), (mHeight / 2).toFloat())
            canvas?.drawArc(0f, 0f, mWidth.toFloat(), mHeight.toFloat(), 0f, 36f, true, paint)
        }
        canvas?.restore()
    }


    private fun drawPointer(canvas: Canvas?) {

        canvas?.drawLine(mWidth / 2f, mHeight / 2f, mWidth / 2f, (mHeight / 4) + 50f, mPointerPaint)


        //        val x1=getCircleX(180.0,mWidth/2,(mHeight/4))
//        val y1=getCircleX(180.0,mHeight/2,(mHeight/4))

        val x1 = (mWidth / 2).toFloat()
        val y1 = (mHeight / 4).toFloat()

        val x2 = (mWidth / 2) + 50f
        val y2 = (mHeight / 2).toFloat()

        val x3 = (mWidth / 2) - 50f
        val y3 = (mHeight / 2).toFloat()

        val path = Path().apply {
            moveTo(x1.toFloat(), y1.toFloat())
            lineTo(x2, y2)
            lineTo(x3, y3)
            close()
        }
        canvas?.drawPath(path, mPointerPaint)



        canvas?.drawCircle((mWidth / 2).toFloat(), (mHeight / 2).toFloat(), 50f, mPointerPaint)

        mPointerPaint.color = Color.parseColor("#ffffff")
        canvas?.drawCircle((mWidth / 2).toFloat(), (mHeight / 2).toFloat(), 15f, mPointerPaint)
        mPointerPaint.color = Color.parseColor("#C81518")
    }


    @SuppressLint("SupportAnnotationUsage")
    @IntRange(from = 0, to = 360)
    fun setAngle(angle: Float) {
        this.angle = angle
    }

    fun start() {

        val target = 360f * 6 + angle
        val anim = ValueAnimator.ofFloat(0f, target)
        anim.duration = 4000L
        anim.addUpdateListener {
            angle = it.animatedValue as Float
            postInvalidate()
            Log.d("执行动画中", "角度:$angle")
            call?.invoke()
        }
        anim.start()
    }


    private var call: (() -> UInt)? = null
    fun setAnimEndListener(call: () -> Unit) {
        this.call
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

}