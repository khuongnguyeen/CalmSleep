package com.example.calmsleep.acivity

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.calmsleep.R
import com.example.calmsleep.application.MyApp
import com.example.calmsleep.databinding.ActivityMainBinding
import com.example.calmsleep.dialog.ViewAllDialog
import com.example.calmsleep.model.MusicData
import com.example.calmsleep.ui.fragment.*
import me.majiajie.pagerbottomtabstrip.MaterialMode
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        sttBar()
        addHomeFragment()
        val a = binding.bar.material()
            .addItem(R.drawable.sleep, "Home", R.color.khuong)
            .addItem(R.drawable.music, "Sounds", R.color.khuong_1)
            .addItem(R.drawable.book, "Stories", R.color.khuong_2)
            .addItem(R.drawable.meditation, "Meditation", R.color.khuong_3)
            .addItem(R.drawable.alarm, "Alarm", R.color.khuong_4)
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
        binding.ivBack.setImageResource(R.drawable.bg_4)
        val manager = supportFragmentManager
        val tran = manager.beginTransaction()
        val fr = HomeFragment()
        tran
            .replace(R.id.rc, fr)
            .commit()
    }

    fun callDialog(position: Int) {
        val v = ViewAllDialog(
            MyApp.getDB().getAlbumId(position),
            MyApp.getDB().getMusicAlbumId(position)
        )
        v.show(supportFragmentManager, v.tag)
    }

    fun callDialogChill(str: String, data: MutableList<MusicData>) {
        val v = ViewAllDialog(str, data)
        v.show(supportFragmentManager, v.tag)
    }

    override fun onBackPressed() {}

    private fun addSoundsFragment() {
        binding.ivBack.setImageResource(R.drawable.bg_2)
        val manager = supportFragmentManager
        val tran = manager.beginTransaction()
        val fr = SoundsFragment()
        tran
            .replace(R.id.rc, fr)
            .commit()
    }

    private fun addStoriesFragment() {

        binding.ivBack.setImageResource(R.drawable.bg_3)
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

        binding.ivBack.setImageResource(R.drawable.bg_6)
        val manager = supportFragmentManager
        val tran = manager.beginTransaction()
        val fr = AlarmFragment()
        tran
            .replace(R.id.rc, fr)
            .commit()
    }

}