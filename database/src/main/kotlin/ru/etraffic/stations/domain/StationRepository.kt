package ru.etraffic.stations.domain

import org.springframework.data.jpa.repository.JpaRepository
import ru.etraffic.stations.domain.model.Station

/**
 *
 * @author Ushmodin N.
 * @since 26.08.2016 17:46
 */

interface StationRepository: JpaRepository<Station, Long>