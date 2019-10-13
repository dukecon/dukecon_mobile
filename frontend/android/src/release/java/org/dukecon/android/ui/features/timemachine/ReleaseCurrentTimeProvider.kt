package org.dukecon.android.ui.features.timemachine

import org.dukecon.domain.features.time.CurrentTimeProvider
import java.util.*

class ReleaseCurrentTimeProvider : CurrentTimeProvider() {

    override fun currentTimeMillis(): Long {
        val calendar: Calendar = Calendar.getInstance()
        return calendar.timeInMillis + 2 * (1000 * 60 * 60)
    }
}
