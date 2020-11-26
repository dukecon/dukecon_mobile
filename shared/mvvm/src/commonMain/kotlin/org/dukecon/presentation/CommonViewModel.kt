package org.dukecon.presentation

import kotlinx.coroutines.CoroutineScope

expect open class CommonViewModel() {
    val clientScope: CoroutineScope
    protected open fun onCleared()
}