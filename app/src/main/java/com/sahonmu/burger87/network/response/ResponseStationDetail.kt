package com.sahonmu.burger87.network.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class StationResult(
    val result: StationResultData
)

@Serializable
data class StationResultData(
    val stationName: String,
    @field:SerializedName("do") val add: String,
    val gu: String,
    val dong: String,
    val lane: List<StationBusNoData>
)

@Serializable
data class StationBusNoData(
    val busNo: String,
    val busID: Int,
)


