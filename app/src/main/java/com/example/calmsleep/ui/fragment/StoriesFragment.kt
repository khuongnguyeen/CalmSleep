package com.example.calmsleep.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.calmsleep.acivity.MainActivity
import com.example.calmsleep.application.MyApp
import com.example.calmsleep.databinding.FragmentStoriesBinding
import com.example.calmsleep.fragment.FavouritesFragment
import com.example.calmsleep.fragment.ViewAllFragment
import com.example.calmsleep.model.MusicData
import com.example.calmsleep.ui.adapter.StoriesAdapter

class StoriesFragment : Fragment() {
    private lateinit var binding: FragmentStoriesBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStoriesBinding.inflate(inflater, container, false)

        binding.vp.adapter = StoriesAdapter(childFragmentManager)
        binding.tabLayout.setViewPager(binding.vp, 0)

        ViewAllFragment.data.clear()
        ViewAllFragment.data.addAll(MyApp.getDB().getMusicAlbumId(2))
        FavouritesFragment.data.clear()
        val data = mutableListOf<MusicData>()
        for (i in MyApp.getMD()){
            for (j in MyApp.getFavourites()){
                if ( i.id == j.musicId){
                    if (i.albumid == 2){
                        data.add(i)
                    }
                }
            }

        }
        FavouritesFragment.data.addAll(data)

        binding.ivPicks.setOnClickListener {
            (activity as MainActivity).callDialogChill("Experts Picks",MyApp.getDB().getMusicAlbumId(2))
        }
        binding.ivFemale.setOnClickListener {
            (activity as MainActivity).callDialogChill("Females narration",MyApp.getDB().getMusicAlbumId(1))
        }
        binding.ivMale.setOnClickListener {
            (activity as MainActivity).callDialogChill("Males narration",MyApp.getDB().getMusicAlbumId(4))
        }
        binding.ivKids.setOnClickListener {
            (activity as MainActivity).callDialogChill("Kids",MyApp.getDB().getMusicAlbumId(3))
        }

        return binding.root
    }
}