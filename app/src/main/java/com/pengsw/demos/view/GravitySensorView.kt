package com.pengsw.demos.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.pengsw.demos.R
import com.pengsw.demos.drawTextWithCenterPoint


/**
 * @date      2021/4/30
 * @author    Pengshuwen
 * @describe  重力感应
 */
class GravitySensorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    View(context, attrs, defStyleAttr)  {

        private var cx=142f
        private var cy=152f

        private var mWidth=400
        private var mHeight=400


        init {
            initGravitySensor()
            setBackgroundColor(ContextCompat.getColor(context, R.color.black))
        }



    private val roundPaint= Paint().apply {
            setColor(Color.parseColor("#ff0000"))
        style=Paint.Style.FILL
        }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            drawRound(canvas)
            it.drawTextWithCenterPoint(100,100,"感应器移动红球",Paint().apply {
                color=Color.parseColor("#ffffff")
                textSize=30f
            })
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mHeight=measuredHeight
        mWidth=measuredWidth
    }


        fun drawRound(cavans: Canvas){
            cavans.drawCircle(cx, cy, 50f, roundPaint)
        }


    private fun initGravitySensor() {
        val sm = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sm.registerListener(object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent) {
                if (Sensor.TYPE_ACCELEROMETER !== event.sensor.type) {
                    return
                }
                val values = event.values
                val ax = values[0]
                val ay = values[1]
                val az = values[2]

                cx = cx - (ax * 2)
                cy = cy +(ay * 2)

                if (cx < 0) {
                    cx = 0f
                }
                if (cx > mWidth) {
                    cx = mWidth.toFloat()
                }
                if (cy < 0) {
                    cy = 0f
                }
                if (cy > mHeight) {
                    cy = mHeight.toFloat()
                }


                Log.e("onSensorChanged: ", "cx:$cx cy:$cy")

                invalidate()
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME)
    }

}