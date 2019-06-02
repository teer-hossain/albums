package com.exercise.album.data.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.exercise.album.data.AlbumEntity
import com.exercise.album.data.db.AppDatabase.Companion.VERSION

@Database(entities = [AlbumEntity::class], version = VERSION)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getAlbumDao(): AlbumDao

    companion object {
        private const val DATABASE_NAME = "album_database"
        const val VERSION = 1

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Application): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }

        fun destroyInstance() {
            INSTANCE?.run { if (isOpen) close() }
            INSTANCE = null
        }
    }

}