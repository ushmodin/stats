package ru.etraffic.station.common

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.etraffic.station.success
import java.util.*

/**
 * Created by nikolay on 01.09.16.
 */
@RestController("commonController")
@RequestMapping("/api/common")
class Controller @Autowired constructor(val dbService: DbService) {
    @RequestMapping("hosts")
    fun hosts() = dbService.hosts().success()

    @RequestMapping("countries")
    fun countries() = dbService.countries().success()

    @RequestMapping("regions")
    fun regions(countryId: Optional<Long>, name: Optional<String>, pageable: Pageable) = dbService.regions(countryId, name, pageable).success()

    @RequestMapping("areas")
    fun areas(regionId: Optional<Long>,
              name: Optional<String>,
              pageable: Pageable) = dbService.areas(regionId, name, pageable).success()

    @RequestMapping("places")
    fun places(regionId: Optional<Long>,
               areaId: Optional<Long>,
               cityId: Optional<Long>,
               name: Optional<String>,
               pageable: Pageable)
            = dbService.places(regionId, areaId, cityId, name, pageable).success()

    @RequestMapping("stations")
    fun stations(regionId: Optional<Long>,
                 areaId: Optional<Long>,
                 cityId: Optional<Long>,
                 placeId: Optional<Long>,
                 name: Optional<String>,
                 pageable: Pageable)
            = dbService.stations(areaId, regionId, cityId, placeId, name, pageable).success()


    @RequestMapping("populatedLocalityTypes")
    fun populatedLocalityTypes()
            = dbService.getPopulatedLocalityTypes().success()
}