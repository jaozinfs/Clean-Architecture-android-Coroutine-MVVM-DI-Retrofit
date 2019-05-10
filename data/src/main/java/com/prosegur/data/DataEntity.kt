package com.prosegur.data

interface DataEntity<T> {
    fun toDomain(): T
}