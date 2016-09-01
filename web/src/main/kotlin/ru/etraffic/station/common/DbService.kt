package ru.etraffic.station.common

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.etraffic.stations.domain.*
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
        val stationRepository: StationRepository) {
    open fun hosts() = hostRepository.findAll().map {
        HostDto(id = it.id!!, name = it.name!!, inn = it.inn!!)
    }

    open fun countries() = countryRepository.findAll().map {
        CountryDto(id = it.id!!, name = it.longName!!)
    }

    open fun regions(countryId: Optional<Long>) = regionRepository.findByCountryId(countryId).map {
        RegionDto(id = it.id!!, guid = it.guid!!, name = it.name!!)
    }

    open fun areas(regionId: Optional<Long>) = areaRepository.findByRegionId(regionId).map {
        AreaDto(id = it.id!!, guid = it.guid!!, name = it.name!!)
    }

    open fun places(areaId: Optional<Long>, regionId: Optional<Long>, cityId: Optional<Long>) = placeRepository.findByAreaIdAndRegionId(areaId, regionId, cityId).map {
        PlaceDto(id = it.id!!, guid = it.guid!!, name = it.name!!)
    }

    open fun stations(areaId: Optional<Long>,
                      regionId: Optional<Long>,
                      cityId: Optional<Long>,
                      placeId: Optional<Long>) = stationRepository.findByAreaIdAndRegionIdAndPlaceId(regionId, areaId, cityId, placeId).map {
        StationDto(id = it.id!!, guid = it.guid!!, name = it.name!!)
    }
}