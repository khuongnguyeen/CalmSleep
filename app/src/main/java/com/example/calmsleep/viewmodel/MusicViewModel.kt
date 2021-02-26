package com.example.calmsleep.viewmodel

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calmsleep.acivity.MainActivity
import com.example.calmsleep.api.MusicAPI
import com.example.calmsleep.api.Retrofit
import com.example.calmsleep.model.MusicData
import com.example.calmsleep.model.VerticalModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MusicViewModel : ViewModel() {
    private val musicAPI: MusicAPI = Retrofit.createRetrofit()

    val musicData = MutableLiveData<MutableList<MusicData>>()
    val musicDatas = MutableLiveData<MutableList<MusicData>>()


    val isSearchingData = ObservableBoolean(true)

    @SuppressLint("CheckResult")
    fun searchSong(songName: String?, page: Int = 1) {
        isSearchingData.set(true)
        musicAPI.songSearch(songName, page)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    musicData.value = it
                    isSearchingData.set(false)
                    Log.d("duy khuong", "----------------->>>> API <<<<-----------------")
                },
                {
                    Log.e("duy khuong", "----------------->>>> API <<<<-----------------")
                }
            )
    }

    @SuppressLint("CheckResult")
    fun searchSongs(songName: String?, page: Int = 1) {
        isSearchingData.set(true)
        musicAPI.songSearch(songName, page)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    musicDatas.value = it
                    isSearchingData.set(false)
                    Log.d("duy khuong", "----------------->>>> API <<<<-----------------")
                },
                {
                    Log.e("duy khuong", "----------------->>>> API <<<<-----------------")
                }
            )
    }

}