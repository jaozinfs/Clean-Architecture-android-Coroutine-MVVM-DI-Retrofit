package com.prosegur.data.films

/**
 * Created by João Victor Oliveira on 07,Maio,2019
 */

interface UseCase<T, Params> {
    suspend fun execute(params: Params): T
}