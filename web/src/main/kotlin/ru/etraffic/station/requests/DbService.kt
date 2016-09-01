package ru.etraffic.station.requests

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.etraffic.stations.domain.AvsHostRepository
import ru.etraffic.stations.domain.StationRequestRepository
import ru.etraffic.stations.domain.model.StationRequest
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

/**
 * Created by nikolay on 01.09.16.
 */
@Service
@Transactional
open class DbService @Autowired constructor(val stationRequestRepository: StationRequestRepository) {
    open fun requests(filter: RequestFilter, page: Pageable) = stationRequestRepository.findAll(RequestsSpecification(filter), page).map {
        RequestDto(
                id = it.id,
                country = it.station?.region?.country?.longName,
                region = it.station?.region?.name,
                area = it.station?.area?.name,
                place = it.station?.place?.name,
                station = it.station?.name,
                name = it.name,
                regionName = it.regionName,
                okato = it.okato,
                longitude = it.longitude,
                latitude = it.latitude
        )
    }
}

class RequestsSpecification(val filter: RequestFilter) : Specification<StationRequest> {
    override fun toPredicate(root: Root<StationRequest>?, query: CriteriaQuery<*>?, cb: CriteriaBuilder?): Predicate? =
            cb!!.and(*listOf(filter.hostId?.let {
                val host = root!!.join<Any, Any>("host")
                return cb!!.equal(host!!.get<Long>("id")!!, it)
            }, filter.name?.let {
                cb!!.like(cb!!.lower(root!!.get<String>("name")), "%${it.toLowerCase()}%")
            }).filterNotNull().toTypedArray())
}
