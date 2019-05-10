package com.prosegur.data

import com.prosegur.data.movies.schemas.MovieSchema

data class Response<T>(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<T>
)