package ru.etraffic.stations.ws

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.etraffic.stations.ws.model.*
import javax.servlet.http.HttpServletRequest

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
        return Response.error(ex.javaClass.simpleName, ex.message)
    }

    @RequestMapping("ping")
    fun ping(value: String) = value.success()

    @RequestMapping("reg")
    fun reg(@RequestBody req: Request<RegReq>, httpReq: HttpServletRequest)
            = apiService.reg(req.data!!, httpReq.remoteAddr).success()

    @RequestMapping("batch")
    fun postBatch(@RequestBody req: AuthRequest<PostBatchReq>)
        = apiService.postBatch(req.host!!, req.data!!).success()

    @RequestMapping("batch")
    fun getBatch(@RequestBody req: AuthRequest<GetBatchReq>)
        = apiService.getBatch(req.host!!, req.data!!).success()
}