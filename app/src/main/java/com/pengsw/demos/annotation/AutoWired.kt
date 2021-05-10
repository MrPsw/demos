package com.pengsw.demos.annotation


/**
 * @date      2021/4/22
 * @author    Pengshuwen
 * @describe
 */
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class AutoWired(val alias: String) {

}