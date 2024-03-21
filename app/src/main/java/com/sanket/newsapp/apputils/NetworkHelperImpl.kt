package com.sanket.newsapp.apputils

import android.content.Context
import android.net.ConnectivityManager

class NetworkHelperImpl constructor(private val context: Context): NetworkHelper {
    override fun isNetworkAvailable(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork?.isConnected ?: false
    }
}