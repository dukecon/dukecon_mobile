package org.dukecon.domain.features.time

interface CurrentTimeProvider {
    fun currentTimeMillis(): Long
}
