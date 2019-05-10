package com.prosegur.data.network.requestutils

import kotlinx.coroutines.Deferred

interface SynchronousRequestManager<T> {
    suspend fun getResult(deferred: Deferred<T>):T
}