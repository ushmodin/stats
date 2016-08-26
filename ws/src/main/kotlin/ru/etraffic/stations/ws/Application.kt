package ru.etraffic.stations.ws

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * Created by nikolay on 27.08.16.
 */
@SpringBootApplication
open class Application {

}

fun main(args: Array<String>) {
    SpringApplication.run(arrayOf(Application::class.java), args)
}