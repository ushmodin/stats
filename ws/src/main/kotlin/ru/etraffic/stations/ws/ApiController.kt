package ru.etraffic.stations.ws

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.etraffic.stations.ws.model.*
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

/**
 * Created by nikolay on 26.08.16.
 */
@RestController
@RequestMapping("/api")
open class ApiController @Autowired constructor(val apiService: ApiService) {

    val log = LoggerFactory.getLogger(ApiController::class.java)

    @ExceptionHandler(Throwable::class)
    fun error(ex: Throwable): Response<Nothing> {
        log.error(null, ex)
        return Response.error("APP_ERROR", ex.message)
    }

    @RequestMapping("ping")
    fun ping(value: String) = value.success()

    @RequestMapping("reg")
    fun reg(@Valid @RequestBody req: Request<RegReq>, httpReq: HttpServletRequest): Response<Any> {
        return apiService.reg(req.data!!, httpReq.remoteAddr).success()
    }

    @RequestMapping("guid")
    fun guid(@RequestBody req: AuthRequest<GuidReq>): Response<Any> {
        return apiService.guid(req.host, req.data!!).success()
    }
}