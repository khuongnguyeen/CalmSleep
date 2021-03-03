package com.example.calmsleep.model

import androidx.room.Entity
import androidx.room.PrimaryKey


data class MusicData(
    val id: Int,
    val songName: String,
    val artistName: String,
    val linkSong: String,
    var linkImage: String? = null,
    var linkMusic: String? = null,
    var categoryId: Int,
    var favourite: Int? = null,
    val duration: Long? = null,
    var albumid: Int
)


data class FavouriteData(
    val id: Int,
    val musicId:Int,
    val albumId: Int,
    val categoryId: Int
)

@Entity
data class MusicOnlineMp3 (
    @PrimaryKey
    var id:Int=0,
    var songName:String,
    var linkMusic:String,
    var linkHtml:String,
    var pathOnline:String
)