package ru.etraffic.stations.domain.model

import java.math.BigDecimal
import java.util.*
import javax.persistence.*

/**
 * Created by nikolay on 28.08.16.
 */
@Entity
@Table(name = "st_batch_items")
data class StationItem(
        @Id
        @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
        var id: Long? = null,
        @ManyToOne(fetch = javax.persistence.FetchType.LAZY)
        @JoinColumn(name = "station_id")
        val station: Station? = null,
        @ManyToOne(fetch = javax.persistence.FetchType.LAZY, optional = false)
        @JoinColumn(name = "batch_id")
        val batch: StationBatch? = null,
        @Column(name = "host_id")
        var hostId: String? = null,
        val name: String? = null,
        val description: String? = null,
        val okato: String? = null,
        @Column(name="region_guid")
        val regionGuid: String? = null,
        @Column(name="region_name")
        val regionName: String? = null,
        val latitude: BigDecimal? = null,
        val longitude: BigDecimal? = null
)