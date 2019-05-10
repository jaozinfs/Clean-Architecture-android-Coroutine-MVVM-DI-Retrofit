package com.prosegur.starwars.movie.mockresponses

import com.prosegur.domain.movies.entities.Movie
import com.prosegur.starwars.SimulateRequest
import java.io.IOException
import java.time.Instant
import java.util.*


class MovieRequest : SimulateRequest<Movie> {
    override fun requestByID(movieId: Int): Movie {
        if(movieId == movieMocked.episode_id){
            return movieMocked
        }else {
            throw IOException("movie not found")
        }
    }
}


val movieMocked =  Movie(title = "A New Hope",
    episode_id = 4,
    opening_crawl = "It is a period of civil war.\\r\\nRebel spaceships, striking\\r\\nfrom a hidden base, have won\\r\\ntheir first victory against\\r\\nthe evil Galactic Empire.\\r\\n\\r\\nDuring the battle, Rebel\\r\\nspies managed to steal secret\\r\\nplans to the Empire's\\r\\nultimate weapon, the DEATH\\r\\nSTAR, an armored space\\r\\nstation with enough power\\r\\nto destroy an entire planet.\\r\\n\\r\\nPursued by the Empire's\\r\\nsinister agents, Princess\\r\\nLeia races home aboard her\\r\\nstarship, custodian of the\\r\\nstolen plans that can save her\\r\\npeople and restore\\r\\nfreedom to the galaxy....",
    director = "George Lucas",
    producer = "Gary Kurtz, Rick McCallum",
    release_date = "1977-05-25",
    characters = arrayListOf("Teste"),
    planets = arrayListOf("Teste"),
    starships = arrayListOf("Teste"),
    vehicles = arrayListOf("Teste"),
    species = arrayListOf("Teste"),
    created = Date.from(Instant.parse("2014-12-10T14:23:31.880000Z")),
    edited = "2015-04-11T09:46:52.774897Z",
    url = "https://swapi.co/api/films/1/"
)
