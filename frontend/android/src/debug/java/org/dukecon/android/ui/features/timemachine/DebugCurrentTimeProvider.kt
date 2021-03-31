package org.dukecon.android.ui.features.timemachine

import java.util.*

class DebugCurrentTimeProvider : CustomizableCurrentTimeProvider() {

  override fun currentTimeMillis(): Long {
    val calendar: Calendar = Calendar.getInstance()
    return calendar.timeInMillis + 2 * 1000 * 60 * 60 + offset
  }

  private var offset: Long = 0

  override fun setCustomMillis(value: Long) {
    val calendar: Calendar = Calendar.getInstance()
    offset = value - calendar.timeInMillis
  }
}
