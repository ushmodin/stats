package ru.etraffic.station.common

/**
 * Created by nikolay on 01.09.16.
 */
data class HostDto (
        val id: Long,
        val name: String,
        val inn: String
)

data class CountryDto (
        val id: Long,
        val name: String
)

data class RegionDto (
    val id: Long,
    val guid: String,
    val name: String
)

data class AreaDto (
        val id: Long,
        val guid: String,
        val name: String
)

data class PlaceDto (
        val id: Long,
        val guid: String,
        val name: String
)

data class StationDto (
        val id: Long,
        val guid: String,
        val name: String
)

data class AddrObjTypeDto (
        val name: String,
        val shortName: String
)