package ru.etraffic.station.requests.edit

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.etraffic.station.Response
import ru.etraffic.station.success

/**
 * Created by nikolay on 02.09.16.
 */
@RestController("requestEditController")
@RequestMapping("/api/request")
class Controller @Autowired constructor(val dbService: DbService) {
    @RequestMapping("/{id}")
    fun request(@PathVariable id: Long) = dbService.request(id).success()

    @RequestMapping("stations")
    fun stations(@RequestBody filter: StationFilter, pageable: Pageable)
        = dbService.stations(filter, pageable).success()

    @RequestMapping("link")
    fun link(@RequestBody data: LinkDto): Response<Any> {
        dbService.link(data)
        return "OK".success()
    }

    @RequestMapping("newStation")
    fun newStation(@RequestBody data: NewStationDto): Response<Any> {
        dbService.newStation(data)
        return "OK".success()
    }
}