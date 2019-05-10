package com.prosegur.starwars.movie.GetMovieUseCase

import com.prosegur.data.films.MoviesRepository
import com.prosegur.domain.movies.usecase.GetMovieUseCase
import com.prosegur.starwars.movie.mockresponses.MovieRequest
import com.prosegur.starwars.movie.mockresponses.movieMocked

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.IOException

class GetMoviesUseCaseTest {


    @MockK
    private lateinit var repository: MoviesRepository

    private lateinit var useCase: GetMovieUseCase

    private val data = movieMocked

    private lateinit var simulateMovieRequest: MovieRequest

    @Before
    fun setup() {

        MockKAnnotations.init(this)


        simulateMovieRequest = MovieRequest()

        val slot = slot<Int>()

        every {
            runBlocking {
                repository.get(capture(slot))
            }
        } answers {
            simulateMovieRequest.requestByID(slot.captured)
        }
        useCase = GetMovieUseCase(repository)
    }


    @Test
    fun `get movie by id sucelly`() = runBlocking {
        val movie = useCase.execute(GetMovieUseCase.Params(4))
        Assert.assertEquals(movie?.episode_id, data.episode_id)
    }

    @Test(expected = IOException::class)
    fun `get movie by id not found exception`() = runBlocking {
        every { runBlocking { repository.get(any()) } } answers { throw IOException() }
        val movies = useCase.execute(GetMovieUseCase.Params(99))
    }


}