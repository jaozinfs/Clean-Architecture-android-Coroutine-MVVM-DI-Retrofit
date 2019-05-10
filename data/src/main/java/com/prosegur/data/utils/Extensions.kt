package com.prosegur.data.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.ImageView
import java.util.*


/**
 *
 * @author João victor
 * @since 07/05/2019
 */

val Context.hasNetwork: Boolean
    get() = run {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        activeNetwork != null && activeNetwork.isConnected
    }

