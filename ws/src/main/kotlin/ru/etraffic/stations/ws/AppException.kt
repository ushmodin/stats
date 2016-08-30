package ru.etraffic.stations.ws

/**
 *
 * @author Ushmodin N.
 * @since 30.08.2016 15:48
 */

class AppException(val code: AppErrorCode, message: String): RuntimeException(message)

enum class AppErrorCode {
    UNKNOWN_HOST, TO_MANY_DATA
}