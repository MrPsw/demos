package com.pengsw.demos.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.pengsw.demos.ext.getCircleX
import com.pengsw.demos.ext.getCircleY
import kotlin.math.min


/**
 * @date      2021/5/8
 * @author    Pengshuwen
 * @describe  印章
 */
class StampView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    private var mWidth = 0
    private var mHeight = 0

    val mPaint = Paint().apply {
        color = Color.parseColor("#C81518")
        style = Paint.Style.STROKE
        strokeWidth = 12f
        isAntiAlias=true
    }

    val mTextPaint = Paint().apply {
        color = Color.parseColor("#C81518")
        style = Paint.Style.FILL
        textSize = 80f
        isAntiAlias=true
    }

    val mFivePointedStarPaint = Paint().apply {
        color = Color.parseColor("#C81518")
        style = Paint.Style.FILL
        isAntiAlias=true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val centerX = mWidth / 2f
        val centerY = mHeight / 2f
        val margin = mPaint.strokeWidth / 2

        canvas?.drawCircle(centerX, centerY, centerX - margin, mPaint)

        val path = Path().apply {
            addCircle(centerX, centerY, centerX - 150, Path.Direction.CW)
        }
        canvas?.save()
        canvas?.rotate(180f,centerX,centerY)
        canvas?.drawTextOnPath("举举违章专用章(上海海岛交警大队办事处)", path, 0f, 0f, mTextPaint)
        canvas?.restore()

        val xAngle = 360 / 10

        var x1 = 0.0
        var y1 = 0.0
        val path2 = Path().apply {
            for (i in 0..10) {
                val angle = xAngle * i

                if (i % 2 == 0) {
                    x1 = getCircleX(angle.toDouble(), centerX.toInt(), 80)
                    y1 = getCircleY(angle.toDouble(), centerY.toInt(), 80)
                }else{
                    x1 = getCircleX(angle.toDouble(), centerX.toInt(), 180)
                    y1 = getCircleY(angle.toDouble(), centerY.toInt(), 180)
                }

                if (i == 0) {
                    moveTo(x1.toFloat(), y1.toFloat())
                } else {
                    lineTo(x1.toFloat(),y1.toFloat())
                }
            }
        }
        canvas?.drawPath(path2,mFivePointedStarPaint)

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