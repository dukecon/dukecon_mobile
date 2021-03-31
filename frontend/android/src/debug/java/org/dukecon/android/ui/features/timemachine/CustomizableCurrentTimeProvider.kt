package org.dukecon.android.ui.features.timemachine

import org.dukecon.domain.features.time.CurrentTimeProvider

abstract class CustomizableCurrentTimeProvider : CurrentTimeProvider {
  abstract fun setCustomMillis(value: Long)
}
