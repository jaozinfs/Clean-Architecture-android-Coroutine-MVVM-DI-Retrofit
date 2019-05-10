package com.prosegur.data

import com.prosegur.data.films.Repository
import com.prosegur.data.films.SWApiImp
import com.prosegur.data.network.requestutils.SynchronousRequestManagerImpl
import com.prosegur.data.utils.exceptions.DataHttpException
import com.prosegur.data.utils.exceptions.DataIOException
import com.prosegur.data.utils.toDomain
import kotlinx.coroutines.Deferred

/**
 *
 * @author João victor
 * @since 22/01/2019
 */
abstract class BaseCloudRepository<T, S>(protected val api: SWApiImp) : Repository<T> where S : Schema<T> {

    var syncRequestList = SynchronousRequestManagerImpl<Response<S>>()
    var syncRequestGet = SynchronousRequestManagerImpl<S>()


    abstract val listMethod: (Int, String?) -> Deferred<Response<S>>
    abstract val getMethod: (Int) -> Deferred<S>

    @Throws(DataIOException::class, DataHttpException::class)
    override suspend fun get(id: Int): T? {
        val result = syncRequestGet.getResult(getMethod(id))
        return result.toDomain()
    }

    @Throws(DataIOException::class, DataHttpException::class)
    suspend fun listResponse(page: Int, key: String?): Response<S> {
        if(key != null){
            return syncRequestList.getResult(listMethod(page, key))
        }
        return syncRequestList.getResult(listMethod(page, null))
    }

    @Throws(DataIOException::class, DataHttpException::class)
    override suspend fun list(page: Int, apiKey: String?): List<T> {
        return listResponse(page, apiKey).results.toDomain()
    }

    override suspend fun insert(t: T) = throw UnsupportedOperationException()
    override suspend fun update(t: T) = throw UnsupportedOperationException()
    override suspend fun delete(t: T) = throw UnsupportedOperationException()

}