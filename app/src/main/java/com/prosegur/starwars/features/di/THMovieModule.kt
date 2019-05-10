package com.prosegur.starwars.features.di

import com.prosegur.data.tm_movies.repository.TMMovieCloudRepository
import com.prosegur.domain.th_movies.repository.THMovieRepository
import com.prosegur.domain.th_movies.usecase.THMovieUseCase
import com.prosegur.starwars.features.thmovie.view.THListViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val ThMovieModule = module {
    factory { TMMovieCloudRepository(get()) as THMovieRepository }
    factory { THMovieUseCase(get()) }
    viewModel { THListViewModel(get()) }
}