package ru.etraffic.station.requests.edit

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.etraffic.stations.domain.StationRequestRepository

/**
 * Created by nikolay on 02.09.16.
 */
@Service("requestsEditDbService")
@Transactional
open class DbService @Autowired constructor(val stationRequestRepository: StationRequestRepository) {
    open fun request(id: Long) = stationRequestRepository.getOne(id).let {
        StationRequestDto(
                id = it.id!!,
                name = it.name!!,
                regionName = it.regionName!!,
                okato = it.okato!!)
    }
}