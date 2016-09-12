package ru.etraffic.stations.domain.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

/**
 *
 * @author Ushmodin N.
 * @since 12.09.2016 17:27
 */
@Entity
@Table(name="addrobjtypes")
data class AddrobjType(
        @Id
        var id: Long? = null,
        @Column(name = "socrname")
        var name: String? = null,
        @Column(name = "scname")
        var shortName: String? = null,
        var level: Integer? = null
)