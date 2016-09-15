package ru.etraffic.station.common

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.etraffic.station.jpaContaints
import ru.etraffic.stations.domain.*
import ru.etraffic.stations.domain.model.EntityStatus
import java.util.*

/**
 * Created by nikolay on 01.09.16.
 */
@Service("commonDbService")
@Transactional
open class DbService @Autowired constructor(
        val hostRepository: AvsHostRepository,
        val countryRepository: CountryRepository,
        val regionRepository: RegionRepository,
        val areaRepository: AreaRepository,
        val placeRepository: PlaceRepository,
        val stationRepository: StationRepository,
        val addrobjTypeRepository: AddrobjTypeRepository) {

    open fun hosts() = hostRepository.findAll().map {
        it.toDto()
    }

    open fun countries() = countryRepository.findByStatus(EntityStatus.A).map {
        it.toDto()
    }

    open fun regions(countryId: Optional<Long>, name: Optional<String>, pageable: Pageable)
            = regionRepository.findByCountryIdAndStatus(countryId, EntityStatus.A, name.map { it.toLowerCase().jpaContaints() }, pageable).map {
            it.toDto()
    }

    open fun areas(regionId: Optional<Long>, name: Optional<String>, pageable: Pageable)
            = areaRepository.findByRegionIdAndStatus(regionId,EntityStatus.A, name.map { it.toLowerCase().jpaContaints() },pageable).map {
        it.toDto()
    }

    open fun places(areaId: Optional<Long>,
                    regionId: Optional<Long>,
                    cityId: Optional<Long>,
                    name: Optional<String>,
                    pageable: Pageable)
            = placeRepository.findByAreaIdAndRegionIdAndStatus(areaId, regionId, cityId, EntityStatus.A, name.map { it.toLowerCase().jpaContaints() }, pageable).map {
        it.toDto()
    }

    open fun stations(areaId: Optional<Long>,
                      regionId: Optional<Long>,
                      cityId: Optional<Long>,
                      placeId: Optional<Long>,
                      name: Optional<String>,
                      pageable: Pageable)
            = stationRepository.findByAreaIdAndRegionIdAndPlaceIdAndStatus(regionId, areaId, cityId, placeId, EntityStatus.A, name.map { it.toLowerCase().jpaContaints() }, pageable).map {
        it.toDto()
    }


    open fun getPopulatedLocalityTypes() = addrobjTypeRepository.findPopulatedLocalityTypes()
            .distinctBy { it.shortName }
            .map { AddrObjTypeDto(name = it.name!!, shortName = it.shortName!!)}
}