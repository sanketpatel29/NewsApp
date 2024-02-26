package com.sanket.newsapp.apputils.logger

interface Logger {

    fun v(classInstance: Class<out Any?>?, msg: String?)
    fun i(classInstance: Class<out Any?>?, msg: String?)
    fun d(classInstance: Class<out Any?>?, msg: String?)
    fun w(classInstance: Class<out Any?>?, msg: String?)

}