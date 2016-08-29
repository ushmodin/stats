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
            throw IllegalArgumentException("to many stations")
        }

        val host = avsHostRepository.findByGuid(hostGuid)
        val extIds = data.stations.map { it.id!! }

        val registered = stationRequestRepository.findByHostAndExtIdIn(host, extIds)

        stationRequestRepository.save(data.stations
                .filterNot {newStation-> registered.any {readyStation-> readyStation.extId!!.equals(newStation.id)  } }
                .map {StationRequest(
                            host = host
                            , extId = it.id
                            , name = it.name
                            , description = it.description
                            , okato = it.okato
                            , regionName = it.regionName
                            , regionGuid = it.regionGuid
                            , latitude = it.latitude
                            , longitude = it.longitude
                    )
                }
        )

        return GuidRsp(stations = registered.filter { it.station != null }.map { StationDto(
                guid = it.station!!.guid
                ,id = it.extId
                ,name = it.station!!.name
                ,regionName = it.station!!.region!!.name
                ,regionGuid = it.station!!.region!!.guid
                ,description = it.description
                ,okato = it.station!!.place?.okato
                ,latitude = it.station!!.latitude
                ,longitude = it.station!!.longitude
        ) })
    }
}