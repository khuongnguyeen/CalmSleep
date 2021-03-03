package com.example.calmsleep.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.calmsleep.application.MyApp
import com.example.calmsleep.databinding.ItemVetiBinding
import com.example.calmsleep.model.MusicData
import com.example.calmsleep.ui.adapter.HomeAdapter

class VerticalHomeAdapter(val context: Context, val inter: IMusic) :
    RecyclerView.Adapter<VerticalHomeAdapter.VerticalHomeHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalHomeHolder {
        return VerticalHomeHolder(
            ItemVetiBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), inter
        )
    }

    override fun getItemCount() = inter.getCount()

    override fun onBindViewHolder(holder: VerticalHomeHolder, position: Int) {
        val homeAdapter = HomeAdapter(MyApp.getDB().getMusicAlbumId(position+1))
        holder.binding.tvText.text = MyApp.getDB().getAlbumId(position+1)
        load(holder, homeAdapter)
        Log.e("ok", "-----------------ok position: $position")
    }

    private fun load(holder: VerticalHomeHolder, homeAdapter: HomeAdapter) {
        holder.binding.rc.setHasFixedSize(true)
        holder.binding.rc.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        holder.binding.rc.adapter = homeAdapter
        holder.binding.rc.adapter!!.notifyDataSetChanged()
    }

    interface IMusic {
        fun getCount(): Int
        fun getData(position: Int): MusicData
        fun onClick(position: Int)
    }


    class VerticalHomeHolder(val binding: ItemVetiBinding, inter: IMusic) :
        RecyclerView.ViewHolder(binding.root) {
        init {

            binding.btnViewAll.setOnClickListener {
                inter.onClick(adapterPosition)
            }
        }
    }
}