package ru.etraffic.stations.domain

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import ru.etraffic.stations.domain.model.Station
import java.util.*

/**
 *
 * @author Ushmodin N.
 * @since 20.09.2016 11:29
 */

interface StationFullTextRepository {
    open fun searchLike(
            name: String?
            , okato: String?
            , countryName: String?
            , countryGuid: String?
            , regionName: String?
            , regionGuid: String?
            , areaName: String?
            , areaGuid: String?
            , cityName: String?
            , cityGuid: String?
            , page: Pageable): Page<Station>
}