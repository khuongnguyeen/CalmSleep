package com.example.calmsleep.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calmsleep.databinding.SettingLanguageBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class LanguageSetting : BottomSheetDialogFragment()  {

    private lateinit var binding: SettingLanguageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SettingLanguageBinding.inflate(inflater, container,false)



        return binding.root
    }





}