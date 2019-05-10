package com.prosegur.starwars.features.di

import com.prosegur.data.films.ANOTHER_URL
import com.prosegur.data.films.ApiClientBuilder
import com.prosegur.data.films.BASE_URL
import com.prosegur.data.films.SWApiImp
import com.prosegur.data.utils.CacheConfig
import com.prosegur.starwars.features.networkconfig.cacheSize
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val RetrofitModule = module {
    single {
        ApiClientBuilder.createServiceApi(
            cache = CacheConfig(androidContext(), cacheSize),
            serviceClass = SWApiImp::class.java,
            baseUrl = ANOTHER_URL
        )
    }
}