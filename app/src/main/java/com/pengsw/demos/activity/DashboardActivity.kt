package com.pengsw.demos.activity

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.pengsw.demos.R

import com.pengsw.demos.view.DashboardView
import com.pengsw.demos.ext.CommonTimerTask

class DashboardActivity : AppCompatActivity() {

    private var num = 0

    private var mCommonTimer: CommonTimerTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)




        findViewById<View>(R.id.btn_accelerator).setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_POINTER_DOWN -> {
                    startTimer()
                }
                MotionEvent.ACTION_POINTER_UP -> {
                    stopTimer()
                }
            }
            true
        }
    }


    private fun startTimer() {
        if (mCommonTimer == null) {
            mCommonTimer = CommonTimerTask()
        }
        mCommonTimer?.start()
        mCommonTimer?.setOnTimeChangeListener(object :
            CommonTimerTask.OnTimeChangeListener {
            override fun onChange(time: Long) {
                num = (time * 7f).toInt()
                if (num > 180) {
                    num = 180
                }
                updateUi()
            }

            override fun onStop() {
                num = 0
                updateUi()
            }
        })
    }


    fun stopTimer() {
        mCommonTimer?.cancel()
    }

    fun updateUi(){
        findViewById<DashboardView>(R.id.mDashboardView).setProgress(num)
    }
}