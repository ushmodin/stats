package ru.etraffic.stations.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import ru.etraffic.stations.domain.model.Place
import java.util.*

/**
 *
 * @author Ushmodin N.
 * @since 26.08.2016 17:45
 */

interface PlaceRepository: JpaRepository<Place, Long> {
    @Query("select p from Place p where (p.region.id = ?1 or ?1 is null) " +
                                         "and (p.area.id = ?2 or ?2 is null)" +
                                         "and (p.city.id = ?3 or ?3 is null)")
    fun findByAreaIdAndRegionId(areaId: Optional<Long>,
                                regionId: Optional<Long>,
                                cityId: Optional<Long>): List<Place>
}