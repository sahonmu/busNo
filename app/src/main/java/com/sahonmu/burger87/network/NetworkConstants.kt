package com.sahonmu.burger87.network


object NetworkConstants {

    enum class SERVER {
        DEV,
        QA,
        STAGING,
        LIVE,
    }

    val BUS_API = "https://api.odsay.com/"
    const val API_KEY = "QMUxGUif3efI7amTs/g8M3gfFuoDUmMgnBhQltodfr8"

    const val CONNECT_TIMEOUT = 30L
    const val WRITE_TIMEOUT = 30L
    const val READ_TIMEOUT = 30L



}