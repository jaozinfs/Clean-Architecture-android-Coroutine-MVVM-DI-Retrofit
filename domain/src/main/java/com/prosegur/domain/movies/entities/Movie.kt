package com.prosegur.domain.movies.entities

import java.util.*


data class Movie(
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
)
