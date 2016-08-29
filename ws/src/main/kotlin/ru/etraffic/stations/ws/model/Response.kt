package ru.etraffic.stations.ws.model

import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlRegistry
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.XmlType

/**
 * Created by nikolay on 27.08.16.
 */
open class Response<T> (
        val success: Boolean = true,
        val data: T? = null,
        val error: Error? = null
) {
    companion object {
        fun error(code: String?, message: String?)
                = Response(success = false, data = null, error = Error(code, message))
    }
}

class Error (
        val code: String? =  null,
        val message: String? = null
)


fun Any.success() = Response(success = true, data = this, error = null)

