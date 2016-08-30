package ru.etraffic.stations.client

import com.google.common.collect.Lists

/**
 *
 * @author Ushmodin N.
 * @since 30.08.2016 15:12
 */

fun <T> List<T>.split(size: Int) = Lists.partition<T>(this, size)