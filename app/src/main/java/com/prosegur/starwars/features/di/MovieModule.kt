package com.prosegur.starwars.features.di

import com.prosegur.data.films.MoviesRepository
import com.prosegur.data.movies.repository.MovieCloudRepository
import com.prosegur.domain.movies.usecase.GetMovieUseCase
import com.prosegur.domain.movies.usecase.ListMoviesUseCase
import com.prosegur.starwars.features.starwars.view.details.SWDetailsViewModel
import com.prosegur.starwars.features.starwars.view.list.SWListViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val MovieModule = module {
    factory { MovieCloudRepository(get()) as MoviesRepository }
    factory { ListMoviesUseCase(get()) }
    factory { GetMovieUseCase(get()) }
    viewModel { SWListViewModel(get()) }
    viewModel { SWDetailsViewModel(get()) }
}