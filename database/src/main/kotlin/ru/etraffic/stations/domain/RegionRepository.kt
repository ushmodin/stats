package ru.etraffic.stations.domain

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import ru.etraffic.stations.domain.model.EntityStatus
import ru.etraffic.stations.domain.model.Region
import java.util.*

/**
 *
 * @author Ushmodin N.
 * @since 26.08.2016 17:44
 */

interface  RegionRepository:JpaRepository<Region, Long> {
    @Query("select r from Region r where (r.country.id = ?1 or ?1 is null) " +
                                         "and r.status = ?2 " +
                                         "and (?3 is null or lower(r.name) like ?3) " +
                                         "order by r.name")
    fun findByCountryIdAndStatus(countryId: Optional<Long>,
                                 status: EntityStatus,
                                 name: Optional<String>,
                                 pageable: Pageable): List<Region>
}