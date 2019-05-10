package com.prosegur.starwars

import java.io.IOException

interface SimulateRequest<T>{
    @Throws(IOException::class)
    fun requestByID(movieId: Int):T
}