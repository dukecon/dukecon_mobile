package org.dukecon.android.ui.features.timemachine

import org.dukecon.domain.features.time.CurrentTimeProvider

object CurrentTimeProviderFactory {
  fun newInstance(): CurrentTimeProvider {
    return DebugCurrentTimeProvider()
  }
}
