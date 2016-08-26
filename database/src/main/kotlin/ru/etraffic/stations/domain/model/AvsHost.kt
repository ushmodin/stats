package ru.etraffic.stations.domain.model

import java.util.*
import javax.persistence.*

/**
 *
 * @author Ushmodin N.
 * @since 26.08.2016 17:47
 */
@Entity
@Table(name = "avs_hosts")
data class AvsHost(
        @Id
        @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
        var id: Long? = null,
        @Column(name = "name", length = 120, nullable = false)
        var name: String? = null,
        @Column(name = "desc", length = 120)
        var description: String? = null,
        @Column(name = "inn", length = 12)
        var inn: String? = null,
        @Column(name = "ip", length = 15)
        var ip: String? = null,
        @Column(length = 36)
        var guid: String? = null,
        @Column()
        var created: Date? = Date()
)