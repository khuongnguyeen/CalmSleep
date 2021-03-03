package com.example.calmsleep.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calmsleep.databinding.SettingNotifitionPopupBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NotificationPopupSetting : BottomSheetDialogFragment()  {

    private lateinit var binding: SettingNotifitionPopupBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SettingNotifitionPopupBinding.inflate(inflater, container,false)


        binding.ivClose.setOnClickListener {
            dismiss()
        }

        return binding.root
    }





}