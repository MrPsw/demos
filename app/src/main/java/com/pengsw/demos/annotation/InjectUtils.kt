package com.pengsw.demos.annotation

import android.app.Activity
import android.os.Parcelable
import android.text.TextUtils
import java.util.*


/**
 * @date      2021/4/22
 * @author    Pengshuwen
 * @describe
 */
class InjectUtils {

    fun inject(activity: Activity) {
        //获取class
        val aClass = activity.javaClass as Class<out Activity>
        //获取类的字段
        val fields = aClass.declaredFields
        //获取Bundle
        val extras = activity.intent.extras ?: return

        fields?.forEach { field ->
            //是否是指定注解
            if (field.isAnnotationPresent(AutoWired::class.java)) {
                val autoWired = field.getAnnotation(AutoWired::class.java)
                val key = if (TextUtils.isEmpty(autoWired.alias)) field.name else autoWired.alias
                if (extras.containsKey(key)) {
                    var value = extras.get(key)
                    val fieldType = field.type.componentType

                    // class1.isAssignableFrom(class2) 判定此 class1 对象所表示的类或接口与指定的 class2 参数所表示的类或接口是否相同，或是否是其超类或超接口
                    if (field.type.isArray && Parcelable::class.java.isAssignableFrom(fieldType)) {
                        val objects = value as Array<Any>
                        //创建对应类型的数组并由objects拷贝,即拷贝成对应Parcelable类型的数组
                        val realArr = Arrays.copyOf(
                            objects,
                            objects.size,
                            field.type as Class<out Array<Any>?>
                        )
                        //重新对value赋值
                        value = realArr
                    }
                    //设置访问权限
                    field.isAccessible = true
                    //反射设置值
                    field[activity] = value
                }
            }
        }

    }


}