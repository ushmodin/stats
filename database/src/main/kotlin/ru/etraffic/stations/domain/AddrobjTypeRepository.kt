package ru.etraffic.stations.domain

import org.springframework.data.jpa.repository.JpaRepository
import ru.etraffic.stations.domain.model.AddrobjType

/**
 *
 * @author Ushmodin N.
 * @since 31.08.2016 15:24
 */

interface  AddrobjTypeRepository: JpaRepository<AddrobjType, Long>