package com.pengsw.demos.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.pengsw.demos.R
import com.pengsw.demos.view.ShutterView

class ShutterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shutter)

        findViewById<View>(R.id.btn_start).setOnClickListener {
            findViewById<ShutterView>(R.id.chart).startAnim()
        }
    }
}