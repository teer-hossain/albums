package com.exercise.album.ext

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

val Context.connectivityManager: ConnectivityManager
    get() = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

inline fun Context.hasConnection(): Boolean {
    var isConnected = false
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    if (activeNetwork != null && activeNetwork.isConnected)
        isConnected = true
    return isConnected
}

inline fun FragmentActivity.addFragment(@IdRes idRes: Int, fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .add(idRes, fragment).commit()
}