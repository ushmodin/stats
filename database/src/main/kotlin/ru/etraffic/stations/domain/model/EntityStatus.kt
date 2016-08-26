package ru.etraffic.stations.domain.model

/**
 *
 * @author Ushmodin N.
 * @since 26.08.2016 16:42
 */

enum class EntityStatus(val value: String) {
    ACTIVE("A"), HIDED("H"), DELETED("D");

    override fun toString(): String {
        return value
    }
}