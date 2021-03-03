package com.example.calmsleep.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import android.os.Environment
import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.example.calmsleep.application.MyApp
import com.example.calmsleep.model.MusicData
import com.example.calmsleep.model.MusicOnlineMp3
import org.jsoup.Jsoup
import java.io.FileOutputStream
import java.net.URL

class MusicViewModel : ViewModel() {

    val isSearchingData = ObservableBoolean(true)

    fun saveInToDatabase(item: MusicData, context: Context) {
        if (MyApp.getDBLocal().musicOnlineMp3Dao().findOneById(
                item.id
            ) != null
        ) {
            return
        }
        val path =
            Environment.getDataDirectory().path +
                    "/data/" + context.packageName + "/" +
                    item.songName + ".mp3"

        val input = URL(item.linkMusic).openStream()
        val out = FileOutputStream(path)
        val b = ByteArray(1024)
        var le = input.read(b)
        while (le >=0){
            out.write(b, 0, le)
            le = input.read(b)
        }
        out.close()
        input.close()

        val mp3 = MusicOnlineMp3(
            item.id,
            item.songName, item.linkMusic!!, item.linkSong, path
        )
        MyApp.getDBLocal().musicOnlineMp3Dao().insertOne(mp3)
    }

}