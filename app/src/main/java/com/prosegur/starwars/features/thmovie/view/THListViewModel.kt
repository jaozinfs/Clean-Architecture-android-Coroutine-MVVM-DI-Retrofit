package com.prosegur.starwars.features.thmovie.view


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder

import androidx.paging.PagedList
import com.prosegur.data.films.API_KEY
import com.prosegur.domain.th_movies.entities.THMovie
import com.prosegur.domain.th_movies.usecase.THMovieUseCase
import com.prosegur.starwars.features.BaseViewModel
import com.prosegur.starwars.features.thmovie.adapter.PostsDataSource
import com.prosegur.starwars.utils.asyncAwait


/**
 * Created by João Victor Oliveira on 07,Maio,2019
 */

class THListViewModel(

    private val useCase: THMovieUseCase

) : BaseViewModel<List<THMovie>?>() {


    var postsLiveData  : LiveData<PagedList<THMovie>>



    fun getPosts():LiveData<PagedList<THMovie>> = postsLiveData


    private var mutableDetailsDescription = MutableLiveData<String>()
     fun getDetailsDescription():LiveData<String> = mutableDetailsDescription


    private var mutableDetailsImage = MutableLiveData<String>()
     fun getDetailsImage():LiveData<String> = mutableDetailsImage


    private var mutableSetDetailsState2 = MutableLiveData<Boolean>()
    private var mutableSetDetailsState = MutableLiveData<Boolean>()
    fun getMutableSetDetailsState(): LiveData<Boolean> = mutableSetDetailsState

    init {
        mutableSetDetailsState.value = false
        mutableSetDetailsState2.value = false


        val config = PagedList.Config.Builder()
            .setPageSize( 10 )
            .setEnablePlaceholders(true)
            .setPrefetchDistance(2)
            .build()
        postsLiveData  = initializedPagedListBuilder(config).build()
    }


    fun changeDetailsLayout(movie: THMovie?){
        if (!mutableSetDetailsState2.value!!) {

            mutableDetailsDescription.value = movie?.overview
            mutableDetailsImage.value = movie?.backdrop_path
        }
        mutableSetDetailsState2.value = !mutableSetDetailsState2.value!!
        mutableSetDetailsState.value = !mutableSetDetailsState.value!!
    }
    private fun initializedPagedListBuilder(config: PagedList.Config): LivePagedListBuilder<Int, THMovie> {
        val dataSourceFactory = object : DataSource.Factory<Int, THMovie>() {
            override fun create(): DataSource<Int, THMovie> {
                return PostsDataSource(useCase)
            }
        }
        return LivePagedListBuilder<Int, THMovie >(dataSourceFactory, config)
    }

}