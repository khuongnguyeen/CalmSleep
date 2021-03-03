package com.example.calmsleep.application

import android.annotation.SuppressLint
import android.app.Application
import androidx.room.Room
import com.example.calmsleep.db.AppDatabase
import com.example.calmsleep.db.DataBaseManager
import com.example.calmsleep.model.FavouriteData
import com.example.calmsleep.model.MusicData
import com.example.calmsleep.model.MusicOnlineMp3
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
        private val musicDownload = mutableListOf<MusicData>()
        private val favouriteData = mutableListOf<FavouriteData>()
        private  val musicOffline = mutableListOf<MusicOnlineMp3>()
        fun getMusicOffline() = musicOffline
        var ISPLAYING = false
        fun getDB() = database
        fun getMusicDownLoad() = musicDownload
        fun getMD() = musicDataBase
        fun getFavourites() = favouriteData
        private lateinit var db: AppDatabase
        fun getDBLocal() = db
    }



    override fun onCreate() {
        super.onCreate()
        database = DataBaseManager(applicationContext)
        DataBaseManager(this).createFavourites()
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name.sqlite"
        ).allowMainThreadQueries()
            .build()
    }
}
