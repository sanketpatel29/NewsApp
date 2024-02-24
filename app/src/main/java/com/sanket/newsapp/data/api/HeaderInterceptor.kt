package com.sanket.newsapp.data.api

import com.sanket.newsapp.apputils.Constants
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        return chain.run {
            proceed(
                request().newBuilder()
                    .addHeader(Constants.ApiHeaders.Key.API_KEY, Constants.ApiHeaders.Value.API_KEY)
                    .build()
            )
        }
    }
}