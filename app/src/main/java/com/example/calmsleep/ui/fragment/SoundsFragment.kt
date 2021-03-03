package com.example.calmsleep.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.calmsleep.acivity.LoadingAcivity
import com.example.calmsleep.acivity.MainActivity
import com.example.calmsleep.adapter.SoundsAdapter
import com.example.calmsleep.application.MyApp
import com.example.calmsleep.databinding.FragmentSoundsBinding
import com.example.calmsleep.fragment.FavouritesFragment
import com.example.calmsleep.fragment.ViewAllFragment
import com.example.calmsleep.model.MusicData

class SoundsFragment : Fragment() {
    private lateinit var binding: FragmentSoundsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSoundsBinding.inflate(inflater, container, false)
        binding.vp.adapter = SoundsAdapter(childFragmentManager)
        binding.tabLayout.setViewPager(binding.vp,0)
        ViewAllFragment.data.clear()
        ViewAllFragment.data.addAll(MyApp.getDB().getMusicAlbumId(1))
        FavouritesFragment.data.clear()
        val data = mutableListOf<MusicData>()
        for (i in MyApp.getMD()){
            for (j in MyApp.getFavourites()){
                if ( i.id == j.musicId){
                    if (i.albumid == 1){
                        data.add(i)
                    }
                }
            }

        }
        FavouritesFragment.data.addAll(data)
        binding.ivPicks.setOnClickListener {
            (activity as MainActivity).callDialogChill("Experts Picks",MyApp.getDB().getMusicCategoryId(4))
        }
        binding.ivAmbient.setOnClickListener {
            (activity as MainActivity).callDialogChill("Experts Picks",MyApp.getDB().getMusicCategoryId(1))
        }
        binding.ivNature.setOnClickListener {
            (activity as MainActivity).callDialogChill("Experts Picks",MyApp.getDB().getMusicCategoryId(2))
        }
        binding.ivKid.setOnClickListener {
            (activity as MainActivity).callDialogChill("Experts Picks",MyApp.getDB().getMusicCategoryId(3))
        }


        return binding.root
    }


}