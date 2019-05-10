package com.prosegur.starwars.features.starwars.view.list

import com.prosegur.domain.movies.entities.Movie
import com.prosegur.domain.movies.usecase.ListMoviesUseCase
import com.prosegur.starwars.features.BaseViewModel
import com.prosegur.starwars.utils.asyncAwait

/**
 * Created by João Victor Oliveira on 07,Maio,2019
 */

class SWListViewModel(

    private val useCase: ListMoviesUseCase

) : BaseViewModel<List<Movie>?>() {

    suspend fun loadCharacters() {
        doWorkWithProgress {
            asyncAwait {
                useCase.execute(ListMoviesUseCase.Params())
            }
        }
    }

}