package com.example.cryptomanager_v2.utils

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class NoInternetConnectionInterceptor @Inject constructor(private val context: Context): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return if (!Utils.isConnectionOn(context)) {
            throw NoConnectivityException()
        } else if(!Utils.isInternetAvailable()) {
            throw NoInternetException()
        } else {
            chain.proceed(chain.request())
        }
    }
}

class NoConnectivityException : IOException() {
    override val message: String
        get() =
            "No network available, please check your WiFi or Data connection"
}

class NoInternetException : IOException() {
    override val message: String
        get() =
            "No internet available, please check your connected WIFi or Data"
}