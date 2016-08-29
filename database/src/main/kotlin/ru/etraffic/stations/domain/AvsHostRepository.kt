package ru.etraffic.stations.domain

import org.springframework.data.jpa.repository.JpaRepository
import ru.etraffic.stations.domain.model.AvsHost

/**
 *
 * @author Ushmodin N.
 * @since 26.08.2016 17:52
 */

interface AvsHostRepository: JpaRepository<AvsHost, Long> {
    fun findByGuid(host: String): AvsHost
}