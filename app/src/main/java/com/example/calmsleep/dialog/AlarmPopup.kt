package com.example.calmsleep.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.core.app.ServiceCompat.stopForeground
import com.example.calmsleep.acivity.LoadingAcivity
import com.example.calmsleep.databinding.PopupBinding
import com.example.calmsleep.service.MusicService

class AlarmPopup (context: Context) : Dialog(context) {

    private lateinit var binding: PopupBinding


    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = PopupBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnOff.setOnClickListener {
            dismiss()
            stopForeground(LoadingAcivity.service!!,Service.STOP_FOREGROUND_REMOVE)
        }

    }


    override fun onBackPressed() {
        super.onBackPressed()
        dismiss()
        val intent = Intent(context, MusicService::class.java)
        context.stopService(intent)
    }


}