package com.prosegur.data.movies.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.prosegur.data.DataEntity
import com.prosegur.data.movies.entities.MovieEntity.Companion.TABLE_NAME
import com.prosegur.domain.movies.entities.Movie
import java.util.*


/**
 *
 * @author João victor
 * @since 14/02/2019
 */

@Entity(tableName = TABLE_NAME)
data class MovieEntity(
    @PrimaryKey
    var id: Int,
    var characters: List<String>,
    var created: Date,
    var director: String,
    var edited: String,
    var episode_id: Int,
    var opening_crawl: String,
    var planets: List<String>,
    var producer: String,
    var release_date: Date,
    var species: List<String>,
    var starships: List<String>,
    var title: String,
    var url: String,
    var vehicles: List<Any>


) : DataEntity<Movie> {
    override fun toDomain() =
        Movie(
            characters = characters,
            created = created,
            director = director,
            edited = edited,
            episode_id = episode_id,
            opening_crawl = opening_crawl,
            planets = planets,
            producer = producer,
            release_date = release_date,
            species = species,
            starships = starships,
            title = title,
            url = url,
            vehicles = vehicles
        )

    companion object {
        const val TABLE_NAME = "CHARACTERS"
    }
}