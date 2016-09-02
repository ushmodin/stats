package ru.etraffic.station

/**
 *
 * @author Ushmodin N.
 * @since 31.08.2016 15:09
 */
fun String.jpaContaints() = "%$this%"

fun String.jpaStartWith() = "$this%"