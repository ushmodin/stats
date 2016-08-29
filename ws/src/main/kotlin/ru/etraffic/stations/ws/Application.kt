package ru.etraffic.stations.ws

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

/**
 * Created by nikolay on 27.08.16.
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = arrayOf("ru.etraffic.stations"))
@EntityScan(basePackages = arrayOf("ru.etraffic.stations"))
open class Application {
}

fun main(args: Array<String>) {
    SpringApplication.run(arrayOf(Application::class.java), args)
}