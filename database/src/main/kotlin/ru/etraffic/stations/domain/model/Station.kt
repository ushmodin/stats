package ru.etraffic.stations.domain.model

import org.hibernate.search.annotations.Field
import org.hibernate.search.annotations.Indexed
import org.hibernate.search.annotations.IndexedEmbedded
import java.math.BigDecimal
import java.util.*
import javax.persistence.*

/**
 *
 * @author Ushmodin N.
 * @since 26.08.2016 17:30
 */
@Entity
@Table(name = "stations")
@Indexed
data class Station (
        @Id
        @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
        var id: Long? = null,

        @Column(name = "name", length = 200, nullable = false)
        @Field
        var name: String? = null,

        @Column(name = "typ", length = 10, nullable = false)
        @Field
        var type: String? = null,

        @Column(name = "address", length = 300, nullable = false)
        @Field
        var address: String? = null,

        @Column(name = "guid", length = 36, nullable = false, unique = true)
        var guid: String? = null,

        @ManyToOne(fetch = javax.persistence.FetchType.LAZY, optional = false)
        @JoinColumn(name = "region_id")
        @IndexedEmbedded
        var region: Region? = null,

        @ManyToOne(fetch = javax.persistence.FetchType.LAZY)
        @JoinColumn(name = "area_id")
        @IndexedEmbedded
        var area: Area? = null,

        @ManyToOne(fetch = javax.persistence.FetchType.LAZY)
        @JoinColumn(name = "city_id")
        @IndexedEmbedded
        var city: Place? = null,

        @ManyToOne(fetch = javax.persistence.FetchType.LAZY)
        @JoinColumn(name = "place_id")
        @IndexedEmbedded
        var place: Place? = null,

        @Column()
        val latitude: BigDecimal? = null,

        @Column()
        val longitude: BigDecimal? = null,

        @Column(name = "updated", nullable = false)
        var updated: Date? = Date(),

        @Enumerated(javax.persistence.EnumType.STRING)
        var status: EntityStatus? = EntityStatus.A
)
