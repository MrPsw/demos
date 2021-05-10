package com.pengsw.demos

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.pengsw.demos.activity.*
import com.pengsw.demos.ext.startAct

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initActivity()
    }

    private fun initActivity() {
        findViewById<View>(R.id.btn_bagua).setOnClickListener {
            startAct(BaGuaActivity::class.java)
        }
        findViewById<View>(R.id.btn_gravity_sensor).setOnClickListener {
            startAct(GravitySensorActivity::class.java)
        }
        findViewById<View>(R.id.btn_load).setOnClickListener {
            startAct(LoadViewActivity::class.java)
        }
        findViewById<View>(R.id.btn_distribution).setOnClickListener {
            startAct(ShutterActivity::class.java)
        }
        findViewById<View>(R.id.btn_dashboard).setOnClickListener {
            startAct(DashboardActivity::class.java)
        }

        findViewById<View>(R.id.btn_choujiang).setOnClickListener {
            startAct(LotteryActivity::class.java)
        }

        findViewById<View>(R.id.btn_stamp).setOnClickListener {
            startAct(StampActivity::class.java)
        }
        findViewById<View>(R.id.btn_clock).setOnClickListener {
            startAct(ClockActivity::class.java)
        }







    }
}