package com.exercise.album.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.exercise.album.data.AlbumEntity
import io.reactivex.Observable

@Dao
interface AlbumDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(albums: List<AlbumEntity>)

    @Query("SELECT * FROM table_album ORDER BY title")
    fun getAlbums(): List<AlbumEntity>

    @Query("DELETE FROM table_album")
    fun deleteAll()
}