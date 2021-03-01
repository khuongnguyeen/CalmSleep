package com.example.calmsleep.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calmsleep.databinding.PlayerMusicBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MusicPlayer : BottomSheetDialogFragment() {

    lateinit var binding: PlayerMusicBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PlayerMusicBinding.inflate(inflater, container,false)


        return binding.root
    }


}