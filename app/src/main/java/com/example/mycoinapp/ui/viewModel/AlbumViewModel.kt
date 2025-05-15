package com.example.mycoinapp.ui.viewModel

import android.app.Application
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycoinapp.data.model.Album
import com.example.mycoinapp.data.model.getDatabase
import com.example.mycoinapp.data.repository.AlbumRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

interface AlbumUiState {
    data class Success(val albuns: List<Album>) : AlbumUiState
    data object Error : AlbumUiState
    data object Loading : AlbumUiState
}

class AlbumViewModel(application: Application) : AndroidViewModel(application) {
    var albumUiState: AlbumUiState by mutableStateOf(AlbumUiState.Loading)
        private set
    private var listResult: List<Album>? = null
    private val database = getDatabase(application)
    private val repository = AlbumRepository(database)

    init {
        val context = getApplication<Application>().applicationContext
        getData(context)
    }

    private fun getData(context: Context) {

        viewModelScope.launch {
            albumUiState = AlbumUiState.Loading
            var loadFromDb = false
            //get data from database
            try {
                val albunsdb = repository.getAlbunsDb()
                loadFromDb = !albunsdb.isEmpty()
                albumUiState =
                    if (!loadFromDb) AlbumUiState.Loading else AlbumUiState.Success(albunsdb)
            } catch (e: Exception) {
                AlbumUiState.Error
            }
            //get data from API
            if (!loadFromDb) {
                try {
                    repository.getAlbunsApi(context) { result ->
                        listResult = result
                        storeData(listResult!!)
                        albumUiState = AlbumUiState.Success(listResult!!)

                    }
                } catch (e: IOException) {
                    AlbumUiState.Error
                } catch (e: HttpException) {
                    AlbumUiState.Error
                }
            }
        }
    }

    fun storeData(albuns: List<Album>) = viewModelScope.launch { repository.insertItemsDb(albuns) }
}