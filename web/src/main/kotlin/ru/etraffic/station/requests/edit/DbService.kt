package ru.etraffic.station.requests.edit

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.etraffic.station.jpaContaints
import ru.etraffic.station.jpaStartWith
import ru.etraffic.stations.domain.AddrobjTypeRepository
import ru.etraffic.stations.domain.StationRepository
import ru.etraffic.stations.domain.StationRequestRepository
import ru.etraffic.stations.domain.model.Station
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

/**
 * Created by nikolay on 02.09.16.
 */
@Service("requestsEditDbService")
@Transactional
open class DbService @Autowired constructor(
        val stationRequestRepository: StationRequestRepository,
        val stationRepository: StationRepository) {
    open fun request(id: Long) = stationRequestRepository.getOne(id).let {
        StationRequestDto(
                id = it.id!!,
                name = it.name!!,
                regionName = it.regionName!!,
                okato = it.okato!!)
    }

    open fun stations(filter: StationFilter, pageable: Pageable)
            = stationRepository.findAll(StationSpecification(filter), pageable).map {
        StationDto(
                id= it.id!!,
                name = it.name?:"",
                type = it.type?:"",
                country = it.region?.country?.shortName?:"",
                region = it.region?.name?:"",
                area = it.area?.name?:"",
                city = it.city?.name?:"",
                place = it.place?.name?:"",
                okato = it.place?.okato?:""
                )
    }

    open fun link(data: LinkDto) {
        val request = stationRequestRepository.getOne(data.requestId)
        val station = stationRepository.getOne(data.stationId)
        request.station = station
        stationRequestRepository.save(request)
    }
}

class StationSpecification(val filter: StationFilter) : Specification<Station> {
    override fun toPredicate(root: Root<Station>?, query: CriteriaQuery<*>?, cb: CriteriaBuilder?)
        = cb!!.and(*listOf(filter.name?.let {
            cb!!.like(cb!!.lower(root!!.get<String>("name")), it.toLowerCase().jpaContaints())
        }, filter.type?.let {
            cb!!.like(cb!!.lower(root!!.get<String>("type")), it.toLowerCase().jpaContaints())
        }, filter.type?.let {
            val place = root!!.join<Any, Any>("place")
            cb!!.like(cb!!.lower(place!!.get<String>("okato")), it.toLowerCase().jpaStartWith())
        }, filter.countryId?.let {
            val region = root!!.join<Any, Any>("region")
            val country = region!!.join<Any, Any>("country")
            cb!!.equal(country!!.get<Long>("id"), it)
        }, filter.regionId?.let {
            val region = root!!.join<Any, Any>("region")
            cb!!.equal(region!!.get<Long>("id"), it)
        }, filter.areaId?.let {
            val area = root!!.join<Any, Any>("area")
            cb!!.equal(area!!.get<Long>("id"), it)
        }, filter.cityId?.let {
            val city = root!!.join<Any, Any>("city")
            cb!!.equal(city!!.get<Long>("id"), it)
        }, filter.placeId?.let {
            val place = root!!.join<Any, Any>("place")
            cb!!.equal(place!!.get<Long>("id"), it)
        }).filterNotNull().toTypedArray())
}
