package com.prosegur.starwars

import android.app.Application
import com.prosegur.s.BuildConfig
import com.prosegur.starwars.features.di.MovieModule
import com.prosegur.starwars.features.di.RetrofitModule
import com.prosegur.starwars.features.di.ThMovieModule
import org.koin.android.ext.android.startKoin
import timber.log.Timber
import timber.log.Timber.plant

/**
 * Created by João Victor Oliveira on 07,Maio,2019
 */
class SWApplication: Application() {


    override fun onCreate() {
        super.onCreate()

        //start timber for logs
        startTimberProcess()

        // start Koin di
        setupKoin()


    }

    fun startTimberProcess() {
        if (BuildConfig.DEBUG) plant(Timber.DebugTree())
        else { //TODO Crash reporting three logging for production app
        }
    }

    fun setupKoin(){
        startKoin(
            this, listOf(
                RetrofitModule,
                MovieModule,
                ThMovieModule
            )
        )


    }
}

