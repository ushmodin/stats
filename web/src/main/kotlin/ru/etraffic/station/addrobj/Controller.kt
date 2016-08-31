package ru.etraffic.station.addrobj

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.etraffic.station.success

/**
 *
 * @author Ushmodin N.
 * @since 31.08.2016 15:04
 */

@RestController("addrobjController")
@RequestMapping("/api/addrobj")
class Controller @Autowired constructor(val dbService: DbService) {
    @RequestMapping("list")
    fun list(@RequestBody filter: AddrobjFilter, page: Pageable) = dbService.list(filter, page).success()
}