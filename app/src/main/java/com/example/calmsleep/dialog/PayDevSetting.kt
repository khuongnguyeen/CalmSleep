package com.example.calmsleep.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calmsleep.databinding.SettingPayDeveloperBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PayDevSetting : BottomSheetDialogFragment()  {

    private lateinit var binding: SettingPayDeveloperBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SettingPayDeveloperBinding.inflate(inflater, container,false)



        return binding.root
    }





}