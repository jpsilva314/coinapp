package com.example.mycoinapp.data.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Album::class],version=2,exportSchema = false)
abstract class AlbumDatabase : RoomDatabase() {
    abstract val albumDatabaseDao: AlbumDao
}

private lateinit var INSTANCE: AlbumDatabase

fun getDatabase(context: Context):AlbumDatabase
{
    if(!::INSTANCE.isInitialized)
    {
        INSTANCE=
            Room.databaseBuilder(context.applicationContext, AlbumDatabase::class.java,"album_database")
                .fallbackToDestructiveMigration(false).build()
    }
    return INSTANCE
}