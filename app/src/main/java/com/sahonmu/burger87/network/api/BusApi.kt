package com.sahonmu.burger87.network.api

import com.sahonmu.burger87.network.response.BusNoDetailResultData
import com.sahonmu.burger87.network.response.BusNoGraphResultData
import com.sahonmu.burger87.network.response.BusNoResultData
import com.sahonmu.burger87.network.response.StationResult
import retrofit2.http.*

interface BusApi {

    @GET("v1/api/searchBusLane")
    suspend fun readBusNoList(
        @Query("busNo") busNo: String,
        @Query("apiKey") apiKey: String,
    ): BusNoResultData

    @GET("v1/api/busLaneDetail")
    suspend fun readBusNoDetail(
        @Query("busID") busId: Int,
        @Query("apiKey") apiKey: String,
    ): BusNoDetailResultData

    @GET("v1/api/loadLane")
    suspend fun readBusNoGraph(
        @Query("mapObject") mapObject: String,
        @Query("apiKey") apiKey: String,
    ): BusNoGraphResultData

    @GET("v1/api/busStationInfo")
    suspend fun readStationDetail(
        @Query("stationID") stationID: Int,
        @Query("apiKey") apiKey: String,
    ): StationResult

}