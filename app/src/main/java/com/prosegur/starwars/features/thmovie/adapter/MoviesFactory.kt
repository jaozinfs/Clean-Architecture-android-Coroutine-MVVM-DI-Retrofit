package com.prosegur.starwars.features.thmovie.adapter

import androidx.paging.PageKeyedDataSource
import com.prosegur.domain.th_movies.entities.THMovie


class PostsDataSource(
    private val loadPageData: (page: Int) -> List<THMovie>?
) : PageKeyedDataSource<Int, THMovie>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, THMovie?>) {
        val movies = loadPageData(1)
        callback.onResult(movies!!, null, 1)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, THMovie>) {
        val movies = loadPageData(params.key + 1)
        callback.onResult(movies!!, params.key + 1)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, THMovie>) {}
}