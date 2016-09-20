package ru.etraffic.station

import org.hibernate.search.jpa.Search
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.web.support.SpringBootServletInitializer
import org.springframework.context.ApplicationListener
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.stereotype.Component
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

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


@Component
class SearchIndex: ApplicationListener<ApplicationReadyEvent> {
    @PersistenceContext
    lateinit var em: EntityManager

    override fun onApplicationEvent(event: ApplicationReadyEvent?) {
        Search.getFullTextEntityManager(em).createIndexer().start()
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(arrayOf(Application::class.java), args)
}