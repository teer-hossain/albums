package com.exercise.album.data

import android.app.Application
import com.exercise.album.data.db.AlbumDao
import com.exercise.album.data.db.AppDatabase
import com.exercise.album.ext.*
import com.exercise.album.support.entities.Outcome
import com.exercise.album.support.schedulers.BaseSchedulerProvider
import com.exercise.album.support.schedulers.SchedulerProvider
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

class AlbumRepository private constructor(app: Application) : AlbumDataSource {

    private val albumService = AlbumAPI.getInstance()
    private val albumDao = AppDatabase.getInstance(app).getAlbumDao()

    override val scheduler: BaseSchedulerProvider = SchedulerProvider()

    override val albumResponse: PublishSubject<Outcome<List<Album>>> = PublishSubject.create()

    /**
     * Fetch all entries from web service and insert into database. If unable to
     * get data from web service, fetch the data from local database.
     */
    override fun getAlbums(disposable: CompositeDisposable) {
        albumResponse.loading(true)
        albumService.getAlbums()
            .map(::databaseEntityMapper)
            .map(::refreshDatabase)
            .map(::albumDataMapper)
            .performOnBackOutOnMain(scheduler)
            .subscribe({
                albumResponse.success(it)
            }, { error ->
                error.printStackTrace()
                getAlbumFromDb(disposable)
            })
            .addTo(disposable)
    }

    /**
     * Fetch all album entries from database
     */
    private fun getAlbumFromDb(disposable: CompositeDisposable) {
        getAlbums(albumDao)
            .map(::albumDataMapper)
            .performOnBackOutOnMain(scheduler)
            .subscribe({
                albumResponse.success(it)
            }, {
                it.printStackTrace()
                albumResponse.failed(it)
            })
            .addTo(disposable)
    }

    private fun refreshDatabase(entities: List<AlbumEntity>): List<AlbumEntity> {
        albumDao.deleteAll()
        albumDao.insertAll(entities)
        return albumDao.getAlbums()
    }

    /**
     * Attach observable to get entries from database.
     */
    private fun getAlbums(albumDao: AlbumDao): Single<List<AlbumEntity>> = Single.fromCallable { albumDao.getAlbums() }

    /**
     * Maps plain data class object to database entities.
     */
    private fun databaseEntityMapper(albums: List<Album>): List<AlbumEntity> = arrayListOf<AlbumEntity>().apply {
        albums.forEach { add(AlbumEntity(it.id, it.userId, it.title)) }
    }

    /**
     * Maps database entities to plain object.
     */
    private fun albumDataMapper(entities: List<AlbumEntity>): List<Album> = arrayListOf<Album>().apply {
        entities.forEach { add(Album(it.id, it.userId, it.title)) }
    }

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: AlbumDataSource? = null

        fun getInstance(app: Application) =
            instance ?: synchronized(this) {
                instance ?: AlbumRepository(app).also { instance = it }
            }

        fun destroyInstance() {
            instance = null
        }
    }
}