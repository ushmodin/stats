package ru.etraffic.stations.ws

import org.hibernate.engine.jdbc.batch.spi.Batch
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.etraffic.stations.domain.AvsHostRepository
import ru.etraffic.stations.domain.StationBatchRepository
import ru.etraffic.stations.domain.StationItemRepository
import ru.etraffic.stations.domain.model.AvsHost
import ru.etraffic.stations.domain.model.Station
import ru.etraffic.stations.domain.model.StationBatch
import ru.etraffic.stations.domain.model.StationItem
import ru.etraffic.stations.ws.model.*
import java.util.*

/**
 * Created by nikolay on 28.08.16.
 */
@Service
@Transactional
open class ApiService @Autowired constructor(
        private val avsHostRepository: AvsHostRepository,
        private val stationBatchRepository: StationBatchRepository,
        private val stationItemRepository: StationItemRepository
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

    fun postBatch(hostGuid: String, data: PostBatchReq): PostBatchRsp {
        val host = avsHostRepository.findByGuid(hostGuid)
        val batch = stationBatchRepository.save(StationBatch(host = host, guid = UUID.randomUUID().toString()))

        stationItemRepository.save(data.stations!!.map {
            StationItem(
                    hostId = it.id
                    , batch = batch
                    , name = it.name
                    , description = it.description
                    , okato = it.okato
                    , regionName = it.regionName
                    , regionGuid = it.regionGuid
                    , latitude = it.latitude
                    , longitude = it.longitude
            )
        })

        return PostBatchRsp(batchGuid = batch.guid)
    }

    fun getBatch(host: String, data: GetBatchReq): GetBatchRsp {
        val batch = stationBatchRepository.findByGuid(data.batchId!!)
        val stations = stationItemRepository.findByBatchAndStationNotNull(batch).map {
            StationDto(
                    guid = it.station!!.guid
                    ,id = it.hostId
                    ,name = it.name
                    ,regionName = it.regionName
                    ,regionGuid = it.station!!.region!!.guid
                    ,description = it.description
                    ,okato = it.station!!.place?.okato
                    ,latitude = it.station!!.latitude
                    ,longitude = it.station!!.longitude)
        }
        return GetBatchRsp(stations = stations)
    }
}