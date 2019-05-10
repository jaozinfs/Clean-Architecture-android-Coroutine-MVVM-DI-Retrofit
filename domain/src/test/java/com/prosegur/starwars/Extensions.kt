package com.prosegur.starwars

const val pageSize = 3

fun <T> List<T>.getItemsPage(page:Int):List<T>{
    val fromIndex = page * pageSize - pageSize
    var toIndex = fromIndex + pageSize

    if (toIndex > this.size) {
        toIndex = this.size
    }

    return this.subList(fromIndex, toIndex).toList()
}