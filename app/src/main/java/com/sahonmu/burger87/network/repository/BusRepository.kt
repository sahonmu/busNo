package com.sahonmu.burger87.network.repository

import com.sahonmu.burger87.network.NetworkConstants.API_KEY
import com.sahonmu.burger87.network.api.BusApi
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class BusRepository @Inject constructor(
    private val busApi: BusApi
) {

    suspend fun readBusNoList(busNo: String) =
        runCatching {
            busApi.readBusNoList(busNo, API_KEY)
        }

    suspend fun readBusNoDetail(busNoId: Int) =
        runCatching {
            busApi.readBusNoDetail(busNoId, API_KEY)
        }

    suspend fun readBusNoGraph(mapObject: String) =
        runCatching {
            busApi.readBusNoGraph(mapObject, API_KEY)
        }

    suspend fun readStationDetail(stationId: Int) =
        runCatching {
            busApi.readStationDetail(stationId, API_KEY)
        }

}