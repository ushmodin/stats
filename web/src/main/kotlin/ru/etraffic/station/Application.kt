package ru.etraffic.station

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.support.SpringBootServletInitializer
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

/**
 *
 * @author Ushmodin N.
 * @since 31.08.2016 09:17
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = arrayOf("ru.etraffic.stations"))
@EntityScan(basePackages = arrayOf("ru.etraffic.stations"))
open class Application: SpringBootServletInitializer() {

    override fun configure(builder: SpringApplicationBuilder?): SpringApplicationBuilder? {
        return builder!!.sources(Application::class.java)
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(arrayOf(Application::class.java), args)
}