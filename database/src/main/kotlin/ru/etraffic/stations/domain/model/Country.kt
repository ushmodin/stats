package ru.etraffic.stations.domain.model

import javax.persistence.*

/**
 *
 * @author Ushmodin N.
 * @since 26.08.2016 16:33
 */

@Entity
@Table(name = "countries")
data class Country (
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "short_name", nullable = false, length = 128)
    var shortName: String? = null,

    @Column(name = "long_name", length = 128)
    var longName: String? = null,

    @Column(name = "short_eng_name", nullable = false, length = 128)
    var shortEngName: String? = null,

    @Column(name = "code2", nullable = false, length = 2, unique = true)
    var iso2: String? = null,

    @Column(name = "code3", nullable = false, length = 3, unique = true)
    var iso3: String? = null,

    @Enumerated(javax.persistence.EnumType.STRING)
    var status: EntityStatus? = EntityStatus.A
)