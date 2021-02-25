package com.example.calmsleep.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.calmsleep.databinding.ItemMusicBinding
import com.example.calmsleep.model.MusicData

class HomeAdapter(private val inter: IMusicOne) : RecyclerView.Adapter<HomeAdapter.HomeHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        return HomeHolder(
            ItemMusicBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            inter
        )
    }

    override fun getItemCount() = inter.getCount()
    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        holder.binding.data = inter.getData(position)
    }

    interface IMusicOne {
        fun getCount(): Int
        fun getData(position: Int): MusicData
        fun onClick(position: Int)
    }

    class HomeHolder(val binding: ItemMusicBinding, inter: IMusicOne) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                inter.onClick(adapterPosition)
            }
        }
    }
}