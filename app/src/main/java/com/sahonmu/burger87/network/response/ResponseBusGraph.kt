package com.sahonmu.burger87.network.response

import kotlinx.serialization.Serializable

@Serializable
data class BusNoGraphResultData(
    val result: BusNoGraphLaneData
)

@Serializable
data class BusNoGraphLaneData(
    val lane: List<BusNoGraphSectionData>
)

@Serializable
data class BusNoGraphSectionData(
    val type: String,
    val section: List<BusNoGraphPosData>
)

@Serializable
data class BusNoGraphPosData(
    val graphPos: List<BusNoGraphNodeData>,
)

@Serializable
data class BusNoGraphNodeData(
    val x: Double,
    val y: Double
)

