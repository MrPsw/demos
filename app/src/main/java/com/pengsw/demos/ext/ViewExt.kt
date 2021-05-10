package com.pengsw.demos.ext

import android.view.View
import kotlin.math.cos
import kotlin.math.sin


/**
 * @date      2021/5/7
 * @author    Pengshuwen
 * @describe
 */



//fun View.measureAndMargins(parentWidthMeasureSpec: Int, parentHeightMeasureSpec: Int) {
//
//    // 获取子视图的布局参数
//    val lp: LayoutParams = child.layoutParams
//
//    // 调用getChildMeasureSpec()，根据父视图的MeasureSpec & 布局参数LayoutParams，计算单个子View的MeasureSpec
//    // getChildMeasureSpec()请回看上面的解析
//    // 获取 ChildView 的 widthMeasureSpec
//    val childWidthMeasureSpec: Int = getChildMeasureSpec(
//        parentWidthMeasureSpec,
//        mPaddingLeft + mPaddingRight, lp.width
//    )
//    // 获取 ChildView 的 heightMeasureSpec
//    val childHeightMeasureSpec: Int = getChildMeasureSpec(
//        parentHeightMeasureSpec,
//        mPaddingTop + mPaddingBottom, lp.height
//    )
//
//    // 将计算好的子View的MeasureSpec值传入measure()，进行最后的测量
//    child.measure(childWidthMeasureSpec, childHeightMeasureSpec)
//}




fun getCircleX(angle: Double, centerX: Int, radius: Int): Double {
    return centerX + sin(Math.toRadians(angle)) * radius
}

fun getCircleY(angle: Double, centerY: Int, radius: Int): Double {
    return centerY + cos(Math.toRadians(angle)) * radius
}