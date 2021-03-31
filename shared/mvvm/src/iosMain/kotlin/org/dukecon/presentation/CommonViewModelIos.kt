package org.dukecon.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import org.dukecon.platform.dispatcher

actual open class CommonViewModel actual constructor() {
  private val viewModelJob = SupervisorJob()
  val viewModelScope: CoroutineScope = CoroutineScope(dispatcher() + viewModelJob)

  actual val clientScope: CoroutineScope = viewModelScope

  protected actual open fun onCleared() {
    viewModelJob.cancelChildren()
  }
}
