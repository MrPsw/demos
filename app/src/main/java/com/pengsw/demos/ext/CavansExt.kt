package com.pengsw.demos

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import com.pengsw.demos.ext.getCircleX
import com.pengsw.demos.ext.getCircleY
import kotlin.math.ceil


/**
 * @date      2021/4/30
 * @author    Pengshuwen
 * @describe
 */

/**
 * 以中心点绘制文字
 *
 * @param canvas
 * @param centerX
 * @param centerY
 * @param text
 * @param paint
 */
fun Canvas.drawTextWithCenterPoint(
    centerX: Int,
    centerY: Int,
    text: String,
    paint: Paint
) {
    //获取文本的宽度，但是是一个比较粗略的结果
    val textWidth = getTextWidth(paint, text).toFloat()
    //文字度量
    val fontMetrics = paint.fontMetrics
    //得到基线的位置
    val baselineY = centerY + (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom
    //绘制
    drawText(text, centerX - textWidth / 2, baselineY, paint)
    //println("X坐标：${centerX - textWidth / 2}Y坐标：${baselineY}")
}


/**
 * 以中心点绘制文字
 *
 * @param canvas
 * @param centerX
 * @param centerY
 * @param text
 * @param paint
 */
fun Canvas.drawTextWithCenterPoint(
    centerX: Int,
    centerY: Int,
    text: String,
    angle: Int,
    paint: Paint
) {
    //获取文本的宽度，但是是一个比较粗略的结果
    val textWidth = getTextWidth(paint, text).toFloat()
    //文字度量
    val fontMetrics = paint.fontMetrics
    //得到基线的位置
    val baselineY = centerY + (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom
    //绘制
    drawText(text, centerX - textWidth / 2, baselineY, paint)
    //println("X坐标：${centerX - textWidth / 2}Y坐标：${baselineY}")
}


private fun getTextWidth(paint: Paint, str: String?): Int {
    var iRet = 0
    if (str != null && str.isNotEmpty()) {
        val len = str.length
        val widths = FloatArray(len)
        paint.getTextWidths(str, widths)
        for (j in 0 until len) {
            iRet += ceil(widths[j].toDouble()).toInt()
        }
    }
    return iRet
}



 fun Canvas.drawStar(centerX:Int,centerY:Int,radius:Int,paint: Paint){

    val xAngle = 360 / 10

    var x1 = 0.0
    var y1 = 0.0
    val path2 = Path().apply {
        for (i in 0..10) {
            val angle = xAngle * i

            if (i % 2 == 0) {
                x1 = getCircleX(angle.toDouble(), centerX, radius/2)
                y1 = getCircleY(angle.toDouble(), centerY, radius/2)
            }else{
                x1 = getCircleX(angle.toDouble(), centerX, radius)
                y1 = getCircleY(angle.toDouble(), centerY, radius)
            }

            if (i == 0) {
                moveTo(x1.toFloat(), y1.toFloat())
            } else {
                lineTo(x1.toFloat(),y1.toFloat())
            }
        }
    }
    drawPath(path2,paint)
}