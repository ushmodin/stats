package ru.etraffic.stations.domain

import org.springframework.data.jpa.repository.JpaRepository
import ru.etraffic.stations.domain.model.Place

/**
 *
 * @author Ushmodin N.
 * @since 26.08.2016 17:45
 */

interface PlaceRepository: JpaRepository<Place, Long>