package com.sahonmu.burger87.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sahonmu.burger87.base.BaseViewModel
import com.sahonmu.burger87.network.repository.BusRepository
import com.sahonmu.burger87.network.response.BusNoDetailData
import com.sahonmu.burger87.network.response.BusNoGraphLaneData
import com.sahonmu.burger87.network.response.BusNoTotalCityData
import com.sahonmu.burger87.network.response.StationResultData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BusViewModel @Inject constructor(
    private val busRepository: BusRepository
) : BaseViewModel() {

    private val _busNoListData = MutableLiveData<BusNoTotalCityData>()
    var busNoListData: LiveData<BusNoTotalCityData> = _busNoListData

    private val _busNoDetailData = MutableLiveData<BusNoDetailData>()
    var busNoDetailData: LiveData<BusNoDetailData> = _busNoDetailData

    private val _busNoGraphData = MutableLiveData<BusNoGraphLaneData>()
    var busNoGraphData: LiveData<BusNoGraphLaneData> = _busNoGraphData

    private val _stationDetailData = MutableLiveData<StationResultData>()
    var stationDetailData: LiveData<StationResultData> = _stationDetailData

    /**
     * 버스노선 목록 정보 조회
     */
    fun readBusNoList(busNo: String) {
        viewModelScope.launch {
            busRepository.readBusNoList(busNo)
                .onSuccess { response ->
                    _busNoListData.value = response.result
                }.onFailure { throwable ->
                    _toastLiveData.value = throwable.toString()
                }
        }
    }

    /**
     * 버스 노선 상세 정보 조회
     */
    fun readBusNoDetail(busNoId: Int) {
        viewModelScope.launch {
            busRepository.readBusNoDetail(busNoId)
                .onSuccess { response ->
                    _busNoDetailData.value = response.result
                }.onFailure { throwable ->
                    _toastLiveData.value = throwable.toString()
                }
        }
    }

    /**
     * 노선 그래프 정보 조회
     */
    fun readBusNoGraph(mapObject: String) {
        viewModelScope.launch {
            busRepository.readBusNoGraph(mapObject)
                .onSuccess { response ->
                    _busNoGraphData.value = response.result
                }.onFailure { throwable ->
                    _toastLiveData.value = throwable.toString()
                }
        }
    }

    /**
     * 정류장 상세 정보 조회
     */
    fun readStationDetail(stationId: Int) {
        viewModelScope.launch {
            busRepository.readStationDetail(stationId)
                .onSuccess { response ->
                    _stationDetailData.value = response.result
                }.onFailure { throwable ->
                    _toastLiveData.value = throwable.toString()
                }
        }
    }



}