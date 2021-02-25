package com.example.calmsleep.acivity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.calmsleep.R
import com.example.calmsleep.databinding.LoadingBinding

class LoadingAcivity : AppCompatActivity() {
    private lateinit var binding: LoadingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.loading)
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivities(arrayOf(intent))
        }, 3000)


    }
}