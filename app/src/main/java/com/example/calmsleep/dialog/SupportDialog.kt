package com.example.calmsleep.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calmsleep.databinding.SupportBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SupportDialog : BottomSheetDialogFragment()  {

    private lateinit var binding: SupportBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SupportBinding.inflate(inflater, container,false)

        return binding.root
    }





}