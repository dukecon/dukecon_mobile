package org.dukecon.presentation

import kotlinx.coroutines.*

internal actual fun dispatcher(): CoroutineDispatcher = Dispatchers.Main