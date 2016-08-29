package ru.etraffic.stations.ws

import org.apache.commons.lang3.RandomStringUtils
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional
import ru.etraffic.stations.ws.model.RegReq
import ru.etraffic.stations.ws.model.RegRsp
import ru.etraffic.stations.ws.model.Request
import ru.etraffic.stations.ws.model.Response
import java.util.*

/**
 * Created by nikolay on 28.08.16.
 */
@RunWith(SpringRunner::class)
@SpringBootTest(classes = arrayOf(Application::class), webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
open class ApiControllerTest {

    @Autowired lateinit var rest: TestRestTemplate


    class PingRspType : Response<String>()
    class RegRspType : Response<RegRsp>()

    @Test
    fun ping() {
        val value = RandomStringUtils.random(30)
        val rsp = rest.getForObject("/api/ping?value={value}", PingRspType::class.java, mapOf("value" to value))
        Assert.assertTrue(rsp.success)
        Assert.assertEquals(rsp.data, value)
    }



    @Test
    @Rollback(true)
    fun reg() {
        val req = Request(data = RegReq(name = RandomStringUtils.random(30), inn = RandomStringUtils.randomNumeric(12)))
        val rsp = rest.postForObject("/api/reg", req, RegRspType::class.java)
        Assert.assertTrue(rsp.success)
        Assert.assertNotNull(rsp.data!!.hostGuid)
    }

}