package com.exercise.album.data

import com.exercise.album.support.entities.Outcome
import com.exercise.album.support.schedulers.BaseSchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

interface AlbumDataSource {

    val scheduler: BaseSchedulerProvider

    val albumResponse: PublishSubject<Outcome<List<Album>>>

    fun getAlbums(disposable: CompositeDisposable)

}