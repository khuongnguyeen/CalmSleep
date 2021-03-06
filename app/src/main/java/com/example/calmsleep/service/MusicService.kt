package com.example.calmsleep.service

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.AsyncTask
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.provider.MediaStore
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.example.calmsleep.R
import com.example.calmsleep.application.MyApp
import com.example.calmsleep.manager.MusicOnlineManager
import com.example.calmsleep.model.MusicOnlineMp3
import org.jsoup.Jsoup

@Suppress("DEPRECATION")
class MusicService : LifecycleService() {
    val play = MusicOnlineManager()
    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        if (intent!!.getIntExtra("setting", 0) == 1) {
            createNotification("Have a wonderful day ahead, you :)")

        } else if (intent.getIntExtra("setting", 0) == 2) {
            createNotification("Sleep now. Boost Productivity Tomorrow :)")

        } else {
            MyApp.getMD().clear()
            MyApp.getMD().addAll(MyApp.getDB().getMusic())
            MyApp.getFavourites().clear()
            MyApp.getFavourites().addAll(MyApp.getDB().getFavourites())
        }

        action(intent)
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
                if (MyApp.POSITION == 1) {
                    play(MyApp.getDB().getMusic().size)
                } else {
                    play(MyApp.POSITION - 1)
                }
            }
            "PLAY" -> {
                playPause(MyApp.POSITION)
            }
            "NEXT" -> {
                if (MyApp.POSITION == MyApp.getDB().getMusic().size) {
                    play(1)
                } else {
                    play(MyApp.POSITION + 1)
                }
            }
            "CANCEL" -> {
                play.stop()
                stopForeground(true)
                MyApp.ISPLAYING = false

            }
        }
    }

    fun play(id: Int) {
        createNotification(id)

        getLinkMusicAsyn(id)
        MyApp.POSITION = id
        MyApp.ISPLAYING = true
    }

    fun playPause(id: Int) {
        if (MyApp.ISPLAYING) {
            play.pause()
            createNotification(id, false)
            MyApp.ISPLAYING = false
        } else {
            createNotification(id)
            getLinkMusicAsyn(id)
            MyApp.POSITION = id
            MyApp.ISPLAYING = true
        }
    }

    private fun getLinkMusicAsyn(id: Int) {
        val asyn = @SuppressLint("StaticFieldLeak")
        object : AsyncTask<Void, Void, String?>() {
            override fun doInBackground(vararg params: Void?): String? {
                val link = getLinkMusic(linkHtml = MyApp.getDB().getMusic(id).linkSong)
                MyApp.getDB().getMusic(id).linkMusic = link
                return link
            }

            override fun onPostExecute(result: String?) {
                MyApp.getDB().getMusic(id).linkMusic = result
                if (result != null) {
                    play.setPath(result)
                }
            }
        }
        asyn.execute()
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

    private fun createNotification(s: String) {
        createChannel2()
        val no = NotificationCompat.Builder(this, "no") as NotificationCompat.Builder
        no.setSmallIcon(R.drawable.baseline_play_circle_white_24dp)
            .setContentTitle("Calm Sleep")
            .setContentText(s)
            .setOngoing(false)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setDefaults(Notification.DEFAULT_ALL)
            .setAutoCancel(true)
            .build()
        val mNotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager.notify(2, no.build())
//        startForeground(2, no)
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

    private fun createNotification(
        id: Int,
        isPlaying: Boolean = true
    ) {
        createChannel()
        val remoteView = RemoteViews(packageName, R.layout.notification)
        remoteView.setTextViewText(R.id.tv_name_2, MyApp.getDB().getMusic(id).songName)
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

        if (MyApp.getDB().getMusic(id).linkImage != null) {
            Glide.with(this).asBitmap().load(
                MyApp.getDB().getMusic(id).linkImage
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
            channel.setSound(null, null)
            channel.description = "noti"
            val noti = getSystemService(NotificationManager::class.java)
            noti.createNotificationChannel(channel)
        }
    }


    private fun createChannel2() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val import = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("no", "Nguyễn Duy Khương", import)
            channel.setSound(null, null)
            channel.description = "no"
            val noti = getSystemService(NotificationManager::class.java)
            noti.createNotificationChannel(channel)
        }
    }

}