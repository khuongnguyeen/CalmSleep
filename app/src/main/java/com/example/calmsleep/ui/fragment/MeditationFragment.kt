package com.example.calmsleep.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.calmsleep.acivity.MainActivity
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
        binding.vp.adapter = MeditationAdapter(childFragmentManager)
        binding.tabLayout.setViewPager(binding.vp,0)
        ViewAllFragment.data.clear()
        ViewAllFragment.data.addAll(MyApp.getDB().getMusicAlbumId(2))
        binding.ivPicks.setOnClickListener {
            (activity as MainActivity).callDialogChill("Experts Picks",MyApp.getDB().getMusicCategoryId(2))
        }
        binding.ivFemale.setOnClickListener {
            (activity as MainActivity).callDialogChill("Females narration",MyApp.getDB().getMusicCategoryId(3))
        }
        binding.ivMale.setOnClickListener {
            (activity as MainActivity).callDialogChill("Males narration",MyApp.getDB().getMusicCategoryId(4))
        }
        return binding.root
    }
}