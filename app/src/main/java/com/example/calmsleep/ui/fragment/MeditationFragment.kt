package com.example.calmsleep.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.calmsleep.adapter.SoundsAdapter
import com.example.calmsleep.application.MyApp
import com.example.calmsleep.databinding.FragmentMeditationBinding
import com.example.calmsleep.fragment.ViewAllFragment
import com.example.calmsleep.ui.adapter.MeditationAdapter

class MeditationFragment : Fragment(){
    private lateinit var binding: FragmentMeditationBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMeditationBinding.inflate(inflater, container,false)

        binding.tabLayout.setupWithViewPager(binding.vp)
        binding.vp.adapter = MeditationAdapter(childFragmentManager)
        ViewAllFragment.data.clear()
        ViewAllFragment.data.addAll(MyApp.getDB().getMusicAlbumId(2))
        return binding.root
    }
}