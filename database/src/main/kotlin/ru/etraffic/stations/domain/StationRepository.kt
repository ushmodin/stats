package ru.etraffic.stations.domain

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import ru.etraffic.stations.domain.model.EntityStatus
import ru.etraffic.stations.domain.model.Station
import java.util.*

/**
 *
 * @author Ushmodin N.
 * @since 26.08.2016 17:46
 */

interface StationRepository: JpaRepository<Station, Long>, JpaSpecificationExecutor<Station>, StationFullTextRepository {
    @Query("select s from Station s where (s.region.id = ?1 or ?1 is null) " +
                                          "and (s.area.id = ?2 or ?2 is null) " +
                                          "and (s.city.id = ?3 or ?3 is null) " +
                                          "and (s.place.id = ?4 or ?4 is null) " +
                                          "and (s.status = ?5) " +
                                          "and (?6 is null or lower(s.name) like ?6) " +
                                          "order by s.name")
    fun findByAreaIdAndRegionIdAndPlaceIdAndStatus(regionId: Optional<Long>,
                                                   areaId: Optional<Long>,
                                                   cityId: Optional<Long>,
                                                   placeId: Optional<Long>,
                                                   status: EntityStatus,
                                                   name: Optional<String>,
                                                   pageable: Pageable): List<Station>
}