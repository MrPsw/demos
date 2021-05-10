package com.pengsw.demos.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.pengsw.demos.drawStar
import com.pengsw.demos.drawTextWithCenterPoint
import com.pengsw.demos.ext.CommonTimerTask
import java.text.SimpleDateFormat
import java.util.*


/**
 * @date      2021/5/8
 * @author    Pengshuwen
 * @describe  不知道写得什么，看到别人用fluteer写了，试试而已
 */
class Guizhidao1View @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var mCommonTimer: CommonTimerTask? = null
    private var mHeight = 0
    private var mWidth = 0

    private var timeText = "12:32"
    private var angle = 0f

    val paint = Paint().apply {

    }

    val paint2 = Paint().apply {
        isAntiAlias = true
        color = Color.parseColor("#ffffff")
        style = Paint.Style.FILL
    }

    val mTextPaint = Paint().apply {
        color = Color.parseColor("#ffffff")
        typeface = Typeface.DEFAULT_BOLD
        textSize = 120f
    }

    val mSunPaint = Paint().apply {
        isAntiAlias = true
        color = Color.parseColor("#FECE00")
    }

    val mMoonPaint = Paint().apply {
        isAntiAlias = true
        color = Color.parseColor("#ffffff")
    }

    val mStarPaint = Paint().apply {
        isAntiAlias = true
        color = Color.parseColor("#FECE00")
    }

    init {
        startTimer()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)


        val centerX = (mWidth / 2).toFloat()
        val centerY = (mHeight / 4).toFloat()

        canvas?.drawRect(0f, 0f, mWidth.toFloat(), mHeight.toFloat(), paint)



        canvas?.save()
        for (i in 0..12) {
            canvas?.rotate(30f, centerX, centerY)
            canvas?.drawCircle(centerX, 200f, 12f, paint2)
        }
        canvas?.restore()

        canvas?.drawTextWithCenterPoint(centerX.toInt(), centerY.toInt(), timeText, mTextPaint)


        val hour = getH(System.currentTimeMillis())

//        if (hour in 10..18){
//            //想画太阳，可惜下班了 骑着我心爱的小摩托，它从来不会堵车
//            canvas?.drawCircle(200f,200f,100f,mTextPaint)
//        }else{
//            //想画月亮，可惜下班了  骑着我心爱的小摩托，它从来不会堵车
//        }

        drawSun(canvas)
        drawMoon(canvas)

    }

    private fun drawSun(canvas: Canvas?) {
        canvas?.drawCircle(200f, 200f, 50f, mSunPaint)
    }

    private fun drawMoon(canvas: Canvas?) {
        val sc = canvas!!.saveLayer(
            0F,
            0F,
            mWidth.toFloat(),
            mHeight.toFloat(),
            null,
            Canvas.ALL_SAVE_FLAG
        )
        canvas?.drawCircle(mWidth - 200f, 200f, 50f, mMoonPaint)
        mMoonPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
        canvas?.drawCircle(mWidth - (200f + 25), 200f, 50f, mMoonPaint)
        mMoonPaint.xfermode = null
        canvas?.restoreToCount(sc)


        canvas?.drawStar(120, 190, 15, mStarPaint)

        canvas?.drawStar(220, 390, 20, mStarPaint)

        canvas?.drawStar(320, 190, 10, mStarPaint)

        canvas?.drawStar(420, 420, 18, mStarPaint)

        canvas?.drawStar(520, 320, 14, mStarPaint)

        canvas?.drawStar(620, 520, 12, mStarPaint)

        canvas?.drawStar(720, 360, 16, mStarPaint)


    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = measuredWidth
        mHeight = measuredHeight
//        if (mWidth > 0 && mHeight > 0) {
//            mWidth = min(mHeight, mWidth)
//            mHeight = min(mHeight, mWidth)
//            setMeasuredDimension(mWidth, mHeight)
//        }

        updatePaint()
    }

    private fun updatePaint() {
        val startColor = Color.parseColor("#460190")
        val endColor = Color.parseColor("#7852FF")
        paint.shader =
            SweepGradient((mWidth / 2).toFloat(), (mHeight / 4).toFloat(), startColor, endColor)
        paint.shader.setLocalMatrix(Matrix().apply {
            setRotate(270 + angle, (mWidth / 2).toFloat(), (mHeight / 4).toFloat())
        })
    }


    private fun startTimer() {


        if (mCommonTimer == null) {
            mCommonTimer = CommonTimerTask()
        }
        mCommonTimer?.start()
        mCommonTimer?.setInterval(100)
        mCommonTimer?.setOnTimeChangeListener(object :
            CommonTimerTask.OnTimeChangeListener {
            override fun onChange(time: Long) {
                val time = System.currentTimeMillis()
                timeText = formatToHM(time) ?: ""

                angle = ((time / 1000) % 60) * 6f
                println("角度：$time $angle")
                updatePaint()
                postInvalidate()
            }

            override fun onStop() {

            }
        })
    }


    fun stopTimer() {
        mCommonTimer?.cancel()
    }

    fun formatToHM(timeStamp: Long): String? {
        if (timeStamp == 0L) {
            return ""
        }
        val sdf = SimpleDateFormat("HH:mm")
        return sdf.format(Date(timeStamp.toString().toLong()))
    }


    fun getH(timeStamp: Long): Int {
        if (timeStamp == 0L) {
            return 0
        }
        val sdf = SimpleDateFormat("HH")
        return sdf.format(Date(timeStamp.toString().toLong())).toInt()
    }


}