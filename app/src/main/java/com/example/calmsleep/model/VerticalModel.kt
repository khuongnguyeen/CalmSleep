package com.example.calmsleep.model


data class MusicData(
    val id: Int = 0,
    val songName: String,
    val artistName: String,
    val linkSong: String,
    var linkImage: String? = null,
    var linkMusic: String? = null,
    var categoryid: Int? = null,
    var albumid: Int? = null
)