package com.pengsw.demos.ext

import android.app.Activity
import android.content.Intent
import com.pengsw.demos.activity.ShutterActivity


/**
 * @date      2021/5/6
 * @author    Pengshuwen
 * @describe
 */


fun Activity.startAct(clazz: Class<out Activity>) {
    startActivity(Intent(this, clazz))
}