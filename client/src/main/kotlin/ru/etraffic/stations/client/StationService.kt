package ru.etraffic.stations.client

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import javax.transaction.Transactional

/**
 *
 * @author Ushmodin N.
 * @since 30.08.2016 09:41
 */

@Service
class StationService @Autowired constructor(@Value("\${station.server}") val server: String,
                                            val restTemplate: RestTemplate,
                                            val dbService: DbService
                                            ) {
    class RegRspType: Response<RegRsp>()
    class GuidRspType: Response<GuidRsp>()
    class PingRspType: Response<Any>()

    fun reg(host: Host): Host {
        val rsp = restTemplate.postForObject("$server/api/reg", Request(data = RegReq(name = host.name ?: "", inn = host.inn ?: "")), RegRspType::class.java)
        host.guid = rsp.data!!.hostGuid
        return dbService.saveHost(host)
    }

    fun ping(host: Host) = restTemplate.getForEntity("$server/api/ping?value=test&host=${host.guid}", PingRspType::class.java)

    fun syncGuids() {
        var host = dbService.getHost()
        if (host.guid == null) {
            host = reg(host)
        }
        val ping = ping(host)
        if (!ping.body.success && ping.body.error?.code == "UNKNOWN_HOST") {
            host = reg(host)
        }

        dbService
                .getNotLinkedStation()
                .split(100)
                .forEach {stations->
            val rsp = restTemplate.postForObject("$server/api/guid", AuthRequest(host = host.guid!!, data = GuidReq(stations = stations)), GuidRspType::class.java)
            dbService.updateStation(rsp.data!!.stations)
        }
    }
}

@Service
@Transactional
open class DbService @Autowired constructor(val stationRepository: StationRepository,
                                            val hostRepository: HostRepository,
                                            val regionRepository: RegionRepository) {

    open fun getHost() = hostRepository.findByState(State.A)

    open fun saveHost(host: Host) = hostRepository.save(host)

    open fun getNotLinkedStation() = stationRepository.findByGuidNull().map { StationDto(
                id = it.id!!.toString()
                ,name = it.name
                ,description = it.description
                ,okato = it.kladr?.okato
                ,latitude = it.latitude
                ,longitude = it.longitude
                ,regionName = it.region?.name
                ,regionGuid = it.region?.guid
        )}

    open fun updateStation(stations: List<StationDto>) {
        val guidMap = stations.map { it.id!!.toLong() to it.guid }.toMap()
        val localStations = stationRepository.findByIdIn(stations.map { it.id!!.toLong() })
        localStations.forEach {
            it.guid = guidMap[it.id]
        }
        stationRepository.save(localStations)
    }
}