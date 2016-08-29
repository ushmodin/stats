package ru.etraffic.stations.ws.model

import javax.validation.constraints.Size

/**
 * Created by nikolay on 28.08.16.
 */
data class RegReq (
        @Size(min = 3)
        val name: String,
        @Size(min = 10, max = 12)
        val inn: String
)