package com.prosegur.data.utils

import com.prosegur.data.utils.exceptions.DataHttpException
import java.io.File


fun Any.loadJsonFromResources(fileName : String) : String {
    // Load the JSON response
    val uri = this.javaClass.classLoader.getResource("movies/$fileName")
    System.out.println(" assa ${uri.readBytes()}")
    val file = File(uri.path)
    return String(file.readBytes())
}
