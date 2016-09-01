package ru.etraffic.stations.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import ru.etraffic.stations.domain.model.Region
import java.util.*

/**
 *
 * @author Ushmodin N.
 * @since 26.08.2016 17:44
 */

interface  RegionRepository:JpaRepository<Region, Long> {
    @Query("select r from Region r where r.country.id = ?1")
    fun findByCountryId(countryId: Optional<Long>): List<Region>
}