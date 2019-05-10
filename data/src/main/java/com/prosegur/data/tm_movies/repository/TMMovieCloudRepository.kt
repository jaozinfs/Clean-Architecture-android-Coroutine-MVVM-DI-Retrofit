package com.prosegur.data.tm_movies.repository

import com.prosegur.data.BaseCloudRepository
import com.prosegur.data.Response
import com.prosegur.data.films.MoviesRepository
import com.prosegur.data.films.SWApiImp
import com.prosegur.data.tm_movies.schemas.THMovieSchema
import com.prosegur.domain.th_movies.entities.THMovie
import com.prosegur.domain.th_movies.repository.THMovieRepository
import kotlinx.coroutines.Deferred
import java.lang.Exception

class TMMovieCloudRepository(api: SWApiImp) :
    BaseCloudRepository<THMovie, THMovieSchema>(api), THMovieRepository {

    override val getMethod: (Int) -> Deferred<THMovieSchema>
        get() = throw Exception("not implemented")

    override val listMethod: (Int, String?) -> Deferred<Response<THMovieSchema>>
        get() = api::listTHMovies
}