package com.example.calmsleep.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.calmsleep.databinding.FragmentRecentlyBinding

class RecentlyFragment: Fragment(){
    private lateinit var binding: FragmentRecentlyBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecentlyBinding.inflate(inflater, container,false)






        return binding.root
    }
}