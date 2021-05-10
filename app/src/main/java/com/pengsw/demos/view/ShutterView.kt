package com.pengsw.demos.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.pengsw.demos.drawTextWithCenterPoint
import kotlin.math.min


/**
 * @date      2021/5/6
 * @author    Pengshuwen
 * @describe  六边形占比图
 */
class ShutterView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var margin = 20f
    private var mHeight: Int = 0
    private var mWidth: Int = 0


    val titles = arrayOf("伤害", "撞树", "冲进包围圈", "落地成盒", "吃鸡", "献祭")

    val values = intArrayOf(59, 335, 340, 315, 15, 356)
    val interim = intArrayOf(0, 0, 0, 0, 0, 0)

    val paint = Paint().apply {
        color = Color.parseColor("#207BF2")
        style = Paint.Style.STROKE
    }

    val paint2 = Paint().apply {
        color = Color.parseColor("#B0207BF2")
        style = Paint.Style.FILL
    }

    val textPaint = Paint().apply {
        color = Color.parseColor("#000000")
        textSize = 30f
        isAntiAlias = true
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            drawSpiderweb(canvas)
            drawLiens(canvas)
            drawPercentage(canvas)
        }

    }


    private fun drawSpiderweb(canvas: Canvas) {
        val centerX = mWidth / 2F
        val centerY = mHeight / 2F
        val radius = mWidth / 2f - margin
        val path = Path().apply {

            for (j in 1..6) {

                for (i in 0..6) {

                    val currentRadius = j * (radius / 6)

                    val x = Math.round(Math.sin(Math.toRadians(i * 60.0)) * currentRadius)
                    val y = Math.round(Math.cos(Math.toRadians(i * 60.0)) * currentRadius)

                    val finalX = centerX + x.toFloat()
                    val finalY = centerY + y.toFloat()

                    if (i == 0) {
                        moveTo(finalX, finalY)
                    } else {
                        lineTo(finalX, finalY)
                    }

                    if (j == 6 && i < titles.size) {
                        canvas.drawTextWithCenterPoint(
                            finalX.toInt(),
                            finalY.toInt(),
                            "${titles[i]}",
                            textPaint
                        )
                    }

                }
            }
        }

        canvas?.drawPath(path, paint)

    }

    private fun drawLiens(canvas: Canvas) {

        val centerX = mWidth / 2F
        val centerY = mHeight / 2F
        val radius = mWidth / 2f - margin


        for (i in 0..6) {


            val x = Math.round(Math.sin(Math.toRadians(i * 60.0)) * radius)
            val y = Math.round(Math.cos(Math.toRadians(i * 60.0)) * radius)

            val finalX = centerX + x.toFloat()
            val finalY = centerY + y.toFloat()

            canvas?.drawLine(centerX, centerY, finalX, finalY, paint)
        }
    }

    private fun drawPercentage(canvas: Canvas) {
        canvas?.let {

            val centerX = mWidth / 2F
            val centerY = mHeight / 2F
            val radius = mWidth / 2f - margin
            //进度就是就是控制radius
            val path = Path()
            interim?.forEachIndexed { index, i ->

                val x = Math.round(Math.sin(Math.toRadians(index * 60.0)) * i)
                val y = Math.round(Math.cos(Math.toRadians(index * 60.0)) * i)

                val finalX = centerX + x.toFloat()
                val finalY = centerY + y.toFloat()
                if (index == 0) {
                    path.moveTo(finalX, finalY)
                } else {
                    path.lineTo(finalX, finalY)
                }


                canvas.drawTextWithCenterPoint(
                    finalX.toInt(),
                    finalY.toInt(),
                    "$i",
                    textPaint
                )
            }
            path.close()
            canvas?.drawPath(path, paint2)

        }
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

    fun startAnim() {
        values?.forEachIndexed { index, i ->
            val anim = ValueAnimator.ofInt(0, i)
            anim.duration = 1000
            anim.addUpdateListener {
                interim[index] = it.animatedValue as Int
                invalidate()
            }
            anim.start()
        }
    }


}