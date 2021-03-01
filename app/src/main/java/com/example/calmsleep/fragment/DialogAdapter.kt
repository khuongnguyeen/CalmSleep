package com.example.calmsleep.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.calmsleep.acivity.LoadingAcivity
import com.example.calmsleep.databinding.ItemChilBinding
import com.example.calmsleep.databinding.ItemMusicBinding
import com.example.calmsleep.model.MusicData

class DialogAdapter(val list: MutableList<MusicData>) : RecyclerView.Adapter<DialogAdapter.HomeHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        return HomeHolder(
            ItemChilBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount() = list.size
    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        holder.binding.data = list[position]
    }



    class HomeHolder(val binding: ItemChilBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                LoadingAcivity.service!!.play(binding.data!!.id)
            }
        }
    }
}