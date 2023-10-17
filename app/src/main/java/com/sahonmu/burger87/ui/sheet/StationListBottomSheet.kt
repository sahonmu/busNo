package com.sahonmu.burger87.ui.sheet

import androidx.recyclerview.widget.LinearLayoutManager
import com.sahonmu.burger87.R
import com.sahonmu.burger87.base.BaseBottomSheet
import com.sahonmu.burger87.databinding.SheetBusNoStationBinding
import com.sahonmu.burger87.network.response.BusNoStationData
import com.sahonmu.burger87.ui.sheet.adpater.BusNoStationSheetAdapter

class StationListBottomSheet: BaseBottomSheet<SheetBusNoStationBinding>(),
    BusNoStationSheetAdapter.IBusStationListener {

    private var title = ""
    private var itemList = mutableListOf<BusNoStationData>()
    private val adapter by lazy {
        BusNoStationSheetAdapter(this)
    }
    private var listener: IBusStationBottomSheetListener? = null

    interface IBusStationBottomSheetListener {
        fun onStationItemClick(stationData: BusNoStationData)
    }

    companion object {
        fun newInstance(title: String, itemList: MutableList<BusNoStationData>) = StationListBottomSheet().apply {
            this.title = title
            this.itemList = itemList
        }
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.sheet_bus_no_station
    }

    override fun initView() {
        isCancelable = true
        if(title.isNotEmpty()) {
            binding.titleTextView.text = title
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter.submitList(itemList)
    }

    override fun addEvent() {
        binding.closeButton.setOnClickListener { dismiss() }
    }

    override fun addObserver() { }

    fun setListener(listener: IBusStationBottomSheetListener) {
        this.listener = listener
    }

    override fun onStationItemClick(stationData: BusNoStationData) {
        listener?.onStationItemClick(stationData)
    }


}