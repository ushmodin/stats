package ru.etraffic.stations.ws.model

import javax.validation.constraints.NotNull

/**
 * Created by nikolay on 28.08.16.
 */
data class GuidReq(
        @NotNull
        val stations: List<StationDto>
)

