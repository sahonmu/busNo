package com.sahonmu.burger87.ui.bus.detail

import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*
import com.sahonmu.burger87.R
import com.sahonmu.burger87.extension.dpToPx
import com.sahonmu.burger87.network.response.BusNoDetailData
import com.sahonmu.burger87.network.response.BusNoGraphSectionData
import com.sahonmu.burger87.network.response.BusNoStationData
import com.sahonmu.burger87.utils.BitmapUtils


class MapFragment : SupportMapFragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener,
    GoogleMap.OnMapClickListener {

    interface IMapListener {
        fun onMapReady()
        fun onMapClick()
        fun onMarkerClick(stationData: BusNoStationData)
    }

    private var listener: IMapListener? = null

    private var busNoData: BusNoDetailData? = null
    private var googleMap: GoogleMap? = null
    private lateinit var selectedMarker: Marker

    private val Z_INDEX_POLYLINE = 1f
    private val Z_INDEX_DEFAULT = 2f
    private val Z_INDEX_SELECTED = 3f
    private val SELECT_MARKER_ZOOM = 15f

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
        initMapOptions(googleMap)
        initSelectedMarker(googleMap)
        busNoData?.let { busNoData ->
            drawStationList(busNoData)
        }
        listener?.onMapReady()
    }

    fun asyncMap(data: BusNoDetailData) {
        this.busNoData = data
        getMapAsync(this)
    }

    private fun initMapOptions(googleMap: GoogleMap) {
        googleMap.uiSettings.apply {
            this.isCompassEnabled = false
            this.isTiltGesturesEnabled = false
            this.isZoomControlsEnabled = false
            this.isMapToolbarEnabled = false
        }

        googleMap.setOnMapClickListener(this)
        googleMap.setOnMarkerClickListener(this)

        googleMap.setMapStyle(
            MapStyleOptions.loadRawResourceStyle(
                requireContext(), R.raw.map_style
            )
        )
    }

    private fun initSelectedMarker(googleMap: GoogleMap) {
        val size = requireContext().dpToPx(48f)
        val bitmapDescriptor =
            BitmapUtils.getBitmapDescriptor(requireContext(), R.drawable.ic_bus_station, size, size)
        bitmapDescriptor?.let { icon ->
            val options = markerOptions(icon, LatLng(0.0, 0.0), Z_INDEX_SELECTED).apply {
                visible(false)
            }
            googleMap.addMarker(options)?.let { marker ->
                selectedMarker = marker
            }
        }
    }


    private fun drawStationList(busNoData: BusNoDetailData) {
        val bounds = LatLngBounds.builder()
        busNoData.station.forEach { stationData ->
            drawStation(stationData)
            bounds.include(LatLng(stationData.y, stationData.x))
        }
        val latLngBound = bounds.build()
        googleMap?.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBound, 100))
    }

    private fun drawStation(stationData: BusNoStationData) {
        val size = requireContext().dpToPx(32f)
        val bitmapDescriptor =
            BitmapUtils.getBitmapDescriptor(requireContext(), R.drawable.ic_bus_station, size, size)
        bitmapDescriptor?.let { icon ->
            val options = markerOptions(icon, LatLng(stationData.y, stationData.x), Z_INDEX_DEFAULT)
            googleMap?.addMarker(options)?.let { marker ->
                marker.tag = stationData
            }
        }
    }

    private fun markerOptions(icon: BitmapDescriptor, latLng: LatLng, zIndex: Float): MarkerOptions {
        return MarkerOptions().apply {
            position(latLng)
            icon(icon)
            anchor(0.5f, 1.0f)
            zIndex(zIndex)
        }
    }

    fun drawGraph(laneList: List<BusNoGraphSectionData>) {
        val options = PolylineOptions().apply {
            zIndex(Z_INDEX_POLYLINE)
            laneList.forEach { laneData ->
                laneData.section.forEach { sectionData ->
                    sectionData.graphPos.forEach { nodeData ->
                        add(LatLng(nodeData.y, nodeData.x))
                    }
                }
            }
        }
        googleMap?.addPolyline(options)

    }

    fun unSelectMarker() {
        selectedMarker.isVisible = false
    }

    fun selectMarker(lat: Double, lng: Double) {
        selectedMarker.position = LatLng(lat, lng)
        selectedMarker.isVisible = true
        animateCamera(lat, lng, SELECT_MARKER_ZOOM)
    }

    fun animateCamera(lat: Double, lng: Double, zoom: Float = -1f) {
        googleMap?.let { map ->
            map.animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(lat, lng),
                    if (zoom == -1f) map.cameraPosition.zoom else zoom
                )
            )
        }
    }

    fun setMapListener(listener: IMapListener) {
        this.listener = listener
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        val tag = marker.tag
        if(tag is BusNoStationData) {
            listener?.onMarkerClick(tag)
        }
        return true
    }

    override fun onMapClick(latLng: LatLng) {
        listener?.onMapClick()
    }


}