package com.rizalferdian.moviecatalogue.service

import com.rizalferdian.moviecatalogue.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AppInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().run {
            val url = url().newBuilder()
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .addQueryParameter("language", "en-US")
                .build()
            newBuilder().url(url).build()
        }
        return chain.proceed(request)
    }
}