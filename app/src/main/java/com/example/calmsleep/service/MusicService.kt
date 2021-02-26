package com.example.calmsleep.service

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.AsyncTask
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.example.calmsleep.R
import com.example.calmsleep.acivity.LoadingAcivity
import com.example.calmsleep.application.MyApp
import com.example.calmsleep.manager.MusicOnlineManager
import com.example.calmsleep.model.MusicData
import com.example.calmsleep.model.VerticalModel
import org.jsoup.Jsoup

@Suppress("DEPRECATION")
class MusicService : LifecycleService(){
    val play = MusicOnlineManager()
    private val musicData = mutableListOf<MusicData>()
    private val musicDatas = mutableListOf<MusicData>()
    fun getMusicData() = musicData
    fun getMusicDatas() = musicDatas

    override fun onCreate() {
        super.onCreate()
        MyApp.getMusic().musicData.observe(this, Observer {
            musicData.clear()
            musicData.addAll(it)
            getData()
        })
        MyApp.getMusic().musicDatas.observe(this, Observer {
            musicDatas.clear()
            musicDatas.addAll(it)
        })

    }

    private fun getData() {
        for (j in 0..5) {
            val k = mutableListOf<MusicData>()
            k.clear()
            for (i in musicData) {
                if (j == i.id) {
                    k.add(i)
                }
            }
            val verticalModel = VerticalModel("Title $j", k)
            MyApp.musicDataVertical.add(verticalModel)

        }
        for (i in MyApp.musicDataVertical){
            Log.d("okkkkkkkk","${i.text},${i.list}")
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        if (intent!=null){
            action(intent)
        }
        return START_NOT_STICKY
    }

    class MyBinder(val service: MusicService) : Binder()

    override fun onBind(intent: Intent): IBinder {
        super.onBind(intent)
        return MyBinder(this)
    }

    private fun action(intent: Intent) {
        if (intent.action == null) {
            return
        }
        when (intent.action) {
            "PREVIOUS" -> {
                if (MyApp.POSITION == 0) {

                } else {

                }
            }
            "PLAY" -> {

            }
            "NEXT" -> {
                if (MyApp.POSITION == musicData.size - 1) {

                } else {

                }
            }
            "CANCEL" -> {
                play.stop()
                stopForeground(true)
                MyApp.ISPLAYING = false

            }
        }
    }

    fun play(position: Int,data:MutableList<MusicData>){
        createNotification(position,data)
        if ( data[position].linkMusic == null) {
            getLinkMusicAsync(data[position].linkSong, position,data)
        } else {
            play.setData(data[position].linkMusic!!)
        }
    }

    private fun createPendingIntentMusic(remoteViews: RemoteViews) {
        val intentPrevious = Intent(this, MusicService::class.java)
        intentPrevious.action = "PREVIOUS"
        val pendingIntentPrevious =
            PendingIntent.getService(this, 1, intentPrevious, PendingIntent.FLAG_UPDATE_CURRENT)
        remoteViews.setOnClickPendingIntent(R.id.btn_pre, pendingIntentPrevious)

        val intentPlay = Intent(this, MusicService::class.java)
        intentPlay.action = "PLAY"
        val pendingIntentPlay =
            PendingIntent.getService(this, 2, intentPlay, PendingIntent.FLAG_UPDATE_CURRENT)
        remoteViews.setOnClickPendingIntent(R.id.btn_play, pendingIntentPlay)

        val intentNext = Intent(this, MusicService::class.java)
        intentNext.action = "NEXT"
        val pendingIntentNext =
            PendingIntent.getService(this, 3, intentNext, PendingIntent.FLAG_UPDATE_CURRENT)
        remoteViews.setOnClickPendingIntent(R.id.btn_next, pendingIntentNext)

        val intentCancel = Intent(this, MusicService::class.java)
        intentCancel.action = "CANCEL"
        val pendingIntentCancel =
            PendingIntent.getService(this, 4, intentCancel, PendingIntent.FLAG_UPDATE_CURRENT)
        remoteViews.setOnClickPendingIntent(R.id.btn_cancel, pendingIntentCancel)
    }

    fun createNotification(position: Int,data:MutableList<MusicData>, isPlaying: Boolean = true) {
        createChannel()
        val remoteView = RemoteViews(packageName, R.layout.notification)
        remoteView.setTextViewText(R.id.tv_name_2, data[position].songName)
        remoteView.setImageViewBitmap(
            R.id.btn_play,
            BitmapFactory.decodeResource(
                resources,
                if (isPlaying) R.drawable.pause else
                    R.drawable.play
            )
        )
        createPendingIntentMusic(remoteView)
        val notification = NotificationCompat.Builder(this, "noti")
            .setSmallIcon(R.drawable.baseline_play_circle_white_24dp)
            .setContent(remoteView)
            .setCustomBigContentView(remoteView)
            .setDefaults(0)
            .build()

        if (data[position].linkImage != null) {
            Glide.with(this).asBitmap().load(
                data[position].linkImage
            ).into(
                object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
                    ) {
                        remoteView.setImageViewBitmap(R.id.iv_image, resource)
                        startForeground(1, notification)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {

                    }
                }
            )
        }
        startForeground(1, notification)
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val import = NotificationManager.IMPORTANCE_LOW
            val channel = NotificationChannel("noti", "Nguyễn Duy Khương", import)
            channel.setSound(null,null)
            channel.description = "noti"
            val noti = getSystemService(NotificationManager::class.java)
            noti.createNotificationChannel(channel)
        }
    }

   fun getLinkMusicAsync(linkHtml: String, position: Int,data:MutableList<MusicData>) {
        val async = @SuppressLint("StaticFieldLeak")
        object : AsyncTask<Void, Void, String?>() {
            override fun doInBackground(vararg params: Void?): String? {
                val link = getLinkMusic(linkHtml = linkHtml)
                data[position].linkMusic = link
                return link
            }

            override fun onPostExecute(result: String?) {
                data[position].linkMusic = result
                if (result != null) {
                    play.setData(result)
                }

            }
        }

        async.execute()
    }

    private fun getLinkMusic(linkHtml: String): String? {
        val doc = Jsoup.connect(linkHtml).get()
        val els = doc.select("div.tab-content")
        for (e in els.first().select("ul.list-unstyled")
            .first().select("a.download_item")) {
            val link = e.attr("href")
            if (link.contains(".mp3")) {
                return link
            }
        }
        return null
    }

}