package com.prosegur.starwars.features.thmovie.adapter

import androidx.paging.PageKeyedDataSource
import com.prosegur.data.films.API_KEY
import com.prosegur.domain.th_movies.entities.THMovie
import com.prosegur.domain.th_movies.usecase.THMovieUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class PostsDataSource(
    private val useCase: THMovieUseCase
) : PageKeyedDataSource<Int, THMovie>() {


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, THMovie?>) {
        GlobalScope.launch {
            val movies = useCase.execute(THMovieUseCase.Params(1, API_KEY))
            callback.onResult(movies!!, null, 1)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, THMovie>) {
        GlobalScope.launch {
            val movies = useCase.execute(THMovieUseCase.Params(params.key + 1, API_KEY))
            callback.onResult(movies!!, params.key + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, THMovie>) { }
}