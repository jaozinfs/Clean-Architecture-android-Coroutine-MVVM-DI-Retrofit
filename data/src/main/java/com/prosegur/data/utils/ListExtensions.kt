package com.prosegur.data.utils

import com.prosegur.data.Schema

fun <T> List<Schema<T>>.toDomain(): List<T> {
    return this.map { it.toDomain() }
}