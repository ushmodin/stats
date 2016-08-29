package ru.etraffic.stations.ws.model

import javax.validation.Valid
import javax.validation.constraints.NotNull

/**
 * Created by nikolay on 28.08.16.
 */
data class Request<T> (
        @NotNull
        @Valid
        var data: T? = null
)

data class AuthRequest<T> (
        @NotNull
        val host: String,
        @NotNull
        @Valid
        val data: T? = null
)