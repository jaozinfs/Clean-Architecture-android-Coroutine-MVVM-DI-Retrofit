package com.prosegur.data

interface Schema<T>{
    fun toDomain() : T
}