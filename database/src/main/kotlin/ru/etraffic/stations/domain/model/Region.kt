package ru.etraffic.stations.domain.model

import org.hibernate.search.annotations.Field
import org.hibernate.search.annotations.IndexedEmbedded
import java.util.*
import javax.persistence.*

/**
 *
 * @author Ushmodin N.
 * @since 26.08.2016 17:20
 */

@Entity
@Table(name = "regions")
data class Region(
        @Id
        @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
        var id: Long? = null,
        @Column(name = "name", length = 120, nullable = false)
        @Field
        var name: String? = null,
        @Column(name = "typ", length = 10, nullable = false)
        var type: String? = null,
        @Column(name = "code", length = 2, nullable = false, unique = true)
        var code: String? = null,
        @Column(name = "guid", length = 36, nullable = false, unique = true)
        @Field(norms = org.hibernate.search.annotations.Norms.NO)
        var guid: String? = null,
        @Column(name = "okato", length = 11, nullable = false, unique = true)
        var okato: String? = null,
        @ManyToOne(fetch = javax.persistence.FetchType.LAZY, optional = false)
        @JoinColumn(name = "country_id")
        @IndexedEmbedded
        var country: Country? = null,
        @Column(name = "updated", nullable = false)
        var updated: Date? = Date(),
        @Enumerated(javax.persistence.EnumType.STRING)
        var status: EntityStatus? = EntityStatus.A

)