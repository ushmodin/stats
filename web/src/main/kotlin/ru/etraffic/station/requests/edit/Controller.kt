package ru.etraffic.station.requests.edit

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.etraffic.station.success

/**
 * Created by nikolay on 02.09.16.
 */
@RestController("requestEditController")
@RequestMapping("/api/request")
class Controller @Autowired constructor(val dbService: DbService) {
    @RequestMapping("/{id}")
    fun request(@PathVariable id: Long) = dbService.request(id).success()
}