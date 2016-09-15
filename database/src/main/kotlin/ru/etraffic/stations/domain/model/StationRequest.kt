package ru.etraffic.stations.domain.model

import java.math.BigDecimal
import java.util.*
import javax.persistence.*

/**
 * Created by nikolay on 28.08.16.
 */
@Entity
@Table(name = "st_reqs",
        uniqueConstraints = arrayOf(UniqueConstraint(columnNames = arrayOf("owner_host_id", "host_id"))))
data class StationRequest(
        @Id
        @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
        var id: Long? = null,
        @ManyToOne(fetch = javax.persistence.FetchType.LAZY, optional = false)
        @JoinColumn(name = "owner_host_id", nullable = false)
        var owner: AvsHost? = null,
        @ManyToOne(fetch = javax.persistence.FetchType.LAZY)
        @JoinColumn(name = "station_id")
        var station: Station? = null,
        @Column(name = "host_id", nullable = false)
        var hostId: String? = null,
        @Column(nullable = false)
        var name: String? = null,
        var description: String? = null,
        var okato: String? = null,
        @Column(name = "country_guid", length = 36)
        var countryGuid: String? = null,
        @Column(name = "country_name")
        var countryName: String? = null,
        @Column(name = "region_guid", length = 36)
        var regionGuid: String? = null,
        @Column(name = "region_name")
        var regionName: String? = null,
        @Column(name = "area_guid", length = 36)
        var areaGuid: String? = null,
        @Column(name = "area_name")
        var areaName: String? = null,
        @Column(name = "city_guid", length = 36)
        var cityGuid: String? = null,
        @Column(name = "city_name")
        var cityName: String? = null,
        var latitude: BigDecimal? = null,
        var longitude: BigDecimal? = null,
        var created: Date? = Date()
)