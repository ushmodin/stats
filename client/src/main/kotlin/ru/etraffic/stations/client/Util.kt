package ru.etraffic.stations.client

import com.google.common.collect.Lists

/**
 *
 * @author Ushmodin N.
 * @since 30.08.2016 15:12
 */

fun <T> List<T>.split(size: Int) = Lists.partition<T>(this, size)

fun Region.fullInfo(): FullRegionInfo {
    var country: Region? = null
    var region: Region? = null
    var area: Region? = null
    var city: Region? = null

    var cur: Region? = this
    while (cur != null) {
        when (cur.type) {
            1 -> country = cur
            2 -> if (cur.id == cur.parent?.id) country = cur else region = cur
            3,4,7,8 -> region = cur
            5 -> area = cur
            6 -> city = cur
        }
        cur = if (cur.id != cur.parent?.id) cur.parent else null
    }
    return FullRegionInfo(country, region, area, city)
}