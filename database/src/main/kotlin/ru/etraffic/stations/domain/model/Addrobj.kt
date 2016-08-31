package ru.etraffic.stations.domain.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

/**
 *
 * @author Ushmodin N.
 * @since 31.08.2016 15:19
 */

@Entity
@Table(name = "addrobj")
data class Addrobj (
        @Id
        @Column(name = "aoid")
        var id: String? = null,
        val aoguid: String? = null,
        val offname: String? = null,
        val shortname: String? = null,
        val aolevel: Short? = null,
        val regioncode: String? = null,
        val areacode: String? = null,
        val placecode: String? = null,
        val postalcode: String? = null,
        val okato: String? = null,
        val parentguid: String? = null,
        val actstatus: Short? = null,
        val currstatus: Short? = null
)