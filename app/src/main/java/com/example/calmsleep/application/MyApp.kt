package com.example.calmsleep.application

import android.annotation.SuppressLint
import android.app.Application
import com.example.calmsleep.db.DataBaseManager
import com.example.calmsleep.model.MusicData
import com.example.calmsleep.viewmodel.MusicViewModel

class MyApp : Application() {
    companion object {
        private val musicViewModel = MusicViewModel()
        @SuppressLint("StaticFieldLeak")
        private lateinit var database : DataBaseManager
        fun getMusicViewModel() = musicViewModel
        var SETTING = true
        var POSITION = 0
        private val musicDataBase = mutableListOf<MusicData>()
        var ISPLAYING = false
        fun getDB() = database
        fun getMD() = musicDataBase
    }



    override fun onCreate() {
        super.onCreate()
        database = DataBaseManager(applicationContext)
    }
}
