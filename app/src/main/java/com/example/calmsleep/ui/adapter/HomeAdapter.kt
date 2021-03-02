package com.example.calmsleep.ui.adapter

import android.media.MediaMetadataRetriever
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.calmsleep.acivity.LoadingAcivity
import com.example.calmsleep.application.MyApp
import com.example.calmsleep.databinding.ItemMusicBinding
import com.example.calmsleep.model.MusicData

class HomeAdapter(val list: MutableList<MusicData>) : RecyclerView.Adapter<HomeAdapter.HomeHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        return HomeHolder(
            ItemMusicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount() = list.size
    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        holder.binding.data = list[position]
        Log.e("ok","------------- $position")

    }


    class HomeHolder(val binding: ItemMusicBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                Log.e("anhkhuongkka","--------------------- ${binding.data!!.id}")
//                Log.e("anhkhuongkka","--------------------- duration :: ${binding.data!!.duration}")
              LoadingAcivity.service!!.play(binding.data!!.id)
//                MyApp.POSITION = binding.data!!.id
            }
        }
    }
}