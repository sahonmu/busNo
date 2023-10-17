package com.sahonmu.burger87.network.response

import kotlinx.serialization.Serializable

@Serializable
data class BusNoResultData(
    val result: BusNoTotalCityData
)

@Serializable
data class BusNoTotalCityData(
    val totalCityList: IncludeCityListData,
    val totalCount: Int,
    val lane: List<LaneData>
)


@Serializable
data class IncludeCityListData(
    val includeCity: List<IncludeCityData>,
)

@Serializable
data class IncludeCityData(
    val cityName: String,
    val CID: Int
)

@Serializable
data class LaneData(
    val busNo: String,
    val busID: Int,
    val type: Int,
    val busCityName: String,
    val busCityCode: Int,
    val busStartPoint: String,
    val busEndPoint: String,
    val busFirstTime: String,
    val busLastTime: String,
    val busCompanyNameKor: String,
)

