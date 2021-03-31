package org.dukecon.android.ui.features.timemachine

import java.util.*
import org.dukecon.domain.features.time.CurrentTimeProvider

class ReleaseCurrentTimeProvider : CurrentTimeProvider {

  // todo add proper support for locales
  override fun currentTimeMillis(): Long {
    val calendar: Calendar = Calendar.getInstance()
    // GMT+2 Hours
    return calendar.timeInMillis + 2 * (1000 * 60 * 60)
  }
}
