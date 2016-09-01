package ru.etraffic.station.requests.index

import java.math.BigDecimal

/**
 * Created by nikolay on 01.09.16.
 */
data class RequestFilter (
        val hostId: Long? = null,
        val name: String? = null
)

data class RequestDto (
        val id: Long? = null,
        val country: String? = null,
        val region: String? = null,
        val area: String? = null,
        val place: String? = null,
        val name: String? = null,
        val regionName: String? = null,
        val okato: String? = null,
        val latitude: BigDecimal? = null,
        val longitude: BigDecimal? = null,
        val station: String? = null
)