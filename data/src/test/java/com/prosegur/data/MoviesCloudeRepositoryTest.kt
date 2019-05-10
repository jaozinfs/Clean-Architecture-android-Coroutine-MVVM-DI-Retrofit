package com.prosegur.data

import com.prosegur.data.films.ApiClientBuilder
import com.prosegur.data.films.SWApiImp
import com.prosegur.data.movies.repository.MovieCloudRepository
import com.prosegur.data.movies.schemas.MovieSchema
import com.prosegur.data.utils.enqueueResponse
import com.prosegur.data.utils.exceptions.DataHttpException
import com.prosegur.data.utils.loadJsonFromResources
import com.prosegur.data.utils.toDomain
import com.prosegur.domain.movies.entities.Movie
import io.mockk.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.fail
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test


class MoviesCloudeRepositoryTest{

    private lateinit var server: MockWebServer
    private lateinit var api: SWApiImp
    private lateinit var repo: MovieCloudRepository

    private val FILE_TAG : String = "movie"


    @Before
    fun setup() {
        server = MockWebServer()
        server.start()

        val url = server.url("/").toString()

        api = ApiClientBuilder.createServiceApi(SWApiImp::class.java, baseUrl = url)

        repo = MovieCloudRepository(api)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun `list response page ( @page ) successfully`() = runBlocking {
        //region arrange
        val page = 1
        val response = loadJsonFromResources("${FILE_TAG}_list_$page.json")
        server.enqueueResponse(200, response)
        //endregion


        //region act
        val listResponse: Response<MovieSchema> = repo.listResponse(page)
        //endregion

        //region assert
        assertEquals("A New Hope", listResponse.results.toDomain()[0].title)
        assertEquals(7, listResponse.count)
    }

    @Test
    fun `list response page ( @page ) throw exception`() = runBlocking{
        //region arrange
        val page = 9999
        val response = "404 error"
        server.enqueueResponse(404, response)


        //act
        val rs = try {
            val movies = repo.list( page )
            fail()
        }catch (error: DataHttpException){
            //assert
            assertEquals(404, error.statusCode)
        }finally {
            verify { api.listMovies(page, "") }
        }


    }

    @Test
    fun `get item by id response sucessfully `() = runBlocking{
        val itemId = 1


        val response = loadJsonFromResources("${FILE_TAG}_get_$itemId.json")

        server.enqueueResponse(200, response)

        try {
            //region act
            val movie: Movie? = repo.get(itemId)

            //region assert
            assertEquals("A New Hope", movie?.title)
            assertEquals(4, movie?.episode_id)

        } catch (e: DataHttpException) {
            //region assert
            assertEquals(404, e.statusCode)
            //endregion
        }
    }


    @Test(expected = DataHttpException::class)
    fun `get item by id null response DataException `() = runBlocking{
        val itemId = 99999

        val response =
            try {
                loadJsonFromResources("${FILE_TAG}_get_$itemId.json")
            } catch (e: IllegalStateException) {
                "{\"error\":\"Character not found\"}"
                server.enqueueResponse(404)
            }


        try {
            //region act
            val movie: Movie? = repo.get(itemId)
            fail()
            //endregion
        } catch (e: DataHttpException) {
            //region assert
            assertEquals(404, e.statusCode)
            //endregion
        }
    }


}