package ru.etraffic.station.common

import ru.etraffic.stations.domain.model.*

/**
 * Created by nikolay on 01.09.16.
 */
data class HostDto (
        val id: Long?,
        val name: String?,
        val inn: String?
)

data class CountryDto (
        val id: Long?,
        val name: String?
)

data class RegionDto (
        val id: Long?,
        val guid: String?,
        val name: String?,
        val country: CountryDto?
)

data class AreaDto (
        val id: Long?,
        val guid: String?,
        val name: String?,
        val region: RegionDto?
)

data class PlaceDto (
        val id: Long?,
        val guid: String?,
        val name: String?,
        val region: RegionDto?,
        val area: AreaDto?,
        val city: PlaceDto?
)

data class StationDto (
        val id: Long?,
        val guid: String?,
        val name: String?,
        val region: RegionDto?,
        val area: AreaDto? = null,
        val city: PlaceDto? = null
)

data class AddrObjTypeDto (
        val name: String,
        val shortName: String
)

fun AvsHost.toDto() = HostDto(id = this.id, name = this.name, inn = this.inn )

fun Country.toDto() = CountryDto(id = this.id, name = this.shortName)

fun Region.toDto() = RegionDto(
        id = this.id
        ,guid = this.guid
        ,name = this.name
        ,country = this.country?.toDto())

fun Area.toDto() = AreaDto(
        id = this.id
        ,guid = this.guid
        ,name = this.name
        ,region = this.region?.toDto())

fun Place.toDto() = PlaceDto(
        id = this.id
        , guid = this.guid
        , name = this.name
        , region = this.region?.toDto()
        , area = this.area?.let { AreaDto(id = it.id, guid = null, name = it.name, region = null) }
        , city = this.city?.let { PlaceDto(id = it.id, guid = null, name = it.name, region = null, area = null, city = null) })

fun Station.toDto() = StationDto(
        id = this.id
        , guid = this.guid
        , name = this.name
        , region = this.region?.toDto()
        , area = this.area?.let { AreaDto(id = it.id, guid = null, name = it.name, region = null) }
        , city = this.city?.let { PlaceDto(id = it.id, guid = null, name = it.name, region = null, area = null, city = null) })
