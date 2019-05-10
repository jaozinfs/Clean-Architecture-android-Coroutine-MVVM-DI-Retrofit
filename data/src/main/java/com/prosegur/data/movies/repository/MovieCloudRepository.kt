package com.prosegur.data.movies.repository

import com.prosegur.data.BaseCloudRepository
import com.prosegur.data.Response
import com.prosegur.data.films.MoviesRepository
import com.prosegur.data.films.SWApiImp
import com.prosegur.data.movies.schemas.MovieSchema
import com.prosegur.domain.movies.entities.Movie
import kotlinx.coroutines.Deferred

class MovieCloudRepository constructor(api: SWApiImp) :
    BaseCloudRepository<Movie, MovieSchema>(api), MoviesRepository {


    override val getMethod: (Int) -> Deferred<MovieSchema>
        get() = api::getMovie


   override val listMethod: (Int, String?) -> Deferred<Response<MovieSchema>>
        get() = api::listMovies

}