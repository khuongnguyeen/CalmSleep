package com.example.calmsleep.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.calmsleep.acivity.LoadingAcivity
import com.example.calmsleep.application.MyApp
import com.example.calmsleep.databinding.ItemVetiBinding
import com.example.calmsleep.model.MusicData
import com.example.calmsleep.model.VerticalModel
import com.example.calmsleep.ui.adapter.HomeAdapter

class VerticalHomeAdapter(val context: Context, val inter: IMusic) :
    RecyclerView.Adapter<VerticalHomeAdapter.VerticalHomeHolder>(), HomeAdapter.IMusicOne {


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

        val homeAdapter = HomeAdapter(this, inter.getData(position).list)
        holder.binding.rc.setHasFixedSize(true)
        holder.binding.data = inter.getData(position)
        holder.binding.rc.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        holder.binding.rc.adapter = homeAdapter
        holder.binding.rc.adapter!!.notifyDataSetChanged()

    }

    interface IMusic {
        fun getCount(): Int
        fun getData(position: Int): VerticalModel
        fun onClick(position: Int)
    }


    override fun onClick(position: Int) {

        Log.d("ok", "-----------------ok: $position")
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