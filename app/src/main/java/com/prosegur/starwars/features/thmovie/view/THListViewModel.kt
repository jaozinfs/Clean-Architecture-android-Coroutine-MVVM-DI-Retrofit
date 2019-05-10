package com.prosegur.starwars.features.thmovie.view

import com.prosegur.data.films.API_KEY
import com.prosegur.domain.movies.entities.Movie
import com.prosegur.domain.movies.usecase.ListMoviesUseCase
import com.prosegur.domain.th_movies.entities.THMovie
import com.prosegur.domain.th_movies.usecase.THMovieUseCase
import com.prosegur.starwars.features.BaseViewModel
import com.prosegur.starwars.utils.asyncAwait

/**
 * Created by João Victor Oliveira on 07,Maio,2019
 */

class THListViewModel(

    private val useCase: THMovieUseCase

) : BaseViewModel<List<THMovie>?>() {

    suspend fun loadCharacters() {
        doWorkWithProgress {
            asyncAwait {
                useCase.execute(THMovieUseCase.Params(apikey = API_KEY))
            }
        }
    }

}