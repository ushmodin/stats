package ru.etraffic.stations.client

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 *
 * @author Ushmodin N.
 * @since 30.08.2016 12:23
 */
@RestController
@RequestMapping("/api")
class ApiController @Autowired constructor(val stationService: StationService) {
    val log = LoggerFactory.getLogger(ApiController::class.java)

    @RequestMapping("/ping")
    fun ping(@RequestParam value: String) = Response(success = true, data = value)

    @RequestMapping("/sync")
    fun sync(): Response<Any> {
        stationService.syncGuids()
        return Response(data = "OK")
    }

    @ExceptionHandler(Throwable::class)
    fun error(ex: Throwable): Response<Any> {
        log.error(null, ex)
        return Response<Any>(success = false, error = Error(code = "ERROR", message = ex.message))
    }
}