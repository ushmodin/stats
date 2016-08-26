package ru.etraffic.stations.ws

import org.springframework.stereotype.Component
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.QueryParam

/**
 * Created by nikolay on 26.08.16.
 */
@Path("/stations")
@Component
open class StationsService {
    @Path("ping")
    @GET
    fun ping(@QueryParam("value") value: String) = value
}