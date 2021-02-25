package com.example.calmsleep.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.calmsleep.adapter.SoundsAdapter
import com.example.calmsleep.databinding.FragmentSoundsBinding

class SoundsFragment : Fragment(){
    private lateinit var binding: FragmentSoundsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSoundsBinding.inflate(inflater, container,false)
        binding.tabLayout.setupWithViewPager(binding.vp)
        binding.vp.adapter = SoundsAdapter(childFragmentManager)


        return binding.root
    }
}