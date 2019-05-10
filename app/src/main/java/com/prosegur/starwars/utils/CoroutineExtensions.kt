package com.prosegur.starwars.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


private val mainDispatcher = Dispatchers.Main
private val ioDispatcher = Dispatchers.IO

fun launchUI(block: suspend () -> Unit) {
    GlobalScope.launch(mainDispatcher) {
        block()
    }
}

suspend fun <T> asyncAwait(block: suspend () -> T): T {
    return GlobalScope.async(ioDispatcher) {
        block()
    }.await()
}