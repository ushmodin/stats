package ru.etraffic.stations.domain

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.test.context.junit4.SpringRunner

/**
 *
 * @author Ushmodin N.
 * @since 26.08.2016 16:50
 */

@RunWith(SpringRunner::class)
@SpringBootTest(classes = arrayOf(ru.etraffic.stations.domain.Configuration::class))
open class SpringContextTest {

    @Test
    fun testStartUp() {
    }
}

@SpringBootApplication
@EnableJpaRepositories
open class Configuration {

}