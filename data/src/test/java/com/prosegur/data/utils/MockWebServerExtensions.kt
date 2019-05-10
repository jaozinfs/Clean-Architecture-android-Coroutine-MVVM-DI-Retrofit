package com.prosegur.data.utils

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer


/**
 *
 * @author João victor
 * @since 23/01/2019
 */

fun MockWebServer.enqueueResponse(responseCode: Int, body: String = "error") {
    enqueue(
        MockResponse()
            .setResponseCode(responseCode)
            .setBody(body)
    )
}