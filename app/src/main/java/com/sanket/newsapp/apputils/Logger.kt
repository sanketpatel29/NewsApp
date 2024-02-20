package com.sanket.newsapp.apputils

import android.text.TextUtils
import android.util.Log

class Logger {

    val APP_TAG = "NewsApp : "

    fun v(classInstance: Class<out Any?>?, msg: String?) {

        if (classInstance != null) {
            Log.v(
                APP_TAG + classInstance.simpleName, (if (TextUtils.isEmpty(msg)) "null" else msg)!!
            )
        }
    }

    fun i(classInstance: Class<out Any?>?, msg: String?) {
        if (classInstance != null) {
            Log.i(
                APP_TAG + classInstance.simpleName, (if (TextUtils.isEmpty(msg)) "null" else msg)!!
            )
        }
    }

    fun w(classInstance: Class<out Any?>?, msg: String?) {
        if (classInstance != null) {
            Log.w(
                APP_TAG + classInstance.simpleName, (if (TextUtils.isEmpty(msg)) "null" else msg)!!
            )
        }
    }

    fun d(classInstance: Class<out Any?>?, msg: String?) {
        if (classInstance != null) {
            Log.d(
                APP_TAG + classInstance.simpleName, (if (TextUtils.isEmpty(msg)) "null" else msg)!!
            )
        }
    }
}