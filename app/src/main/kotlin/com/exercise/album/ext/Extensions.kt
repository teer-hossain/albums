package com.exercise.album.ext

import androidx.annotation.StringRes
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.exercise.album.R
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

inline fun <T> LiveData<T>.reObserve(owner: LifecycleOwner, observer: Observer<T>) {
    removeObserver(observer)
    observe(owner, observer)
}

inline fun Throwable.getMessage(@StringRes msgResId: Int = R.string.msg_empty_data) = when (this) {
    is ConnectException -> R.string.msg_no_internet
    is SocketTimeoutException -> R.string.msg_no_internet
    is TimeoutException -> R.string.msg_no_internet
    is UnknownHostException -> R.string.err_server_host
    else -> msgResId
}