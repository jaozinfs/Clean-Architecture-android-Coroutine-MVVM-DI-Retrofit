package com.prosegur.data.movies.schemas

import com.prosegur.data.Schema
import com.prosegur.domain.movies.entities.Movie
import java.util.*

data class MovieSchema(
    val characters: List<String>,
    val created: Date,
    val director: String,
    val edited: String,
    val episode_id: Int,
    val opening_crawl: String,
    val planets: List<String>,
    val producer: String,
    val release_date: Date,
    val species: List<String>,
    val starships: List<String>,
    val title: String,
    val url: String,
    val vehicles: List<Any>
) : Schema<Movie> {
    override fun toDomain(): Movie =
        Movie(
            characters,
            created,
            director,
            edited,
            episode_id,
            opening_crawl,
            planets,
            producer,
            release_date,
            species,
            starships,
            title,
            url,
            vehicles
            )
}