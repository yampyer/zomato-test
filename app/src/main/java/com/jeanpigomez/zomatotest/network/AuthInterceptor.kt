package com.jeanpigomez.zomatotest.network

import com.jeanpigomez.zomatotest.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {

    /**
     * Interceptor class for setting of the headers for every request
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request?.newBuilder()
                ?.addHeader("user-key", BuildConfig.ZomatoAPIKEY)
                ?.build()
        return chain.proceed(request)
    }
}
