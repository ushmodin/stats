package ru.etraffic.stations.domain

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import ru.etraffic.stations.domain.model.Area
import ru.etraffic.stations.domain.model.EntityStatus
import java.util.*

/**
 *
 * @author Ushmodin N.
 * @since 26.08.2016 17:44
 */

interface AreaRepository: JpaRepository<Area, Long> {
    @Query("select a from Area a where (a.region.id = ?1 or ?1 is null) " +
                                       "and a.status = ?2 " +
                                       "and (?3 is null or lower(a.name) like ?3)" +
                                       "order by a.name ")
    fun findByRegionIdAndStatus(regionId: Optional<Long>,
                                status: EntityStatus,
                                name: Optional<String>,
                                pageable: Pageable): List<Area>
}