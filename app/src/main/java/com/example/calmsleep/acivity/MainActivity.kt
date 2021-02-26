package com.example.calmsleep.acivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.calmsleep.R
import com.example.calmsleep.application.MyApp
import com.example.calmsleep.databinding.ActivityMainBinding
import com.example.calmsleep.dialog.ViewAllDialog
import com.example.calmsleep.fragment.ViewAllFragment
import com.example.calmsleep.model.MusicData
import com.example.calmsleep.ui.fragment.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.bar.selectedItemId = R.id.action_home
        addHomeFragment()
        binding.bar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_home -> {
                    addHomeFragment()
                }
                R.id.action_sounds -> {
                    addSoundsFragment()
                }
                R.id.action_stories -> {
                    addStoriesFragment()
                }
                R.id.action_meditation -> {
                    addMeditationFragment()
                }
                R.id.action_alarm -> {
                    addAlarmFragment()
                }
            }
            true
        }
    }

    private fun addHomeFragment() {
        binding.rlOk.setBackgroundResource(R.drawable.bg_4)
        val manager = supportFragmentManager
        val tran = manager.beginTransaction()
        val fr = HomeFragment()
        tran
            .replace(R.id.rc, fr)
            .commit()
    }

    fun callDialog(position:Int){
        val v = ViewAllDialog(MyApp.musicDataVertical[position].text, MyApp.musicDataVertical[position].list)
        v.show(supportFragmentManager,v.tag)
    }

    fun callDialogChill(str: String,data:MutableList<MusicData>){
        val v = ViewAllDialog(str, data)
        v.show(supportFragmentManager,v.tag)
    }

    override fun onBackPressed() {}

    private fun addSoundsFragment() {
        binding.rlOk.setBackgroundResource(R.drawable.bg_2)
        val manager = supportFragmentManager
        val tran = manager.beginTransaction()
        val fr = SoundsFragment()
        tran
            .replace(R.id.rc, fr)
            .commit()
    }

    private fun addStoriesFragment() {

        binding.rlOk.setBackgroundResource(R.drawable.bg_3)
        val manager = supportFragmentManager
        val tran = manager.beginTransaction()
        val fr = StoriesFragment()
        tran
            .replace(R.id.rc, fr)
            .commit()
    }

    private fun addMeditationFragment() {

        binding.rlOk.setBackgroundResource(R.drawable.bg_5)
        val manager = supportFragmentManager
        val tran = manager.beginTransaction()
        val fr = MeditationFragment()
        tran
            .replace(R.id.rc, fr)
            .commit()
    }

    private fun addAlarmFragment() {

        binding.rlOk.setBackgroundResource(R.drawable.bg_6)
        val manager = supportFragmentManager
        val tran = manager.beginTransaction()
        val fr = AlarmFragment()
        tran
            .replace(R.id.rc, fr)
            .commit()
    }

}