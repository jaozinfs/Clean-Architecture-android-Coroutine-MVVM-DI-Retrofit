package com.prosegur.starwars.movie.ListMoviesUseCase

import com.prosegur.data.films.MoviesRepository
import com.prosegur.domain.movies.usecase.ListMoviesUseCase
import com.prosegur.starwars.getItemsPage
import com.prosegur.starwars.movie.mockresponses.moviesMocke
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ListMoviesUseCaseTest{


    @MockK
    private lateinit var repository: MoviesRepository

    private lateinit var useCase: ListMoviesUseCase

    private val data = moviesMocke

    @Before
    fun setup() {

        MockKAnnotations.init(this)

        val slot = slot<Int>()

        every {
            runBlocking {
                repository.list(capture(slot))
            }
        } answers {
            data.getItemsPage(slot.captured)
        }
        useCase = ListMoviesUseCase(repository)
    }



    @Test
    fun `list first page of characters`() = runBlocking {
        val movies = useCase.execute(ListMoviesUseCase.Params())
        Assert.assertEquals(movies, data.getItemsPage(1))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `list page very large size`() = runBlocking {
        val movies = useCase.execute(ListMoviesUseCase.Params(99))
        Assert.assertEquals(movies, data.getItemsPage(99))
    }



}