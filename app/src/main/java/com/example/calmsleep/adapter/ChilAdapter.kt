package com.example.calmsleep.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.calmsleep.databinding.ItemChilBinding
import com.example.calmsleep.model.MusicData

class ChilAdapter(private val inter: IMusicOne,val list: MutableList<MusicData>) : RecyclerView.Adapter<ChilAdapter.HomeHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        return HomeHolder(
            ItemChilBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            inter
        )
    }

    override fun getItemCount() = list.size
    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        holder.binding.data = list[position]
    }

    interface IMusicOne {
        fun getDataOk(): MutableList<MusicData>
        fun onClick(position: Int)
    }

    class HomeHolder(val binding: ItemChilBinding, inter: IMusicOne) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                inter.onClick(adapterPosition)
            }
        }
    }
}