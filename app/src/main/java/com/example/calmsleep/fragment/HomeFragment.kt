package com.example.calmsleep.fragment

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.calmsleep.adapter.HomeAdapter
import com.example.calmsleep.application.MyApp
import com.example.calmsleep.databinding.FragmentHomeBinding
import com.example.calmsleep.service.MusicService

class HomeFragment : Fragment(), HomeAdapter.IMusic {

    private lateinit var binding: FragmentHomeBinding
    private var service: MusicService? = null
    private var conn: ServiceConnection? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)


        openServiceUnBound()


        register()
        binding.rc.layoutManager = GridLayoutManager(context, 2)
        binding.rc.adapter = HomeAdapter(this)

        binding.data = MyApp.getMusic()

        return binding.root
    }

    private fun register() {
        MyApp.getMusic().musicData.observe(this, Observer {
            binding.rc.adapter!!.notifyDataSetChanged()
        })


    }

    private fun createConnectService() {
        conn = object : ServiceConnection {
            override fun onServiceDisconnected(name: ComponentName?) {

            }

            override fun onServiceConnected(name: ComponentName?, binder: IBinder) {
                val myBinder = binder as MusicService.MyBinder
                service = myBinder.service
                if (service!!.getMusicData().size == 0) {
                    MyApp.getMusic().searchSong(null)
                } else {
                    binding.rc.adapter!!.notifyDataSetChanged()
                }

            }
        }
        val intent = Intent()
        intent.setClass(context!!, MusicService::class.java)
        context!!.bindService(intent, conn!!, Context.BIND_AUTO_CREATE)
    }

    private fun openServiceUnBound() {
        val intent = Intent(context, MusicService::class.java)
        context!!.startService(intent)
        createConnectService()

    }

    override fun onDestroyView() {
        context!!.unbindService(conn!!)
        super.onDestroyView()
    }

    override fun onItemClick(position: Int) {
        Log.e("khuongok", "---------------------------------$position")
    }

    override fun getCount(): Int {
        if ( service == null){
            return 0
        }
        return service!!.getMusicData().size
    }

    override fun getData(position: Int) =  service!!.getMusicData()[position]
}