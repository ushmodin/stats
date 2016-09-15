package ru.etraffic.stations.client

import org.slf4j.LoggerFactory
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
    private val log = LoggerFactory.getLogger(DbService::class.java)

    open fun getHost() = hostRepository.findByState(State.A)

    open fun saveHost(host: Host) = hostRepository.save(host)

    open fun getNotLinkedStation() = stationRepository.findByGuidNull().map {
        val fullRegionInfo = it.region?.fullInfo()

        StationDto(
                hostId = it.id!!.toString()
                ,name = it.name
                ,description = it.description
                ,okato = it.kladr?.okato
                ,latitude = it.latitude
                ,longitude = it.longitude
                ,countryGuid = fullRegionInfo?.country?.guid
                ,countryName = fullRegionInfo?.country?.name
                ,regionName = fullRegionInfo?.region?.name
                ,regionGuid = fullRegionInfo?.region?.guid
                ,areaGuid = fullRegionInfo?.area?.guid
                ,areaName = fullRegionInfo?.area?.name
                ,cityGuid = fullRegionInfo?.city?.guid
                ,cityName = fullRegionInfo?.city?.name
        )
    }

    open fun updateStation(stations: List<StationDto>) {
        val guidMap = stations.map { it.hostId!!.toLong() to it }.toMap()
        val localStations = stationRepository.findByIdIn(guidMap.keys.toList())
        localStations.forEach {
            val stationDto = guidMap[it.id]!!
            it.guid = stationDto.guid
            val fullRegionInfo = it.region?.fullInfo()
            if (fullRegionInfo != null) {
                if (fullRegionInfo.city != null
                        && fullRegionInfo.city.guid == null
                        && stationDto.cityGuid != null) {
                    fullRegionInfo.city.guid = stationDto.cityGuid
                    regionRepository.save(fullRegionInfo.city)
                    fullRegionInfo.city.apply {
                        log.debug("Update region {}:{} with guid {}", id, name, guid)
                    }
                }
                if (fullRegionInfo.area != null
                        && fullRegionInfo.area.guid == null
                        && stationDto.areaGuid != null) {
                    fullRegionInfo.area.guid = stationDto.areaGuid
                    regionRepository.save(fullRegionInfo.area)
                    fullRegionInfo.area.apply {
                        log.debug("Update region {}:{} with guid {}", id, name, guid)
                    }
                }
                if (fullRegionInfo.region != null
                        && fullRegionInfo.region.guid == null
                        && stationDto.regionGuid != null) {
                    fullRegionInfo.region.guid = stationDto.regionGuid
                    regionRepository.save(fullRegionInfo.region)
                    fullRegionInfo.region.apply {
                        log.debug("Update region {}:{} with guid {}", id, name, guid)
                    }
                }
                if (fullRegionInfo.country != null
                        && fullRegionInfo.country.guid == null
                        && stationDto.countryGuid != null) {
                    fullRegionInfo.country.guid = stationDto.countryGuid
                    regionRepository.save(fullRegionInfo.country)
                    fullRegionInfo.country.apply {
                        log.debug("Update region {}:{} with guid {}", id, name, guid)
                    }
                }
            }


        }
        stationRepository.save(localStations)
    }
}