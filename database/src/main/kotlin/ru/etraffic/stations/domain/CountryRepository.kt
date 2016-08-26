package ru.etraffic.stations.domain

import org.springframework.data.jpa.repository.JpaRepository
import ru.etraffic.stations.domain.model.Country

/**
 *
 * @author Ushmodin N.
 * @since 26.08.2016 16:46
 */

interface CountryRepository: JpaRepository<Country, Long> {
}