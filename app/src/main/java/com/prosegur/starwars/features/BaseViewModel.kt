package com.prosegur.starwars.features

import androidx.lifecycle.*
import com.prosegur.data.utils.exceptions.DataHttpException
import com.prosegur.data.utils.exceptions.DataIOException

import timber.log.Timber
import com.prosegur.s.R
import com.prosegur.starwars.utils.SingleLiveEvent

/**
 * Created by João Victor Oliveira on 07,Maio,2019
 */


open class BaseViewModel<T> : ViewModel() {
    private val showProgressEvent = SingleLiveEvent<Void>()
    private val showShimmerEvent = SingleLiveEvent<Void>()
    private val stopShimmerEvent = SingleLiveEvent<Void>()
    private val hideProgressEvent = SingleLiveEvent<Void>()
    private val showErrorEvent = SingleLiveEvent<Int>()

    private val _data: MutableLiveData<T> = MutableLiveData()

    open val data: LiveData<T> = _data

    var triedLoadAtLeastOnce: Boolean = false
        private set

    protected suspend fun doWorkWithProgress(shimmerDetails: Boolean = false, loadData: suspend () -> T) {
        showProgressEvent.call()
        if(shimmerDetails)showShimmerEvent.call()
        triedLoadAtLeastOnce = true
        try {
            _data.value = loadData()
        } catch (e: Exception) {
            Timber.e(e.message)
            showErrorEvent.value = when (e) {
                is DataIOException -> R.string.error_io
                is DataHttpException ->
                    when (e.statusCode) {
                        404 -> R.string.error_404
                        else -> R.string.error_http_general
                    }
                else -> throw e
            }
        } finally {
            if(shimmerDetails)stopShimmerEvent.call()
            hideProgressEvent.call()
        }
    }

    fun observe(
        owner: LifecycleOwner,
        showProgress: () -> Unit,
        hideProgress: () -> Unit,
        showError: (Int) -> Unit,
        handleData: (T) -> Unit,
        showShimmer: (() -> Unit)?,
        stopShimmer: (() -> Unit)?
    ) {
        showProgressEvent.observe(owner, showProgress)
        hideProgressEvent.observe(owner, hideProgress)
        showErrorEvent.observe(owner, Observer(showError))

        if(showShimmer != null){
            showShimmerEvent.observe(owner, showShimmer)
            stopShimmerEvent.observe(owner, stopShimmer!!)
        }

        data.observe(owner, Observer(handleData))
    }

}