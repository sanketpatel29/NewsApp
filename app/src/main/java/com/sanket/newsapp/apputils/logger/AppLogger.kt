package com.sanket.newsapp.apputils.logger

import android.util.Log

class AppLogger : Logger {

    private val APP_TAG = "NewsApp : "

    override fun v(classInstance: Class<out Any?>?, msg: String?) {

        if (classInstance != null) {
            Log.v(
                APP_TAG + classInstance.simpleName, msg!!
            )
        }
    }

    override fun i(classInstance: Class<out Any?>?, msg: String?) {
        if (classInstance != null) {
            Log.i(
                APP_TAG + classInstance.simpleName, msg!!
            )
        }
    }

    override fun w(classInstance: Class<out Any?>?, msg: String?) {
        if (classInstance != null) {
            Log.w(
                APP_TAG + classInstance.simpleName, msg!!
            )
        }
    }

    override fun d(classInstance: Class<out Any?>?, msg: String?) {
        if (classInstance != null) {
            Log.d(
                APP_TAG + classInstance.simpleName, msg!!
            )
        }
    }
}