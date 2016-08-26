package ru.etraffic.stations.domain

import org.springframework.data.jpa.repository.JpaRepository
import ru.etraffic.stations.domain.model.Area

/**
 *
 * @author Ushmodin N.
 * @since 26.08.2016 17:44
 */

interface AreaRepository: JpaRepository<Area, Long>