package ru.etraffic.station.requests

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.etraffic.station.success

/**
 * Created by nikolay on 01.09.16.
 */
@RestController("requestsController")
@RequestMapping("/api/requests")
class Controller @Autowired constructor(val dbService: DbService) {
    @RequestMapping("list")
    fun list(@RequestBody filter: RequestFilter, page: Pageable) = dbService.requests(filter, page).success();
}