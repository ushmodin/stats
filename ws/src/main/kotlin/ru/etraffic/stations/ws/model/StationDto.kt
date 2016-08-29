package ru.etraffic.stations.ws.model

import java.math.BigDecimal
import javax.validation.constraints.NotNull

class StationDto(
        var guid: String? = null,
        @NotNull
        var name: String? = null,
        var id: String? = null,
        var description: String? = null,
        var okato: String? = null,
        var regionGuid: String? = null,
        var regionName: String? = null,
        var latitude: BigDecimal? = null,
        var longitude: BigDecimal? = null
)