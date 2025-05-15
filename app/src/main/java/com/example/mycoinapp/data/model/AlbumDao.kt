package com.example.mycoinapp.data.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AlbumDao {

    @Insert(onConflict = OnConflictStrategy.Companion.IGNORE)
    suspend fun insertItems(albuns: List<Album>)

    @Query("SELECT * FROM album")
    suspend fun getAllDataSet():List<Album>

}