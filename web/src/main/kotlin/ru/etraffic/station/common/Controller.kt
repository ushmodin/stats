package ru.etraffic.station.common

import org.springframework.beans.factory.annotation.Autowired
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
    fun regions(countryId: Optional<Long>) = dbService.regions(countryId).success()

    @RequestMapping("areas")
    fun areas(regionId: Optional<Long>) = dbService.areas(regionId).success()

    @RequestMapping("places")
    fun places(regionId: Optional<Long>, areaId: Optional<Long>, cityId: Optional<Long>) = dbService.places(regionId, areaId, cityId).success()

    @RequestMapping("stations")
    fun stations(regionId: Optional<Long>, areaId: Optional<Long>, cityId: Optional<Long>, placeId: Optional<Long>) = dbService.stations(areaId, regionId, cityId, placeId).success()
}