package com.pengsw.demos.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.pengsw.demos.drawTextWithCenterPoint
import kotlin.math.cos
import kotlin.math.sin


/**
 * @date      2021/5/6
 * @author    Pengshuwen
 * @describe  刻度盘
 */
class DashboardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    private var mWidth: Int = 0

    private var currentIndex = 90


    val mPaint = Paint().apply {
        color = Color.parseColor("#207bf2")
        style = Paint.Style.STROKE
        isAntiAlias = true
        strokeWidth = 20f
    }

    val mPaint2 = Paint().apply {
        color = Color.parseColor("#207bf2")
        style = Paint.Style.STROKE
        isAntiAlias = true
        strokeWidth = 5f
    }


    val mTextPaint = Paint().apply {
        color = Color.parseColor("#000000")
        isAntiAlias = true
        textSize = 20f
    }

    val mPointerPaint = Paint().apply {
        color = Color.parseColor("#C81518")
        isAntiAlias = true
        style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        drawDashboard(canvas)
        drawPointer(canvas)
    }


    private fun drawDashboard(canvas: Canvas?) {
        val centerX = mWidth / 2

        val centerY = centerX

        val radius = mWidth / 2

        //画笔的一半防止超出，不想做处理，但是太难看
        val paint_w_2 = mPaint.strokeWidth / 2

        canvas?.drawArc(
            0f + paint_w_2,
            0f + paint_w_2,
            mWidth.toFloat() - paint_w_2,
            centerY * 2 - paint_w_2,
            180f,
            180f,
            false,
            mPaint
        )

        for (i in 0..180) {
            val blankRadius = if (i % 6 == 0) radius - 50 else radius - 30

            val angle = 270f - (i * (180 / 180.0))


            val finalX = getCircleX(angle, centerX, radius)
            val finalY = getCircleY(angle, centerY, radius)

            val finalX2 = getCircleX(angle, centerX, blankRadius)
            val finalY2 = getCircleY(angle, centerY, blankRadius)

            canvas?.drawLine(
                finalX.toFloat(), finalY.toFloat(), finalX2.toFloat(),
                finalY2.toFloat(), mPaint2
            )

            val finalX3 = getCircleX(angle, centerX, radius - 70)
            val finalY3 = getCircleY(angle, centerY, radius - 70)

            if (i % 6 == 0) {
                canvas?.drawTextWithCenterPoint(
                    finalX3.toInt(),
                    finalY3.toInt(),
                    "${(i / 6) * 6}",
                    mTextPaint
                )
            }
        }
        canvas?.drawCircle(centerX.toFloat(), centerY.toFloat(), 10f, mPointerPaint)
    }


    private fun drawPointer(canvas: Canvas?) {

        val centerX = mWidth / 2
        val centerY = centerX
        val radius = mWidth / 2

        val angle = 270 - (currentIndex * (180 / 180.0))

        val finalX3 = getCircleX(angle, centerX, radius - 100)
        val finalY3 = getCircleY(angle, centerY, radius - 100)
//        canvas?.drawLine(centerX.toFloat(), centerY.toFloat(), finalX3, finalY3, mPaint2)


        val finalX4 = getCircleX(angle + 90, centerX, 6)
        val finalY4 = getCircleY(angle + 90, centerY, 6)

        val finalX5 = getCircleX(angle + 180, centerX, 80)
        val finalY5 = getCircleY(angle + 180, centerY, 80)


        val finalX6 = getCircleX(angle - 90, centerX, 6)
        val finalY6 = getCircleY(angle - 90, centerY, 6)

        val path = Path().apply {
            moveTo(finalX3.toFloat(), finalY3.toFloat())
            lineTo(finalX4.toFloat(), finalY4.toFloat())
            lineTo(finalX5.toFloat(), finalY5.toFloat())
            lineTo(finalX6.toFloat(), finalY6.toFloat())
//            lineTo()
            close()
        }
        canvas?.drawPath(path, mPointerPaint)


    }


    fun getCircleX(angle: Double, centerX: Int, radius: Int): Double {
        return centerX + sin(Math.toRadians(angle)) * radius
    }

    fun getCircleY(angle: Double, centerY: Int, radius: Int): Double {
        return centerY + cos(Math.toRadians(angle)) * radius
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = measuredWidth

        //+80 指针尾部长度
        setMeasuredDimension(mWidth, (mWidth / 2 + 80f).toInt())
    }


    fun setProgress(num: Int) {
        this.currentIndex = num
        println("当前:$currentIndex")
        postInvalidate()
    }

}