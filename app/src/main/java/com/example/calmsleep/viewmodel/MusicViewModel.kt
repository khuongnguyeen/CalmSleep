package com.example.calmsleep.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calmsleep.api.MusicAPI
import com.example.calmsleep.api.Retrofit
import com.example.calmsleep.model.MusicData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MusicViewModel : ViewModel() {
    private val musicAPI: MusicAPI = Retrofit.createRetrofit()

    val musicData = MutableLiveData<MutableList<MusicData>>()
    val isSearchingData = ObservableBoolean(false)

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

}