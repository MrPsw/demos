package com.pengsw.demos.activity

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.pengsw.demos.R

class ClockActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clock)

        val selectColor = Color.parseColor("#ffffff")
        val unselectColor = Color.parseColor("#9668B6")

        findViewById<SwitchCompat>(R.id.sw_1).setOnCheckedChangeListener { buttonView, isChecked ->

            findViewById<TextView>(R.id.text1).apply {
                setTextColor(if (isChecked) selectColor else unselectColor)
            }
        }

        findViewById<SwitchCompat>(R.id.sw_2).setOnCheckedChangeListener { buttonView, isChecked ->

            findViewById<TextView>(R.id.text2).apply {
                setTextColor(if (isChecked) selectColor else unselectColor)
            }

        }
        findViewById<SwitchCompat>(R.id.sw_3).setOnCheckedChangeListener { buttonView, isChecked ->

            findViewById<TextView>(R.id.text3).apply {
                setTextColor(if (isChecked) selectColor else unselectColor)
            }

        }


    }
}