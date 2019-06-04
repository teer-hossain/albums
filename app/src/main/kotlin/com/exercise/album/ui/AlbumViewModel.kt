package com.exercise.album.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.exercise.album.data.Album
import com.exercise.album.data.AlbumRepository
import com.exercise.album.data.db.AppDatabase
import com.exercise.album.ext.toLiveData
import com.exercise.album.support.entities.Outcome
import io.reactivex.disposables.CompositeDisposable

class AlbumViewModel(app: Application) : AndroidViewModel(app) {

    private val repository = AlbumRepository.getInstance(app)
    private val disposable = CompositeDisposable()

    val albumsOutcome: LiveData<Outcome<List<Album>>> by lazy {
        repository.albumResponse.toLiveData(disposable)
    }

    fun getAlbum() {
        repository.getAlbums(disposable)
    }

    override fun onCleared() {
        disposable.clear()
        AppDatabase.destroyInstance()
        AlbumRepository.destroyInstance()
        super.onCleared()
    }

}