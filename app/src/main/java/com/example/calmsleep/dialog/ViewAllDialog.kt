package com.example.calmsleep.dialog


import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.calmsleep.acivity.LoadingAcivity
import com.example.calmsleep.acivity.MainActivity
import com.example.calmsleep.application.MyApp
import com.example.calmsleep.databinding.DialogViewAllBinding
import com.example.calmsleep.fragment.DialogAdapter
import com.example.calmsleep.model.MusicData
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


//android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen
class ViewAllDialog(val str: String, val data: MutableList<MusicData>) : BottomSheetDialogFragment(), DialogAdapter.IMusicOne {

    lateinit var binding: DialogViewAllBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogViewAllBinding.inflate(inflater, container,false)


        binding.rc.layoutManager = GridLayoutManager(context, 2)
        binding.rc.adapter = DialogAdapter(this,data)
        binding.tvTitle.text = str
        binding.data = MyApp.getMusic()
        return binding.root
    }

    override fun onClick(position: Int) {
            LoadingAcivity.service!!.play(position,data)
    }
}