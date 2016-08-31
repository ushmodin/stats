package ru.etraffic.station

/**
 *
 * @author Ushmodin N.
 * @since 31.08.2016 15:07
 */

data class Response<T> (
        val success: Boolean = true,
        val data: T? = null,
        val error: Error? = null
)

data class Error (val code: String = "", val message: String = "")

fun Any.success() = Response(data = this)