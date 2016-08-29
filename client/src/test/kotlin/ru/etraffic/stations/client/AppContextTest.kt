package ru.etraffic.stations.client

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

/**
 *
 * @author Ushmodin N.
 * @since 29.08.2016 15:30
 */

@RunWith(SpringJUnit4ClassRunner::class)
@ContextConfiguration(classes = arrayOf(AppConfig::class))
class AppContextTest {
    @Test
    fun testStartUp() {

    }
}