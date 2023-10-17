package com.sahonmu.burger87.enums

enum class BusType(val busTypeCode: Int, val busTypeName: String) {
    ONE(1, "일반"),
    TWO(2, "좌석"),
    THREE(3, "마을버스"),
    FOUR(4, "직행좌석"),
    FIVE(5, "공항버스"),
    SIX(6, "간선급행"),
    TEN(10, "외곽"),
    ELEVEN(11, "간선"),
    TWELVE(12, "지선"),
    THIRTEEN(13, "순환"),
    FOURTEEN(14, "광역"),
    FIFTEEN(15, "급행"),
    SIXTEEN(16, "관광버스"),
    TWENTY(2, "농어촌버스"),
    TWENTY_TWO(22, "경기도 시외형버스"),
    TWENTY_SIX(26, "급행간선"),
    ELSE(-1, ""),
}

fun busType(busTypeCode: Int): BusType {
    return when(busTypeCode) {
        BusType.ONE.busTypeCode -> {
            BusType.ONE
        }
        BusType.TWO.busTypeCode -> {
            BusType.TWO
        }
        BusType.THREE.busTypeCode -> {
            BusType.THREE
        }
        BusType.FOUR.busTypeCode -> {
            BusType.FOUR
        }
        BusType.FIVE.busTypeCode -> {
            BusType.FIVE
        }
        BusType.SIX.busTypeCode -> {
            BusType.SIX
        }
        BusType.TEN.busTypeCode -> {
            BusType.TEN
        }
        BusType.ELEVEN.busTypeCode -> {
            BusType.ELEVEN
        }
        BusType.TWELVE.busTypeCode -> {
            BusType.TWELVE
        }
        BusType.THIRTEEN.busTypeCode -> {
            BusType.THIRTEEN
        }
        BusType.FOURTEEN.busTypeCode -> {
            BusType.FOURTEEN
        }
        BusType.FIFTEEN.busTypeCode -> {
            BusType.FIFTEEN
        }
        BusType.SIXTEEN.busTypeCode -> {
            BusType.SIXTEEN
        }
        BusType.TWENTY.busTypeCode -> {
            BusType.TWENTY
        }
        BusType.TWENTY_TWO.busTypeCode -> {
            BusType.TWENTY_TWO
        }
        BusType.TWENTY_SIX.busTypeCode -> {
            BusType.TWENTY_SIX
        }
        else -> {
            BusType.ELSE
        }
    }
}