package ru.etraffic.station.requests.edit

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.etraffic.station.common.toDto
import ru.etraffic.station.jpaContaints
import ru.etraffic.station.jpaStartWith
import ru.etraffic.stations.domain.*
import ru.etraffic.stations.domain.model.Station
import java.util.*
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
        val stationRepository: StationRepository,
        val placeRepository: PlaceRepository,
        val areaRepository: AreaRepository) {
    open fun request(id: Long) = stationRequestRepository.getOne(id).let {
        StationRequestDto(
                id = it.id!!,
                name = it.name!!,
                countryName = it.countryName?:null,
                regionName = it.regionName?:null,
                areaName = it.areaName?:null,
                cityName = it.cityName?:null,
                okato = it.okato?:null)
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

    open fun newStation(data: NewStationDto) {
        val place = data.placeId?.let { placeRepository.getOne(it) }
        val city =
                if (place != null && place.city != null) place.city
                else data.cityId?.let { placeRepository.getOne(it) }
        val area =
                if (city != null && city.area != null) city.area
                else if (place != null && place.area != null) place.area
                else data.areaId?.let { areaRepository.getOne(it) }
        var newStation = Station(
                name = data.name
                ,type = data.type
                ,guid = UUID.randomUUID().toString()
                ,place = place
                ,city = city
                ,area = area)
        newStation = stationRepository.save(newStation)

        val request = stationRequestRepository.getOne(data.requestId)
        request.station = newStation
        stationRequestRepository.save(request)
    }

    open fun searchLike(id: Long, pageable: Pageable): Page<StationDto> {
        val request = stationRequestRepository.getOne(id)
        return stationRepository.searchLike(
                name = request.name
                , okato = request.okato
                , countryName = request.countryName
                , countryGuid = request.countryGuid
                , regionName = request.regionName
                , regionGuid = request.regionName
                , areaName = request.areaName
                , areaGuid = request.areaGuid
                , cityName = request.cityName
                , cityGuid = request.cityGuid
                , page = pageable
        ).map {
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
