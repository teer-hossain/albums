package com.exercise.album.support

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class NetworkService<I : Any>(private val url: String, private val token: Class<I>) {

    @Volatile
    private var INSTANCE: I? = null

    fun getInstance(): I = INSTANCE
        ?: synchronized(this) {
            INSTANCE
                ?: buildService().also { INSTANCE = it }
        }

    private fun buildService(): I {
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        return retrofit.create(token)
    }
}