package ru.etraffic.stations.client

import java.math.BigDecimal
import javax.persistence.*

/**
 *
 * @author Ushmodin N.
 * @since 29.08.2016 14:27
 */
@Entity
@Table(name = "stations")
data class Station (
        @Id
        @Column(name = "st_id")
        var id: Long? = null,

        @Column(name = "st_uid")
        var guid: String? = null,

        @Column(name="st_name")
        var name: String? = null,

        @Column(name="st_desc")
        var description: String? = null,

        @ManyToOne(fetch = javax.persistence.FetchType.LAZY)
        @JoinColumn(name="st_reg_ref")
        var region: Region? = null,

        @ManyToOne(fetch = javax.persistence.FetchType.LAZY)
        @JoinColumn(name="st_kladr_ref")
        var kladr: Kladr? = null,

        var latitude: BigDecimal? = null,
        var longitude: BigDecimal? = null
)

@Entity
@Table(name = "ka_kladr")
data class Kladr (
        @Id
        var code: String? = null,

        var name: String? = null,

        @Column(name = "okatd")
        var okato: String? = null
)

@Entity
@Table(name = "regions")
data class Region (
        @Id
        @Column(name = "reg_id")
        var id: Long? = null,

        @Column(name = "reg_name")
        var name: String? = null,

        @Column(name = "reg_guid")
        var guid: String? = null
)
