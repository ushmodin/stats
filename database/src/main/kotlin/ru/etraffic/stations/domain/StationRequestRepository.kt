package ru.etraffic.stations.domain

import org.springframework.data.jpa.repository.JpaRepository
import ru.etraffic.stations.domain.model.AvsHost
import ru.etraffic.stations.domain.model.StationRequest

/**
 * Created by nikolay on 29.08.16.
 */
interface StationRequestRepository : JpaRepository<StationRequest, Long> {
    fun findByHostAndExtIdIn(host: AvsHost, extIds: List<String>): List<StationRequest>
}