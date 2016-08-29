package ru.etraffic.stations.domain

import org.springframework.data.jpa.repository.JpaRepository
import ru.etraffic.stations.domain.model.StationBatch

/**
 * Created by nikolay on 29.08.16.
 */
interface StationBatchRepository: JpaRepository<StationBatch, Long> {
    fun findByGuid(guid: String): StationBatch
}