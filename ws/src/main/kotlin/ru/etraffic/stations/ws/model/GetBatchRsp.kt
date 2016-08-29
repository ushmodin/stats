package ru.etraffic.stations.ws.model

/**
 * Created by nikolay on 29.08.16.
 */
data class GetBatchRsp (
        var stations: List<StationDto>? = null
)