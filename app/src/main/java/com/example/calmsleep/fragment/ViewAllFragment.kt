package com.example.calmsleep.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.calmsleep.databinding.FragmentViewAllBinding
import com.example.calmsleep.model.MusicData
import com.example.calmsleep.ui.adapter.HomeAdapter

class ViewAllFragment(val str: String, val data: MutableList<MusicData>) : Fragment(), HomeAdapter.IMusicOne {

    private lateinit var binding: FragmentViewAllBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewAllBinding.inflate(inflater, container, false)
        binding.rc.layoutManager = GridLayoutManager(context, 3)
        binding.rc.adapter = HomeAdapter(this)
        binding.tvTitle.text = str
        return binding.root
    }

    override fun getCount(): Int {
        return data.size
    }

    override fun getData(position: Int): MusicData {
       return data[position]
    }

    override fun onClick(position: Int) {

    }
}