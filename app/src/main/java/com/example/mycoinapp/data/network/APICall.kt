package com.example.mycoinapp.data.network

import android.content.Context
import android.widget.Toast
import com.example.mycoinapp.data.model.Album
import retrofit.GsonConverterFactory
import retrofit.Call
import retrofit.Callback
import retrofit.Response
import retrofit.Retrofit

class APICall {

    companion object {
        val url = "https://static.leboncoin.fr/"
    }

    fun getData(context: Context, callback: (ArrayList<Album>) -> Unit) {
        val retrofit: Retrofit = Retrofit.Builder().baseUrl(url).addConverterFactory(
            GsonConverterFactory.create()).build()

        val service : APIService = retrofit.create(APIService::class.java)

        val call: Call<ArrayList<Album>> = service.getAllAlbuns()

        call.enqueue(object : Callback<ArrayList<Album>> {
            override fun onResponse(response: Response<ArrayList<Album>>?, retrofit: Retrofit?) {
                if (response!!.isSuccess) {
                    val breeds: ArrayList<Album> = response.body() as ArrayList<Album>
                    callback(breeds)
                }
            }

            override fun onFailure(t: Throwable?) {
                Toast.makeText(context, "Request Fail", Toast.LENGTH_SHORT).show()
            }
        })
    }
}