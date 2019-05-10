package com.prosegur.domain.movies.usecase

import com.prosegur.data.films.MoviesRepository
import com.prosegur.data.films.UseCase
import com.prosegur.domain.movies.entities.Movie


class ListMoviesUseCase(private val repo: MoviesRepository) :
    UseCase<List<Movie>?, ListMoviesUseCase.Params> {
    override suspend fun execute(params: Params): List<Movie>? = repo.list(params.page, null)

    data class Params(val page: Int = 1)
}