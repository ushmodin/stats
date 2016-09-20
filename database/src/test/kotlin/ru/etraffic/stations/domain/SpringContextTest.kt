package ru.etraffic.stations.domain

import org.hibernate.search.jpa.Search
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.test.context.junit4.SpringRunner
import ru.etraffic.stations.Configuration
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

/**
 *
 * @author Ushmodin N.
 * @since 26.08.2016 16:50
 */

@RunWith(SpringRunner::class)
@SpringBootTest(classes = arrayOf(Configuration::class))
open class SpringContextTest {
    @PersistenceContext
    lateinit var em: EntityManager


    @Before
    fun before() {
        Search.getFullTextEntityManager(em).createIndexer().startAndWait();
    }

    @Test
    fun testStartUp() {
    }
}

