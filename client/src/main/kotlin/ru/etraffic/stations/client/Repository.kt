package ru.etraffic.stations.client

import org.springframework.data.jpa.repository.JpaRepository

/**
 *
 * @author Ushmodin N.
 * @since 29.08.2016 14:54
 */

interface StationRepository: JpaRepository<Station, Long> {
    fun findByGuidNull(): List<Station>

    fun findByIdIn(map: List<Long>): List<Station>
}

interface KladrRepository: JpaRepository<Kladr, String>

interface RegionRepository: JpaRepository<Region, Long>

interface HostRepository: JpaRepository<Host, Long> {
    fun findByState(a: State): Host
}