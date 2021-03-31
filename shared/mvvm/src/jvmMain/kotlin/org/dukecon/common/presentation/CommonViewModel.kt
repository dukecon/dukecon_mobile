package org.dukecon.common.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope

actual open class CommonViewModel actual constructor() {
  actual val clientScope: CoroutineScope
    get() = GlobalScope

  protected actual open fun onCleared() {}
}
