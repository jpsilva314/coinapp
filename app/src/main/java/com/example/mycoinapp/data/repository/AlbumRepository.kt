package com.example.mycoinapp.data.repository

import android.content.Context
import androidx.annotation.WorkerThread
import com.example.mycoinapp.data.model.Album
import com.example.mycoinapp.data.model.AlbumDatabase
import com.example.mycoinapp.data.network.APICall

class AlbumRepository(private val database: AlbumDatabase)
{

    suspend fun getAlbunsDb(): List<Album> {
        val albuns = database.albumDatabaseDao.getAllDataSet()
        return albuns
    }

    fun getAlbunsApi(context: Context, callback: (List<Album>) -> Unit) =  APICall().getData(context, callback)

    @WorkerThread
    suspend fun insertItemsDb(albuns: List<Album>) {
        database.albumDatabaseDao.insertItems(albuns)
    }
}