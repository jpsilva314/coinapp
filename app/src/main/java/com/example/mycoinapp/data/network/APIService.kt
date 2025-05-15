package com.example.mycoinapp.data.network

import com.example.mycoinapp.data.model.Album
import retrofit.Call
import retrofit.http.GET

interface APIService {

    @GET("/img/shared/technical-test.json")
    fun getAllAlbuns(): Call<ArrayList<Album>>
}