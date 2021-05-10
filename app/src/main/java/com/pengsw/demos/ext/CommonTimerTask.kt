package com.pengsw.demos.ext

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Message
import java.util.*


/**
 * @date      2019/12/16
 * @author    Pengshuwen
 * @describe
 */
class CommonTimerTask {


    private var timer: Timer? = null
    private var timerTask: TimerTask? = null
    private var listener: OnTimeChangeListener? = null
    private var mInterval = 1000L

    private val TIME_START = 1001

    private val TIME_STOP = 1002

    private var currentTime = 0L

    private var isRun = false;


    fun start() {

        if (isRun) {
            cancel()
        }

        isRun = true
        timerTask = object : TimerTask() {
            override fun run() {
                val msg = Message()
                msg.what = TIME_START
                mHandler.sendMessage(msg)
            }
        }
        timer = Timer()
        timer?.schedule(timerTask, 0, mInterval)
    }


    fun setInterval(interval: Long) {
        this.mInterval = interval
    }

    fun cancel() {
        timer?.let {
            it.cancel()
            timer = null
        }

        timerTask?.let {
            it.cancel()
            timerTask = null
        }
        currentTime = 0

        listener?.onStop()

        isRun = false

    }


    val mHandler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                TIME_START -> {
                    if (!isRun) return
                    currentTime += 1L
                    listener?.onChange(currentTime)
                }
                TIME_STOP -> {
                    cancel()
                }
            }
        }
    }


    fun setOnTimeChangeListener(listener: OnTimeChangeListener) {
        this.listener = listener
    }

    interface OnTimeChangeListener {
        fun onChange(time: Long)
        fun onStop()
    }

}