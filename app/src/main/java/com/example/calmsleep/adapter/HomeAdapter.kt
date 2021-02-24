package com.example.calmsleep.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.calmsleep.databinding.ItemMusicBinding
import com.example.calmsleep.model.MusicData

class HomeAdapter(private val inter: IMusic) : RecyclerView.Adapter<HomeAdapter.HomeHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        return HomeHolder(
            ItemMusicBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), inter
        )
    }

    override fun getItemCount() = inter.getCount()

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        holder.binding.data = inter.getData(position)
    }

    interface IMusic {
        fun onItemClick(position: Int)
        fun getCount(): Int
        fun getData(position: Int): MusicData
    }

    class HomeHolder(val binding: ItemMusicBinding, inter: IMusic) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                inter.onItemClick(adapterPosition)
            }
        }
    }
}