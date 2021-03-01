package com.example.calmsleep.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calmsleep.application.MyApp
import com.example.calmsleep.databinding.PlayerMusicBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MusicPlayer(id: Int) : BottomSheetDialogFragment() {

    lateinit var binding: PlayerMusicBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PlayerMusicBinding.inflate(inflater, container, false)
        binding.data = MyApp.getDB().getMusic(id)
        binding.ivIconPlay.setOnClickListener { }
        binding.ivIconFavourites.setOnClickListener { }
        binding.ivIconAlarm.setOnClickListener { }
        binding.ivIconLoop.setOnClickListener { }
        binding.ivIconSpeak.setOnClickListener { }


        return binding.root
    }


}