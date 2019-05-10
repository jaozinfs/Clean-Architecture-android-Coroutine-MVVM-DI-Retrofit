package com.prosegur.starwars.features.starwars.view.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.prosegur.domain.movies.entities.Movie
import com.prosegur.domain.movies.usecase.GetMovieUseCase
import com.prosegur.starwars.features.BaseViewModel
import com.prosegur.starwars.utils.asyncAwait

/**
 * Created by João Victor Oliveira on 07,Maio,2019
 */

class SWDetailsViewModel(

    private val useCase: GetMovieUseCase

) : BaseViewModel<Movie?>() {


    suspend fun loadMovie(movieId: Int) {
        doWorkWithProgress(true) {
            asyncAwait {
                useCase.execute(GetMovieUseCase.Params(movieId+1))
            }
        }
    }

}