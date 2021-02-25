package com.example.calmsleep.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.calmsleep.databinding.FragmentDownloadBinding

class DownloadFragment : Fragment(){
    private lateinit var binding: FragmentDownloadBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDownloadBinding.inflate(inflater, container,false)
        return binding.root
    }
}