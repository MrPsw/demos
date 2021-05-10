package com.pengsw.demos


/**
 * 筛选和for 同时应用
 *
 * @param T         泛型类
 * @param condition  条件函数
 * @param forfun   循环函数
 * @return
 */
fun <T> Iterable<T>.filterAndFor(condition: (T) -> Boolean, forFun: (T) -> Unit): ArrayList<T> {
    val newList = ArrayList<T>()
    for (it in this) {
        if (condition.invoke(it)) {
            newList.add(it)
            forFun.invoke(it)
        }
    }
//    forEach {
//        if (condition.invoke(it)) {
//            newList.add(it)
//            forFun.invoke(it)
//        }
//    }
    return newList
}



