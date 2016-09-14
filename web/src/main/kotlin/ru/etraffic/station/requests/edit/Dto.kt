package ru.etraffic.station.requests.edit

import java.math.BigDecimal
import java.util.*

/**
 * Created by nikolay on 02.09.16.
 */
data class StationRequestDto (
    val id: Long,
    val name: String,
    val regionName: String,
    val okato: String
)

data class StationDto (
        val id: Long,
        val name: String,
        val type: String,
        val country: String,
        val region: String,
        val area: String?,
        val city: String?,
        val place: String,
        val okato: String?
)

data class StationFilter (
        val name: String?,
        val type: String?,
        val countryId: Long?,
        val regionId: Long?,
        val areaId: Long?,
        val cityId: Long?,
        val placeId: Long?,
        val okato: String?
)

data class LinkDto (
        val requestId: Long,
        val stationId: Long
)

data class NewStationDto (
        val name: String?,
        val type: String?,
        val areaId: Long?,
        val cityId: Long?,
        val placeId: Long?,
        val okato: String?,
        val latitude: BigDecimal? = null,
        val longitude: BigDecimal? = null,
        val requestId: Long? = null
)