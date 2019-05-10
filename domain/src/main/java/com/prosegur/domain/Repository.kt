package com.prosegur.data.films

/**
 * Created by João Victor Oliveira on 07,Maio,2019
 */

interface Repository<T> {
    suspend fun insert(t: T)
    suspend fun delete(t: T)
    suspend fun update(t: T)
    suspend fun list(page: Int, apiKey: String?): List<T>
    suspend fun get(id: Int): T?
}