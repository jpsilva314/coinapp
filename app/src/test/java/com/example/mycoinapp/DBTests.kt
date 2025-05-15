package com.example.mycoinapp

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mycoinapp.data.model.Album
import com.example.mycoinapp.data.model.AlbumDao
import com.example.mycoinapp.data.model.AlbumDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DBTests {

    private lateinit var database: AlbumDatabase
    private lateinit var albumDao: AlbumDao
    @Before
    fun setupDatabase() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context, AlbumDatabase::class.java).build()
        albumDao = database.albumDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    @Throws(Exception::class)
    suspend fun AddAlbunsAndReadSize() {
        withContext(Dispatchers.IO) { albumDao.insertItems(TEST_ALBUNS) }
        assert(albumDao.getAllDataSet().size == 3)
    }


    companion object {
        private val TEST_ALBUNS = listOf<Album>(
            Album(
                albumId = 999,
                id = 6000,
                title = "test 1",
                url = "https://image.png",
                thumbnailUrl = "https://image.png"
            ),
            Album(
                albumId = 888,
                id = 6001,
                title = "test 2",
                url = "https://image.png",
                thumbnailUrl = "https://image.png"
            ),
            Album(
                albumId = 777,
                id = 6002,
                title = "test 3",
                url = "https://image.png",
                thumbnailUrl = "https://image.png"
            )
        )
    }
}