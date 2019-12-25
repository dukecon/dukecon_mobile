package org.dukecon.android.ui.features.timemachine

import org.dukecon.domain.features.time.CurrentTimeProvider
import java.util.*

class ReleaseCurrentTimeProvider : CurrentTimeProvider() {

    // todo add proper support for locales
    override fun currentTimeMillis(): Long {
        val calendar: Calendar = Calendar.getInstance()
        // GMT+2 Hours
        return calendar.timeInMillis + 2 * (1000 * 60 * 60)
    }
}
