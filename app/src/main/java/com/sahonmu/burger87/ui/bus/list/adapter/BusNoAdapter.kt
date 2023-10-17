package com.sahonmu.burger87.ui.bus.list.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sahonmu.burger87.R
import com.sahonmu.burger87.databinding.ItemBusNoBinding
import com.sahonmu.burger87.enums.busType
import com.sahonmu.burger87.extension.pretty
import com.sahonmu.burger87.network.response.LaneData

class BusNoAdapter(private val listener: IBusNoClickListener) : ListAdapter<LaneData, BusNoAdapter.ViewHolder>(
    ItemCallBack()
) {

    interface IBusNoClickListener {
        fun onBusNoItemClick(busNoId: Int)
    }

    class ItemCallBack : DiffUtil.ItemCallback<LaneData>() {
        override fun areItemsTheSame(
            oldItem: LaneData,
            newItem: LaneData
        ): Boolean {
            return oldItem.busID == newItem.busID
        }

        override fun areContentsTheSame(
            oldItem: LaneData,
            newItem: LaneData
        ): Boolean {
            return oldItem.busID == newItem.busID
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemBusNoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemBusNoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val context = binding.root.context

        fun bind(item: LaneData) {
            binding.data = item

            val busTypeName = busType(item.type).busTypeName
            val busNoName = context.getString(R.string.bus_no_name, busTypeName, item.busNo)
            binding.busNoNameTextView.pretty(busNoName)

            binding.itemLayout.setOnClickListener { listener.onBusNoItemClick(item.busID) }
        }
    }

}