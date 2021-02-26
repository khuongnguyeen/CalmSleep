package com.example.calmsleep.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calmsleep.R
import com.example.calmsleep.adapter.VerticalHomeAdapter
import com.example.calmsleep.application.MyApp
import com.example.calmsleep.databinding.FragmentHomeBinding
import com.example.calmsleep.dialog.ViewAllDialog
import com.example.calmsleep.fragment.ViewAllFragment
import com.example.calmsleep.model.VerticalModel

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
        register()
        binding.rc.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rc.adapter = VerticalHomeAdapter(context!!, this)
        binding.data = MyApp.getMusic()
        for (i in MyApp.musicDataVertical) {
            Log.e("okkkkkkkk", "${i.text},${i.list}")
        }
        return binding.root
    }

    private fun register() {
        MyApp.getMusic().musicData.observe(this, Observer {

            binding.rc.adapter!!.notifyDataSetChanged()
        })


    }


//
//    fun addHomeFragment(str: String,data: MutableList<MusicData>) {
//        val manager = childFragmentManager
//        val tran = manager.beginTransaction()
//        val fr = ViewAllFragment(str,data)
//        tran
//            .replace(R.id.rc, fr)
//            .addToBackStack(null)
//            .commit()
//    }

    override fun getCount(): Int {
        return MyApp.musicDataVertical.size
    }

    override fun getData(position: Int): VerticalModel {
        return MyApp.musicDataVertical[position]
    }

    override fun onClick(position: Int) {
        (activity as com.example.calmsleep.acivity.MainActivity).callDialog(position)
//                Toast.makeText(context,"$position",Toast.LENGTH_SHORT).show()



    }
}