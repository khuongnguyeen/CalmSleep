package com.example.calmsleep.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calmsleep.databinding.SetAlarmBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SetAlarmPop : BottomSheetDialogFragment()  {

    private lateinit var binding: SetAlarmBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SetAlarmBinding.inflate(inflater, container,false)

        return binding.root
    }





}