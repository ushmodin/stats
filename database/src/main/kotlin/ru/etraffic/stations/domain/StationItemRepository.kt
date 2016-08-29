package ru.etraffic.stations.domain

import org.springframework.data.jpa.repository.JpaRepository
import ru.etraffic.stations.domain.model.StationBatch
import ru.etraffic.stations.domain.model.StationItem

/**
 * Created by nikolay on 29.08.16.
 */
interface StationItemRepository : JpaRepository<StationItem, Long> {
    fun findByBatchAndStationNotNull(batch: StationBatch): List<StationItem>
}