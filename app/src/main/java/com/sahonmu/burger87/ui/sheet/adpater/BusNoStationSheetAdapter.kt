package com.sahonmu.burger87.ui.sheet.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sahonmu.burger87.databinding.ItemBusNoStationBinding
import com.sahonmu.burger87.network.response.BusNoStationData

class BusNoStationSheetAdapter(private val listener: IBusStationListener) : ListAdapter<BusNoStationData, BusNoStationSheetAdapter.ViewHolder>(
    ItemCallBack()
) {

    interface IBusStationListener {
        fun onStationItemClick(stationData: BusNoStationData)
    }

    class ItemCallBack : DiffUtil.ItemCallback<BusNoStationData>() {
        override fun areItemsTheSame(
            oldItem: BusNoStationData,
            newItem: BusNoStationData
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: BusNoStationData,
            newItem: BusNoStationData
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemBusNoStationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemBusNoStationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BusNoStationData) {
            binding.data = item
//            val busNoName = binding.root.context.getString(R.string.bus_no_name, item.busNo)
//            binding.busNoNameTextView.pretty(busNoName)
            binding.itemLayout.setOnClickListener { listener.onStationItemClick(item) }
        }
    }

}