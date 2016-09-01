package ru.etraffic.stations.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import ru.etraffic.stations.domain.model.Area
import java.util.*

/**
 *
 * @author Ushmodin N.
 * @since 26.08.2016 17:44
 */

interface AreaRepository: JpaRepository<Area, Long> {
    @Query("select a from Area a where a.region.id = ?1 or ?1 is null")
    fun findByRegionId(regionId: Optional<Long>): List<Area>
}