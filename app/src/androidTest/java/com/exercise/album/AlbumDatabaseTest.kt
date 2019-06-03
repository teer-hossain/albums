package com.exercise.album

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.exercise.album.data.Album
import com.exercise.album.data.AlbumEntity
import com.exercise.album.data.db.AlbumDao
import com.exercise.album.data.db.AppDatabase
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class AlbumDatabaseTest {

    private lateinit var albumDao: AlbumDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun writeAlbumAndReadList() {
        val albums = arrayListOf<AlbumEntity>().apply {
            (1..3).forEach { add(AlbumEntity(it, it, "Title$it")) }
        }
        albumDao.insertAll(albums)
        Assert.assertEquals(albums.size, albumDao.getAlbums().size)
    }

}