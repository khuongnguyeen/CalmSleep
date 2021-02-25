package com.example.calmsleep.acivity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.calmsleep.R
import com.example.calmsleep.application.MyApp
import com.example.calmsleep.databinding.LoadingBinding
import com.example.calmsleep.service.MusicService

class LoadingAcivity : AppCompatActivity() {
    private lateinit var binding: LoadingBinding
    private var conn: ServiceConnection? = null
    companion object{
        var service: MusicService? = null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.loading)
        openServiceUnBound()
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivities(arrayOf(intent))
        }, 3000)


    }
    private fun openServiceUnBound() {
        val intent = Intent(applicationContext, MusicService::class.java)
        applicationContext!!.startService(intent)
        createConnectService()
    }

    override fun onDestroy() {
        super.onDestroy()
        applicationContext!!.unbindService(conn!!)
    }

    private fun createConnectService() {
        conn = object : ServiceConnection {
            override fun onServiceDisconnected(name: ComponentName?) {

            }

            override fun onServiceConnected(name: ComponentName?, binder: IBinder) {
                val myBinder = binder as MusicService.MyBinder
                service = myBinder.service
                if (service!!.getMusicData().size == 0) {
                    MyApp.getMusic().searchSong(null)
                }
            }
        }
        val intent = Intent()
        intent.setClass(applicationContext!!, MusicService::class.java)
        applicationContext!!.bindService(intent, conn!!, Context.BIND_AUTO_CREATE)
    }
}