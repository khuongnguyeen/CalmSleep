package com.example.calmsleep.api

import com.example.calmsleep.model.MusicData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicAPI{
    @GET("/api/searchSong")
    fun songSearch(
        @Query("songName") songName:String?,
        @Query("currentPage") currentPage:Int=1
    ): Observable<MutableList<MusicData>>

}