package com.example.calmsleep.manager

import android.media.MediaPlayer
import android.util.Log

class MusicOnlineManager : MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener {
    private var mp:MediaPlayer?=null

    fun setPath(path:String){
        release()
        mp = MediaPlayer()
        mp?.setOnErrorListener(this)
        mp?.setDataSource(path)
        prepareAsyn()


    }

    fun setLooping(isKhuong:Boolean){
        mp?.isLooping = isKhuong
    }

    fun release(){
        mp?.release()
        mp = null
    }
    fun prepareAsyn(){
        mp?.setOnPreparedListener(this)
        mp?.prepareAsync()
    }

    override fun onError(mp: MediaPlayer?, what: Int, extra: Int): Boolean {
        Log.e("MediaManagerOnline", "onError........$extra   , $what ")
        return true
    }

    override fun onPrepared(mp: MediaPlayer?) {

        start()
    }

    fun start(){
        mp?.start()
    }

    fun pause(){
        mp?.pause()
    }
    fun stop(){
        mp?.stop()
    }
}