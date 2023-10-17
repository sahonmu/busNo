package com.sahonmu.burger87.ui.bus.detail

import android.content.Intent
import androidx.fragment.app.activityViewModels
import com.sahonmu.burger87.base.BaseFragment
import com.sahonmu.burger87.R
import com.sahonmu.burger87.databinding.FragmentBusNoDetailBinding
import com.sahonmu.burger87.network.response.BusNoDetailData
import com.sahonmu.burger87.network.response.BusNoStationData
import com.sahonmu.burger87.ui.sheet.StationListBottomSheet
import com.sahonmu.burger87.utils.FragmentUtils
import com.sahonmu.burger87.viewmodels.BusViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BusNoDetailFragment: BaseFragment<FragmentBusNoDetailBinding>(), MapFragment.IMapListener {

    private val busViewModel by activityViewModels<BusViewModel>()

    private var busNoId = -1
    private var busNoDetailData: BusNoDetailData? = null

    private val mapView = MapFragment()

    companion object {
        fun newInstance(busNoId: Int) = BusNoDetailFragment().apply {
            this.busNoId = busNoId
        }
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_bus_no_detail
    }

    override fun initView() {
        readBusNoDetail(busNoId)
    }

    override fun addEvent() {
        onBackPressed = {
            onBack()
        }

        binding.backButton.setOnClickListener { onBackPressed() }

        mapView.setMapListener(this)

        binding.showBusStationButton.setOnClickListener {
            busNoDetailData?.let { data ->
                StationListBottomSheet.newInstance(
                    title = data.busNo,
                    itemList = data.station.toMutableList()
                ).apply {
                    this.setListener(object : StationListBottomSheet.IBusStationBottomSheetListener {
                        override fun onStationItemClick(stationData: BusNoStationData) {
                            onMarkerClick(stationData)
                            dismiss()
                        }
                    })
                }.show()
            }
        }

        binding.stationSummaryView.onBusNoItemClick = { busNoData ->
            intentBusNoDetail(busNoData.busID)
        }
    }

    override fun addObserver() {
        busViewModel.busNoDetailData.observe(this) { data ->
            bind(data)
        }

        busViewModel.busNoGraphData.observe(this) { laneData ->
            if(laneData.lane.isNotEmpty()) {
                mapView.drawGraph(laneData.lane)
            }
        }

        busViewModel.stationDetailData.observe(this) { stationData ->
            binding.stationSummaryView.bind(stationData)
        }
    }

    private fun initMap(data: BusNoDetailData) {
        FragmentUtils.replaceFragmentNoBackstack(
            childFragmentManager,
            mapView,
            binding.mapContainer.id
        )
        mapView.asyncMap(data)
    }

    private fun bind(data: BusNoDetailData) {
        busNoDetailData = data
        binding.title = data.busNo
        binding.busNoNameTextView.isSelected = true
        binding.busNoSummaryView.bind(data)
        initMap(data)
    }

    private fun readBusNoDetail(busNoId: Int) {
        busViewModel.readBusNoDetail(busNoId)
    }

    private fun readBusNoGraph(busNoId: Int) {
        val mapObject = "0:0@${busNoId}:1:-1:-1"
        busViewModel.readBusNoGraph(mapObject)
    }

    private fun showStationSummary() {
        binding.stationSummaryView.show()
    }

    private fun hideStationSummary() {
        binding.stationSummaryView.hide()
    }

    private fun intentBusNoDetail(busNoId: Int) {
        val intent = Intent(requireContext(), BusNoDetailActivity::class.java).apply {
            this.putExtra(getString(R.string.intent_key_bus_id), busNoId)
        }
        startActivity(intent)
    }

    private fun onBack() {
        if(binding.stationSummaryView.isShow) {
            onMapClick()
            return
        }
        onBackPressed()
    }

    override fun onMapReady() {
        busNoDetailData?.let { data ->
            readBusNoGraph(data.busID)
        }
    }

    override fun onMapClick() {
        hideStationSummary()
        mapView.unSelectMarker()
    }

    override fun onMarkerClick(stationData: BusNoStationData) {
        showStationSummary()
        mapView.selectMarker(stationData.y, stationData.x)
        busViewModel.readStationDetail(stationData.stationID)
    }



}