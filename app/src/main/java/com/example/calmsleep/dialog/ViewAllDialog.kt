package com.example.calmsleep.dialog


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.calmsleep.acivity.LoadingAcivity
import com.example.calmsleep.application.MyApp
import com.example.calmsleep.databinding.DialogViewAllBinding
import com.example.calmsleep.fragment.DialogAdapter
import com.example.calmsleep.model.MusicData
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


//android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen
class ViewAllDialog(private val str: String, val data: MutableList<MusicData>) : BottomSheetDialogFragment() {

    lateinit var binding: DialogViewAllBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogViewAllBinding.inflate(inflater, container,false)
        binding.rc.layoutManager = GridLayoutManager(context, 2)
        binding.rc.adapter = DialogAdapter(data)
        binding.tvTitle.text = str
        return binding.root
    }


}