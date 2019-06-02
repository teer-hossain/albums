package com.exercise.album.data

import com.exercise.album.BuildConfig
import com.exercise.album.support.NetworkService
import io.reactivex.Observable
import retrofit2.http.GET

interface AlbumAPI {

    companion object : NetworkService<AlbumAPI>(BuildConfig.BASE_API_URL, AlbumAPI::class.java)

    @GET("albums")
    fun getAlbums(): Observable<List<Album>>

}