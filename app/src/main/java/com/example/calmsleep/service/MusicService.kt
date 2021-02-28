package com.example.calmsleep.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.example.calmsleep.R
import com.example.calmsleep.application.MyApp
import com.example.calmsleep.manager.MusicOnlineManager
import com.example.calmsleep.model.MusicData

@Suppress("DEPRECATION")
class MusicService : LifecycleService() {
    private val play = MusicOnlineManager()
    override fun onCreate() {
        super.onCreate()
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        if (intent != null) {
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
        play.setPath( MyApp.getDB().getMusic(id).linkMusic!!)

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

        if ( MyApp.getDB().getMusic(id).linkImage != null) {
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



}