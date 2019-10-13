package org.dukecon.domain.features.time

import org.dukecon.date.now

open class CurrentTimeProvider {
    open fun currentTimeMillis(): Long = now()
}
