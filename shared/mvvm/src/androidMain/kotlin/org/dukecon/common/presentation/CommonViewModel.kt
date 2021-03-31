package org.dukecon.common.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope

actual open class CommonViewModel actual constructor() : ViewModel() {
  actual val clientScope: CoroutineScope = viewModelScope
  actual override fun onCleared() {
    super.onCleared()
  }
}
