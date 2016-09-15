package ru.etraffic.stations.client

import java.math.BigDecimal

/**
 *
 * @author Ushmodin N.
 * @since 30.08.2016 10:46
 */
data class Request<T> (
        var data: T? = null
)

data class AuthRequest<T> (
        val host: String,
        val data: T? = null
)
open class Response<T> (
        val success: Boolean = true,
        val data: T? = null,
        val error: Error? = null
)

class Error (
        val code: String? =  null,
        val message: String? = null
)

data class StationDto (
        var guid: String? = null,
        var name: String? = null,
        var hostId: String? = null,
        var description: String? = null,
        var okato: String? = null,
        var countryGuid: String? = null,
        var countryName: String? = null,
        var regionGuid: String? = null,
        var regionName: String? = null,
        var areaName: String? = null,
        var areaGuid: String? = null,
        var cityGuid: String? = null,
        var cityName: String? = null,
        var latitude: BigDecimal? = null,
        var longitude: BigDecimal? = null
)

data class GuidRsp(
        val stations: List<StationDto>
)

data class GuidReq(
        val stations: List<StationDto>
)

data class RegReq (
        val name: String,
        val inn: String
)

data class RegRsp (
        val hostGuid: String
)