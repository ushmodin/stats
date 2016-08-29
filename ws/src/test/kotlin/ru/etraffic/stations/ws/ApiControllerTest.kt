package ru.etraffic.stations.ws

import org.apache.commons.lang3.RandomStringUtils
import org.apache.commons.lang3.RandomUtils
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.data.domain.PageRequest
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional
import ru.etraffic.stations.domain.AvsHostRepository
import ru.etraffic.stations.domain.StationRepository
import ru.etraffic.stations.domain.StationRequestRepository
import ru.etraffic.stations.ws.model.*
import java.math.BigDecimal
import java.util.*

/**
 * Created by nikolay on 28.08.16.
 */
@RunWith(SpringRunner::class)
@SpringBootTest(classes = arrayOf(Application::class), webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
open class ApiControllerTest {

    @Autowired lateinit var rest: TestRestTemplate
    @Autowired lateinit var stationRequestRepository: StationRequestRepository
    @Autowired lateinit var stationRepository: StationRepository
    @Autowired lateinit var avsHostRepository: AvsHostRepository


    class PingRspType : Response<String>()
    class RegRspType : Response<RegRsp>()
    class GuidRspType : Response<GuidRsp>()

    @Test
    fun ping() {
        val value = RandomStringUtils.random(30)
        val rsp = rest.getForObject("/api/ping?value={value}", PingRspType::class.java, mapOf("value" to value))
        Assert.assertTrue(rsp.success)
        Assert.assertEquals(rsp.data, value)
    }



    @Test
    fun reg() {
        val req = Request(data = RegReq(name = RandomStringUtils.random(30), inn = RandomStringUtils.randomNumeric(12)))
        val rsp = rest.postForObject("/api/reg", req, RegRspType::class.java)
        Assert.assertTrue(rsp.success)
        Assert.assertNotNull(rsp.data!!.hostGuid)
    }

    @Test
    fun guid() {
        val auth = rest.postForObject("/api/reg", Request(data = RegReq(name = RandomStringUtils.random(30), inn = RandomStringUtils.randomNumeric(12))), RegRspType::class.java)
        Assert.assertTrue(auth.success)
        Assert.assertNotNull(auth.data!!.hostGuid)


        val req = AuthRequest(host = auth.data.hostGuid, data = GuidReq(stations = (1..10).map { StationDto(
                name = RandomStringUtils.randomAlphabetic(10)
                ,description = RandomStringUtils.randomAlphanumeric(10)
                ,id = UUID.randomUUID().toString()
                ,okato = RandomStringUtils.randomNumeric(11)
                ,regionName = RandomStringUtils.randomAlphabetic(10)
                ,latitude = BigDecimal.valueOf(RandomUtils.nextDouble(0.0, 360.0))
                ,longitude = BigDecimal.valueOf(RandomUtils.nextDouble(0.0, 360.0))
        ) }))
        var rsp = rest.postForObject("/api/guid", req, GuidRspType::class.java)
        Assert.assertTrue(rsp.success)
        Assert.assertTrue(rsp.data!!.stations.isEmpty())

        rsp = rest.postForObject("/api/guid", req, GuidRspType::class.java)
        Assert.assertTrue(rsp.success)
        Assert.assertTrue(rsp.data!!.stations.isEmpty())
    }

    @Test
    fun guidWithStation() {
        val auth = rest.postForObject("/api/reg", Request(data = RegReq(name = RandomStringUtils.random(30), inn = RandomStringUtils.randomNumeric(12))), RegRspType::class.java)
        Assert.assertTrue(auth.success)
        Assert.assertNotNull(auth.data!!.hostGuid)


        val req = AuthRequest(host = auth.data.hostGuid, data = GuidReq(stations = (1..10).map { StationDto(
                name = RandomStringUtils.randomAlphabetic(10)
                ,description = RandomStringUtils.randomAlphanumeric(10)
                ,id = UUID.randomUUID().toString()
                ,okato = RandomStringUtils.randomNumeric(11)
                ,regionName = RandomStringUtils.randomAlphabetic(10)
                ,latitude = BigDecimal.valueOf(RandomUtils.nextDouble(0.0, 360.0))
                ,longitude = BigDecimal.valueOf(RandomUtils.nextDouble(0.0, 360.0))
        ) }))
        var rsp = rest.postForObject("/api/guid", req, GuidRspType::class.java)
        Assert.assertTrue(rsp.success)
        Assert.assertTrue(rsp.data!!.stations.isEmpty())

        val host = avsHostRepository.findByGuid(auth.data.hostGuid)
        val stationRequests = stationRequestRepository.findByHostAndExtIdIn(host, listOf(req.data!!.stations[0].id!!))
        stationRequests[0].station = stationRepository.findAll(PageRequest(1, 1)).content[0]
        stationRequestRepository.save(stationRequests[0])

        rsp = rest.postForObject("/api/guid", req, GuidRspType::class.java)
        Assert.assertTrue(rsp.success)
        Assert.assertTrue(rsp.data!!.stations.size == 1)
    }

    @Test
    fun error() {
        val req = AuthRequest(host = UUID.randomUUID().toString(), data = listOf<StationDto>())
        var rsp = rest.postForObject("/api/guid", req, GuidRspType::class.java)
        Assert.assertFalse(rsp.success)
        Assert.assertNull(rsp.data)
        Assert.assertNotNull(rsp.error)
        Assert.assertNotNull(rsp.error?.message)
        Assert.assertNotNull(rsp.error?.code)
    }
}