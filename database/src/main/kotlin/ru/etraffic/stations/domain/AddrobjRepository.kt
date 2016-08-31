package ru.etraffic.stations.domain

import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import ru.etraffic.stations.domain.model.Addrobj

/**
 *
 * @author Ushmodin N.
 * @since 31.08.2016 15:24
 */

interface  AddrobjRepository: JpaRepository<Addrobj, String>, JpaSpecificationExecutor<Addrobj>