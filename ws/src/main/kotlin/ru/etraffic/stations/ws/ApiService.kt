package ru.etraffic.stations.ws

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.etraffic.stations.domain.AvsHostRepository
import ru.etraffic.stations.domain.StationRequestRepository
import ru.etraffic.stations.domain.model.AvsHost
import ru.etraffic.stations.domain.model.StationRequest
import ru.etraffic.stations.ws.model.*
import java.util.*

/**
 * Created by nikolay on 28.08.16.
 */
@Service
@Transactional
open class ApiService @Autowired constructor(
        private val avsHostRepository: AvsHostRepository,
        private val stationRequestRepository: StationRequestRepository
) {

    open fun reg(regData: RegReq, remoteAddr: String): RegRsp {
        val host = AvsHost(
                name = regData.name
                , inn = regData.inn
                , ip = remoteAddr
                , guid = UUID.randomUUID().toString())
        val guid = avsHostRepository.save(host).guid!!
        return RegRsp(hostGuid = guid)
    }

    open fun guid(hostGuid: String, data: GuidReq): GuidRsp {
        if (data.stations.size > 100) {
            throw AppException(AppErrorCode.TO_MANY_DATA, "To many stations")
        }

        val host = getHost(hostGuid)

        val extIds = data.stations.map { it.hostId!! }

        val registered = stationRequestRepository.findByOwnerAndHostIdIn(host, extIds)

        stationRequestRepository.save(data.stations
                .filterNot {newStation-> registered.any {readyStation-> readyStation.hostId.equals(newStation.hostId)  } }
                .map {StationRequest(
                            owner = host
                            , hostId = it.hostId
                            , name = it.name
                            , description = it.description
                            , okato = it.okato
                            , countryGuid = it.countryGuid
                            , countryName = it.countryName
                            , regionName = it.regionName
                            , regionGuid = it.regionGuid
                            , areaGuid = it.areaGuid
                            , areaName = it.areaName
                            , cityGuid = it.cityGuid
                            , cityName = it.cityName
                            , latitude = it.latitude
                            , longitude = it.longitude
                    )
                }
        )

        return GuidRsp(stations = registered.filter { it.station != null }.map { StationDto(
                guid = it.station!!.guid
                ,hostId = it.hostId
                ,name = it.station!!.name
                ,countryGuid = it.station!!.region?.country?.id?.toString()
                ,countryName = it.station!!.region?.country?.longName
                ,regionName = it.station!!.region?.name
                ,regionGuid = it.station!!.region?.guid
                ,areaGuid = it.station!!.area?.guid
                ,areaName = it.station!!.area?.name
                ,cityGuid = it.station!!.city?.guid
                ,cityName = it.station!!.city?.name
                ,description = it.description
                ,okato = it.station!!.place?.okato
                ,latitude = it.station!!.latitude
                ,longitude = it.station!!.longitude
        ) })
    }

    open fun getHost(host: String) = avsHostRepository
                .findByGuid(host)
                .orElseThrow { AppException(AppErrorCode.UNKNOWN_HOST, "Unknown host $host") }
}