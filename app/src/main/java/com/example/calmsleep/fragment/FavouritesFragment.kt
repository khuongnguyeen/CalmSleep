package com.example.calmsleep.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.calmsleep.adapter.ChilAdapter
import com.example.calmsleep.databinding.FragmentFavouritesBinding
import com.example.calmsleep.model.MusicData

class FavouritesFragment : Fragment(),ChilAdapter.IMusicOne {
    companion object{
        val data: MutableList<MusicData> = mutableListOf()
    }
    private lateinit var binding: FragmentFavouritesBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavouritesBinding.inflate(inflater, container,false)
        binding.rc.layoutManager = GridLayoutManager(context, 2)
        binding.rc.adapter = ChilAdapter(data)
        return binding.root
    }

    override fun getDataOk(): MutableList<MusicData> {
        return data
    }
}