package com.prosegur.domain.th_movies.usecase

import com.prosegur.data.films.MoviesRepository
import com.prosegur.data.films.UseCase
import com.prosegur.domain.th_movies.entities.THMovie
import com.prosegur.domain.th_movies.repository.THMovieRepository

class THMovieUseCase( private val repo: THMovieRepository) : UseCase<List<THMovie>?, THMovieUseCase.Params>{
    override suspend fun execute(params: Params): List<THMovie>? {
        return repo.list(
            params.page,
            params.apikey
        )
    }


    data class Params(val page: Int, val apikey: String?)
}