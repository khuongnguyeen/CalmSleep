package com.example.calmsleep.fragment

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import androidx.recyclerview.widget.GridLayoutManager
import com.example.calmsleep.databinding.FragmentViewAllBinding
import com.example.calmsleep.model.MusicData
import com.example.calmsleep.ui.adapter.HomeAdapter
import java.util.*

class ViewAllFragment(val str: String, val data: MutableList<MusicData>,context: Context) : Dialog(context), HomeAdapter.IMusicOne {

    private lateinit var binding: FragmentViewAllBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = FragmentViewAllBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rc.layoutManager = GridLayoutManager(context, 3)
        binding.rc.adapter = HomeAdapter(this,data)
        binding.tvTitle.text = str

    }




    override fun onClick(position: Int) {

    }
}