package com.example.calmsleep.acivity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.Color
import android.os.*
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.calmsleep.R
import com.example.calmsleep.application.MyApp
import com.example.calmsleep.databinding.LoadingBinding
import com.example.calmsleep.model.MusicData
import com.example.calmsleep.service.MusicService
import org.jsoup.Jsoup

@Suppress("DEPRECATION")
class LoadingAcivity : AppCompatActivity() {
    private lateinit var binding: LoadingBinding
    private var conn: ServiceConnection? = null

    companion object {
        var service: MusicService? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.loading)
        sttBar()
        openServiceUnBound()
        Handler().postDelayed({
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivities(arrayOf(intent))
        },4000)

    }

    private fun sttBar() {
        if (Build.VERSION.SDK_INT in 19..20) WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS.setWindowFlag(
            this,
            true
        )
        if (Build.VERSION.SDK_INT >= 19) window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        if (Build.VERSION.SDK_INT >= 21) {
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS.setWindowFlag(this, false)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    private fun Int.setWindowFlag(activity: Activity, on: Boolean) {
        val win: Window = activity.window
        val winParams: WindowManager.LayoutParams = win.attributes
        if (on) winParams.flags = winParams.flags or this else winParams.flags =
            winParams.flags and inv()
        win.attributes = winParams
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
            }
        }
        val intent = Intent()
        intent.setClass(applicationContext!!, MusicService::class.java)
        applicationContext!!.bindService(intent, conn!!, Context.BIND_AUTO_CREATE)
    }
}