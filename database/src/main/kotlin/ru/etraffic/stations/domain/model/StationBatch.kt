package ru.etraffic.stations.domain.model

import java.util.*
import javax.persistence.*

/**
 * Created by nikolay on 29.08.16.
 */
@Entity
@Table(name = "st_batchs")
class StationBatch (
        @Id
        @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
        var id: Long? = null,
        @ManyToOne(fetch = javax.persistence.FetchType.LAZY, optional = false)
        @JoinColumn(name = "host_id")
        var host: AvsHost? = null,
        @Column(unique = true)
        var guid: String? = null,
        var created: Date? = Date()

)