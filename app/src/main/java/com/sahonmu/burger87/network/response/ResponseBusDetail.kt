package com.sahonmu.burger87.network.response

import kotlinx.serialization.Serializable

@Serializable
data class BusNoDetailResultData(
    val result: BusNoDetailData
)

@Serializable
data class BusNoDetailData(
    val busNo: String,
    val busID: Int,
    val type: Int,
    val busCityName: String,
    val busCityCode: Int,
    val busStartPoint: String,
    val busEndPoint: String,
    val busFirstTime: String,
    val busLastTime: String,
    val busInterval: String,
    val station: List<BusNoStationData>
)

@Serializable
data class BusNoStationData(
    val stationName: String,
    val stationID: Int,
    val stationDistance: Long,
    val arsID: String,
    val x: Double,
    val y: Double,
)

