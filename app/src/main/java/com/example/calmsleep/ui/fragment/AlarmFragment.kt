package com.example.calmsleep.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.calmsleep.databinding.FragmentAlarmBinding

class AlarmFragment : Fragment(){
    private lateinit var binding: FragmentAlarmBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlarmBinding.inflate(inflater, container,false)



        return binding.root
    }
}