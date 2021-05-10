package com.pengsw.demos.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.pengsw.demos.view.FiveElementsBaguaView
import com.pengsw.demos.R

/**
 * @date      2021/5/6
 * @author    Pengshuwen
 * @describe  八卦图
 */
class BaGuaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ba_gua)


        val btnStart = findViewById<View>(R.id.btn_start)
        val baGuaView = findViewById<FiveElementsBaguaView>(R.id.baguaView)

        btnStart.setOnClickListener {
            baGuaView.start()
            baGuaView.setResultListener {

            }
        }

    }
}