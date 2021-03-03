package com.example.calmsleep.acivity

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.calmsleep.R
import com.example.calmsleep.application.MyApp
import com.example.calmsleep.databinding.ActivityMainBinding
import com.example.calmsleep.dialog.SetAlarmPop
import com.example.calmsleep.dialog.SupportDialog
import com.example.calmsleep.dialog.ViewAllDialog
import com.example.calmsleep.model.MusicData
import com.example.calmsleep.ui.fragment.*
import me.majiajie.pagerbottomtabstrip.MaterialMode
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener
import org.jsoup.Jsoup
import kotlin.system.exitProcess


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var check = false

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        sttBar()
        addHomeFragment()
        val a = binding.bar.material()
            .addItem(R.drawable.sleep, "Home")
            .addItem(R.drawable.music, "Sounds")
            .addItem(R.drawable.book, "Stories")
            .addItem(R.drawable.meditation, "Meditation")
            .addItem(R.drawable.alarm, "Alarm")
            .setDefaultColor(-0x76000001)
            .setMode(MaterialMode.CHANGE_BACKGROUND_COLOR)
            .build()
        a.setSelect(0)
        a.addTabItemSelectedListener(object : OnTabItemSelectedListener {
            override fun onSelected(index: Int, old: Int) {
                when (index) {
                    0 -> {
                        addHomeFragment()
                    }
                    1 -> {
                        addSoundsFragment()
                    }
                    2 -> {
                        addStoriesFragment()
                    }
                    3 -> {
                        addMeditationFragment()
                    }
                    else -> {
                        addAlarmFragment()
                    }
                }
            }

            override fun onRepeat(index: Int) {
                Log.e("asd", "onRepeat selected: $index")
            }
        })
        sync()

        val b = SupportDialog()
        binding.buttonSup.setOnClickListener {
            b.show(supportFragmentManager, b.tag)
        }

        binding.playerView.playPauseButton.setOnClickListener {
            LoadingAcivity.service!!.playPause(MyApp.POSITION)
        }
        binding.playerView.ivIconPlay.setOnClickListener {
            LoadingAcivity.service!!.playPause(MyApp.POSITION)
        }
        binding.playerView.queueButton.setOnClickListener {
            binding.rlGone.visibility = View.GONE
        }

        binding.playerView.ivIconLoop.setOnClickListener {
            LoadingAcivity.service!!.play.setLooping(true)
            Toast.makeText(applicationContext,"isLooping = true",Toast.LENGTH_SHORT).show()
        }

        binding.playerView.ivIconAlarm.setOnClickListener {
            val v = SetAlarmPop()
            v.show(supportFragmentManager,v.tag)
        }

        binding.playerView.ivIconDownload.setOnClickListener {
            getLinkMusicAsyn(MyApp.getMD()[MyApp.POSITION].linkSong)
            Toast.makeText(applicationContext,"Lưu thành công",Toast.LENGTH_SHORT).show()
        }

        binding.playerView.ivIconFavourites.setOnClickListener {
            val k = MyApp.POSITION -1
            check = false
            for (i in MyApp.getFavourites()) {
                if (i.musicId == k) {
                    check = true
                }
            }

            if (check) {
                binding.playerView.ivIconFavourites.setImageResource(R.drawable.heart)
                MyApp.getDB().deleteFavourite(k)
                Toast.makeText(applicationContext, "Favourites delete done", Toast.LENGTH_SHORT)
                    .show()
                MyApp.getFavourites().clear()
                MyApp.getFavourites().addAll(MyApp.getDB().getFavourites())
                Log.e("okKO", "${MyApp.getFavourites()}")
                check = false
            } else {
                binding.playerView.ivIconFavourites.setImageResource(R.drawable.heart_full)
                MyApp.getDB().insertFavourites(
                    MyApp.getMD()[k].id,
                    MyApp.getMD()[k].albumid,
                    MyApp.getMD()[k].categoryId
                )
                Toast.makeText(applicationContext, "Favourites Done", Toast.LENGTH_SHORT).show()
                Log.e("okKO", "${MyApp.getFavourites()}")
                check = true
            }
        }
    }

    private fun getLinkMusicAsyn(linkHtml: String) {
        val asyn = @SuppressLint("StaticFieldLeak")
        object : AsyncTask<Void, Void, String?>() {
            override fun doInBackground(vararg params: Void?): String? {
                val link = getLinkMusic(linkHtml = linkHtml)
                MyApp.getMD()[MyApp.POSITION-1].linkMusic = link
                MyApp.getMusicViewModel().saveInToDatabase(
                        MyApp.getMD()[MyApp.POSITION-1], context = applicationContext
                    )
                var check = false
                for(i in MyApp.getMusicDownLoad()){
                    check = false
                    if (MyApp.POSITION == i.id){
                        check = true
                        break
                    }
                }
                if (check==false){
                    MyApp.getMusicDownLoad().add(MyApp.getMD()[MyApp.POSITION-1])
                }
                return link
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

    fun updateData() {
        for (a in MyApp.getMD()) {
            if (a.id == MyApp.POSITION) {
                binding.playerView.playingSong.text = a.songName
                binding.playerView.playingArtist.text = a.artistName
                binding.playerView.tvSongName.text = a.songName
                binding.playerView.tvArtist.text = a.artistName
                Glide.with(applicationContext)
                    .load(a.linkImage)
                    .into(binding.playerView.ivMusicImg)
            }
        }
    }

    fun sync() {
        val async = @SuppressLint("StaticFieldLeak")
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg params: Void?): Void? {
                for (i in 1..2) {
                    SystemClock.sleep(500)
                }
                return null
            }

            override fun onPostExecute(result: Void?) {
                if (MyApp.ISPLAYING) {
                    binding.rlGone.visibility = View.VISIBLE
                    binding.playerView.queueButton.visibility = View.GONE
                    binding.playerView.playPauseButton.setImageResource(R.drawable.pause)
                    binding.playerView.ivIconPlay.setImageResource(R.drawable.pause_beau)
                    updateData()
                } else {
                    binding.playerView.queueButton.visibility = View.VISIBLE
                    binding.playerView.playPauseButton.setImageResource(R.drawable.play)
                    binding.playerView.ivIconPlay.setImageResource(R.drawable.play_beau)
                }

                sync()
            }
        }
        async.execute()
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

    private fun addHomeFragment() {
        binding.ivBack.setImageResource(R.drawable.bg_8)
        val manager = supportFragmentManager
        val tran = manager.beginTransaction()
        val fr = HomeFragment()
        tran
            .replace(R.id.rc, fr)
            .commit()
    }


    fun callDialog(position: Int) {

        val v = ViewAllDialog(
            MyApp.getDB().getAlbumId(position + 1),
            MyApp.getDB().getMusicAlbumId(position + 1)
        )
        v.show(supportFragmentManager, v.tag)
    }

    fun callDialogChill(str: String, data: MutableList<MusicData>) {
        val v = ViewAllDialog(str, data)
        v.show(supportFragmentManager, v.tag)
    }


    override fun onBackPressed() {
        val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle(R.string.do_you_really_want_to_exit)
        alertDialogBuilder
            .setCancelable(true)
            .setPositiveButton("NO") { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("YES") { _, _ ->
                finishAffinity()
                exitProcess(0)
            }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun addSoundsFragment() {
        binding.ivBack.setImageResource(R.drawable.bg_7)
        val manager = supportFragmentManager
        val tran = manager.beginTransaction()
        val fr = SoundsFragment()
        tran
            .replace(R.id.rc, fr)
            .commit()
    }

    private fun addStoriesFragment() {

        binding.ivBack.setImageResource(R.drawable.bg_9)
        val manager = supportFragmentManager
        val tran = manager.beginTransaction()
        val fr = StoriesFragment()
        tran
            .replace(R.id.rc, fr)
            .commit()
    }

    private fun addMeditationFragment() {

        binding.ivBack.setImageResource(R.drawable.bg_5)
        val manager = supportFragmentManager
        val tran = manager.beginTransaction()
        val fr = MeditationFragment()
        tran
            .replace(R.id.rc, fr)
            .commit()
    }

    private fun addAlarmFragment() {

        binding.ivBack.setImageResource(R.color.khuong_3)
        val manager = supportFragmentManager
        val tran = manager.beginTransaction()
        val fr = AlarmFragment()
        tran
            .replace(R.id.rc, fr)
            .commit()
    }

}