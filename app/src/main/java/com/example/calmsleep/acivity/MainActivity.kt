package com.example.calmsleep.acivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.calmsleep.R
import com.example.calmsleep.databinding.ActivityMainBinding
import com.example.calmsleep.fragment.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.bottomNavigation.selectedItemId = R.id.action_home
        addHomeFragment()
        binding.bottomNavigation.setOnNavigationItemSelectedListener {
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
    fun addHomeFragment() {
        binding.rlOk.setBackgroundResource(R.drawable.bg_4)
        val manager = supportFragmentManager
        val tran = manager.beginTransaction()
        val fr = HomeFragment()
        tran
            .replace(R.id.content, fr)
            .commit()
    }

    override fun onBackPressed() {

    }

    fun addSoundsFragment() {
        binding.rlOk.setBackgroundResource(R.drawable.bg_2)
        val manager = supportFragmentManager
        val tran = manager.beginTransaction()
        val fr = SoundsFragment()
        tran
            .replace(R.id.content, fr)
            .commit()
    }

    fun addStoriesFragment() {

        binding.rlOk.setBackgroundResource(R.drawable.bg_3)
        val manager = supportFragmentManager
        val tran = manager.beginTransaction()
        val fr = StoriesFragment()
        tran
            .replace(R.id.content, fr)
            .commit()
    }

    fun addMeditationFragment() {

        binding.rlOk.setBackgroundResource(R.drawable.bg_5)
        val manager = supportFragmentManager
        val tran = manager.beginTransaction()
        val fr = MeditationFragment()
        tran
            .replace(R.id.content, fr)
            .commit()
    }

    fun addAlarmFragment() {

        binding.rlOk.setBackgroundResource(R.drawable.bg_6)
        val manager = supportFragmentManager
        val tran = manager.beginTransaction()
        val fr = AlarmFragment()
        tran
            .replace(R.id.content, fr)
            .commit()
    }

}