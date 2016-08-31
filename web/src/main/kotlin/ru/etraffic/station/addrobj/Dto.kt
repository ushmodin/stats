package ru.etraffic.station.addrobj

/**
 *
 * @author Ushmodin N.
 * @since 31.08.2016 15:37
 */

data class AddrobjDto (
        val guid:String? = null,
        val offname:String? = null,
        val regioncode:String? = null,
        val areacode:String? = null,
        val placecode:String? = null,
        val okato:String? = null,
        val actstatus:Short? = null,
        val currstatus:Short? = null
)

data class AddrobjFilter (
        val guid:String? = null,
        val offname:String? = null,
        val regioncode:String? = null,
        val areacode:String? = null,
        val placecode:String? = null,
        val okato:String? = null
)