package org.dukecon.presentation.date

import io.ktor.util.date.GMTDate
import kotlin.math.abs

object Duration {
    fun inMinutes(startTime: GMTDate, endTime: GMTDate): Long {
        val durationMs = abs(endTime.timestamp - startTime.timestamp)
        return durationMs / (1000L * 60)
    }
}
