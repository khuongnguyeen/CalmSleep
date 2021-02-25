package com.example.calmsleep.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.calmsleep.adapter.SoundsAdapter
import com.example.calmsleep.databinding.FragmentStoriesBinding
import com.example.calmsleep.ui.adapter.StoriesAdapter

class StoriesFragment : Fragment(){
    private lateinit var binding: FragmentStoriesBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStoriesBinding.inflate(inflater, container,false)

        binding.tabLayout.setupWithViewPager(binding.vp)
        binding.vp.adapter = StoriesAdapter(childFragmentManager)

        return binding.root
    }
}