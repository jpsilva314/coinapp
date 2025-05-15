package com.example.mycoinapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "album")
data class Album(@SerializedName("albumId")
                 val albumId: Int = 0,
                 @PrimaryKey
                 @SerializedName("id")
                 val id: Int = 0,
                 @SerializedName("title")
                 val title: String = "",
                 @SerializedName("url")
                 val url: String = "",
                 @SerializedName("thumbnailUrl")
                 val thumbnailUrl: String = "")