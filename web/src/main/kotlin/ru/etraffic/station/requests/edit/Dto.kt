package ru.etraffic.station.requests.edit

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