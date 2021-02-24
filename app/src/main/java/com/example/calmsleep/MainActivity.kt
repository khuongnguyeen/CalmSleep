package com.example.calmsleep

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
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
        val manager = supportFragmentManager
        val tran = manager.beginTransaction()
        val fr = HomeFragment()
        tran
            .replace(R.id.content, fr)
            .commit()
    }

    fun addSoundsFragment() {
        val manager = supportFragmentManager
        val tran = manager.beginTransaction()
        val fr = SoundsFragment()
        tran
            .replace(R.id.content, fr)
            .commit()
    }

    fun addStoriesFragment() {
        val manager = supportFragmentManager
        val tran = manager.beginTransaction()
        val fr = StoriesFragment()
        tran
            .replace(R.id.content, fr)
            .commit()
    }

    fun addMeditationFragment() {
        val manager = supportFragmentManager
        val tran = manager.beginTransaction()
        val fr = MeditationFragment()
        tran
            .replace(R.id.content, fr)
            .commit()
    }

    fun addAlarmFragment() {
        val manager = supportFragmentManager
        val tran = manager.beginTransaction()
        val fr = AlarmFragment()
        tran
            .replace(R.id.content, fr)
            .commit()
    }

}