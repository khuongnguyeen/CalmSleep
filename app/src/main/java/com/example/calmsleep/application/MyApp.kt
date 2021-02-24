package com.example.calmsleep.application

import android.app.Application
import com.example.calmsleep.model.MusicData
import com.example.calmsleep.viewmodel.MusicViewModel

class MyApp : Application(){
    companion object{
        private val musicViewModel = MusicViewModel()
        fun getMusic() = musicViewModel
        var SETTING = 1
        var POSITION = 0
        var ISPLAYING = false
        private val musicData = mutableListOf<MusicData>()
        fun getMusicData() = musicData
        fun getViewModel() = musicViewModel
    }

    override fun onCreate() {
        super.onCreate()

    }
}