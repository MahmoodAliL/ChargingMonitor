package com.teaml.chargingmonitor.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teaml.chargingmonitor.data.model.ui.HomeItem
import com.teaml.chargingmonitor.ui.base.BaseViewHolder
import com.teaml.chargingmonitor.databinding.RvItemHomeViewBinding

class RvHomeAdapter(private val list: List<HomeItem>) : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding = RvItemHomeViewBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    inner class HomeViewHolder(private val binding: RvItemHomeViewBinding)
        : BaseViewHolder(binding.root) {
        override fun onBind(position: Int) {
            binding.homeItem = list[position]
        }
    }

}