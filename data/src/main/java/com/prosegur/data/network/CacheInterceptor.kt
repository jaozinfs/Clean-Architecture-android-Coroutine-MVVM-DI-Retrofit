package com.prosegur.data.network

import android.content.Context
import com.prosegur.data.utils.hasNetwork
import okhttp3.Interceptor
import okhttp3.Response

/**
 *
 * @author João victor
 * @since 07/05/2019
 *
 * inspired from [Caching with Retrofit — Store responses offline](https://medium.com/mindorks/caching-with-retrofit-store-responses-offline-71439ed32fda)
 */
class CacheInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        var cacheValue =
            if (context.hasNetwork) {
                // If there is Internet, get the cache that was stored 1 day ago.
                "public, max-age=${60 * 60 * 24 * 1}"
            } else {
                //If there is no Internet, get the cache that was stored 7 days ago.
                "public, only-if-cached, max-stale=${60 * 60 * 24 * 7}"
            }

        val newRequest = chain.request().newBuilder()
            .addHeader("Cache-Control", cacheValue)
            .build()
        return chain.proceed(newRequest)
    }
}