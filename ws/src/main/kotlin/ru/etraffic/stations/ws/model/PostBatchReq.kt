package ru.etraffic.stations.ws.model

import javax.validation.constraints.NotNull

/**
 * Created by nikolay on 28.08.16.
 */
class PostBatchReq(
        @NotNull
        val stations: List<StationDto>
)

