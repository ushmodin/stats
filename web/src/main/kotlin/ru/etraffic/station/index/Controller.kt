package ru.etraffic.station.index

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 *
 * @author Ushmodin N.
 * @since 31.08.2016 09:14
 */

@Controller("indexController")
class Controller {
    @RequestMapping("/")
    fun index() = "index"
}