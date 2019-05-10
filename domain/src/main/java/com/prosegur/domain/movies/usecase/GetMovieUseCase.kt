package com.prosegur.domain.movies.usecase

import com.prosegur.data.films.MoviesRepository
import com.prosegur.data.films.UseCase
import com.prosegur.domain.movies.entities.Movie

class GetMovieUseCase( private val repo: MoviesRepository ) :
    UseCase<Movie?, GetMovieUseCase.Params> {

    override suspend fun execute(params: GetMovieUseCase.Params): Movie? = repo.get(params.movieId)

    data class Params(val movieId: Int = 1)
}