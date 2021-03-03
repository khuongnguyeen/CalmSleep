package com.example.calmsleep.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calmsleep.acivity.MainActivity
import com.example.calmsleep.adapter.VerticalHomeAdapter
import com.example.calmsleep.application.MyApp
import com.example.calmsleep.databinding.FragmentHomeBinding
import com.example.calmsleep.model.MusicData

class HomeFragment : Fragment(), VerticalHomeAdapter.IMusic {

        companion object{
        val TAG = HomeFragment::class.java.name
    }
    private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.rc.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rc.adapter = VerticalHomeAdapter(context!!, this)
        for (i in MyApp.getDB().getMusic()) {
            Log.e("okkkkkkkk", "${i.albumid},${i.categoryId}")
        }
        return binding.root
    }

    override fun getCount() = 5

    override fun getData(position: Int): MusicData {
        return MyApp.getDB().getMusic()[position]
    }

    override fun onClick(position: Int) {
        (activity as MainActivity).callDialog(position+1)
    }
}