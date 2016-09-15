package ru.etraffic.stations.ws.model

import java.math.BigDecimal
import javax.validation.constraints.NotNull

class StationDto(
        var guid: String? = null,
        @NotNull
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