package com.example.calmsleep.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calmsleep.R
import com.example.calmsleep.acivity.LoadingAcivity
import com.example.calmsleep.acivity.MainActivity
import com.example.calmsleep.adapter.VerticalHomeAdapter
import com.example.calmsleep.application.MyApp
import com.example.calmsleep.databinding.FragmentHomeBinding
import com.example.calmsleep.fragment.ViewAllFragment
import com.example.calmsleep.model.MusicData
import com.example.calmsleep.model.VerticalModel

class HomeFragment : Fragment(), VerticalHomeAdapter.IMusic {

//    companion object{
//        val TAG = HomeFragment::class.java.name
//    }
    private lateinit var binding: FragmentHomeBinding
    val musicDataVertical = mutableListOf<VerticalModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        register()
        binding.rc.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        binding.rc.adapter = context?.let { VerticalHomeAdapter(it,this) }
        binding.rc.setHasFixedSize(true)
        binding.rc.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        binding.data = MyApp.getMusic()
        return binding.root
    }

    private fun register() {
        MyApp.getMusic().musicData.observe(this, Observer {
            binding.rc.adapter!!.notifyDataSetChanged()
            getData()
        })


    }

    private fun getData(){
        for (i in 0..5){
            val verticalModel = VerticalModel("Title $i",LoadingAcivity.service!!.getMusicData())
            musicDataVertical.add(verticalModel)


        }
    }

    fun addHomeFragment(str: String,data: MutableList<MusicData>) {
        val manager = childFragmentManager
        val tran = manager.beginTransaction()
        val fr = ViewAllFragment(str,data)
        tran
            .replace(R.id.rc, fr)
            .addToBackStack(null)
            .commit()
    }

    override fun getCount(): Int {
        return musicDataVertical.size
    }

    override fun getData(position: Int): VerticalModel {
        return musicDataVertical[position]
    }

    override fun onClick(position: Int) {
//        addHomeFragment(musicDataVertical[position].text,musicDataVertical[position].list)
        (activity as com.example.calmsleep.acivity.MainActivity).addViewFragment(musicDataVertical[position].text,musicDataVertical[position].list)
        Toast.makeText(context,"kkkkkkkkkkko",Toast.LENGTH_SHORT).show()
    }
}