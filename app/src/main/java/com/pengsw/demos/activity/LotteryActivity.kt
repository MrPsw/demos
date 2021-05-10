package com.pengsw.demos.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.pengsw.demos.R
import com.pengsw.demos.view.RotaryTableView
import java.util.*

/**
 *
 *
 */
class LotteryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lottery)


        findViewById<View>(R.id.btn_start).setOnClickListener {
            findViewById<RotaryTableView>(R.id.rotaryTableView).apply {
                val angle = Random().nextInt(360).toFloat()
                setAngle(angle)
                start()
            }
        }
    }
}