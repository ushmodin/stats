package ru.etraffic.stations.domain.model

import java.math.BigDecimal
import java.util.*
import javax.persistence.*

/**
 * Created by nikolay on 28.08.16.
 */
@Entity
@Table(name = "st_reqs", uniqueConstraints = arrayOf(UniqueConstraint(columnNames = arrayOf("host_id", "ext_id"))))
data class StationRequest(
        @Id
        @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
        var id: Long? = null,
        @ManyToOne(fetch = javax.persistence.FetchType.LAZY, optional = false)
        @JoinColumn(name = "host_id")
        var host: AvsHost? = null,
        @ManyToOne(fetch = javax.persistence.FetchType.LAZY)
        @JoinColumn(name = "station_id")
        var station: Station? = null,
        @Column(name = "ext_id", nullable = false)
        var extId: String? = null,
        @Column(nullable = false)
        var name: String? = null,
        var description: String? = null,
        var okato: String? = null,
        @Column(name = "region_guid")
        var regionGuid: String? = null,
        @Column(name = "region_name")
        var regionName: String? = null,
        var latitude: BigDecimal? = null,
        var longitude: BigDecimal? = null,
        var created: Date? = Date()
)