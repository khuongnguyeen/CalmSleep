package com.example.calmsleep.model

data class MusicData(
    val songName: String,
    val artistName: String,
    val linkSong: String,
    var linkImage: String? = null,
    var linkMusic: String? = null
)