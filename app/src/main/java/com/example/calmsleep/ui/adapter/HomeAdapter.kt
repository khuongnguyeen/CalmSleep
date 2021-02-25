package com.example.calmsleep.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.calmsleep.databinding.ItemMusicBinding
import com.example.calmsleep.model.MusicData

class HomeAdapter(private val inter: IMusicOne,val list: MutableList<MusicData>) : RecyclerView.Adapter<HomeAdapter.HomeHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        return HomeHolder(
            ItemMusicBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            inter
        )
    }

    override fun getItemCount() = list.size
    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        holder.binding.data = list[position]
    }

    interface IMusicOne {
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