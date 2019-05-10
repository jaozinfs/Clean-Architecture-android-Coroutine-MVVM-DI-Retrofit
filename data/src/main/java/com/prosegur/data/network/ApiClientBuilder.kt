package com.prosegur.data.films
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.prosegur.data.network.CacheInterceptor
import com.prosegur.data.utils.CacheConfig
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClientBuilder {

    companion object {
        private val defaultClient: OkHttpClient = OkHttpClient()

        /**
         * @param cacheSize should be informed in Bytes. if null, no cache will be saved
         * */
        fun <S> createServiceApi(
            serviceClass: Class<S>,
            cache: CacheConfig? = null,
            baseUrl: String,
            gson: Gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create(),
            vararg interceptors: Interceptor
        ): S {

            //creat client
            val clientBuilder = defaultClient.newBuilder()


            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            clientBuilder.addInterceptor(httpLoggingInterceptor)

            if(cache != null){
                clientBuilder.cache(Cache(cache.context.cacheDir, cache.cacheSize!!))
                clientBuilder.addInterceptor(CacheInterceptor(cache.context))
            }

            //add interceptors
            interceptors.forEach { clientBuilder.addInterceptor(it) }

            //build client
            val client = clientBuilder.build()


            //build retrofit and set client, Coroutine factory
            val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()

            return retrofit.create(serviceClass)
        }

    }

}
