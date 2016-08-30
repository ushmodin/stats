package ru.etraffic.stations.ws

import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.support.SpringBootServletInitializer
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

/**
 * Created by nikolay on 27.08.16.
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = arrayOf("ru.etraffic.stations"))
@EntityScan(basePackages = arrayOf("ru.etraffic.stations"))
open class Application: SpringBootServletInitializer() {

    override fun configure(builder: SpringApplicationBuilder?): SpringApplicationBuilder? {
        return builder!!.sources(Application::class.java)
    }

    @Bean
    open fun objectMapperBuilder(): Jackson2ObjectMapperBuilder
            = Jackson2ObjectMapperBuilder().modulesToInstall(KotlinModule())
}

fun main(args: Array<String>) {
    SpringApplication.run(arrayOf(Application::class.java), args)
}