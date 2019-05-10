package com.prosegur.data.films

import com.prosegur.data.Response
import com.prosegur.data.movies.schemas.MovieSchema
import com.prosegur.data.tm_movies.schemas.THMovieSchema
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Created by João Victor Oliveira on 07,Maio,2019
 */

interface SWApiImp{

    @GET("films/{movieId}")
    fun getMovie(@Path("movieId") movieId: Int) : Deferred<MovieSchema>

    @GET("films/")
    fun listMovies(@Query("page") page: Int, @Query("api_key") key: String?) : Deferred<Response<MovieSchema>>


    @GET("movie/popular")
    fun listTHMovies(@Query("page") page: Int, @Query("api_key") key: String?) : Deferred<Response<THMovieSchema>>

}