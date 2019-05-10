package com.prosegur.data.network.requestutils

import com.prosegur.data.utils.exceptions.DataException
import com.prosegur.data.utils.exceptions.DataHttpException
import com.prosegur.data.utils.exceptions.DataIOException
import kotlinx.coroutines.Deferred
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

/**
 *
 * @author João victor
 * @since 22/01/2019
 */
class SynchronousRequestManagerImpl<T> : SynchronousRequestManager<T> {
    @Throws(DataException::class)
    override suspend fun getResult(deferred: Deferred<T>): T {
        try {
            val result = deferred.await()
            return result
        } catch (e: Exception) {
            Timber.e(e)
            when (e) {
                is IOException -> throw DataIOException(e.message, e)
                is HttpException -> throw DataHttpException(e.message, e.code(), e)
                else -> throw e
            }

        }
    }


}