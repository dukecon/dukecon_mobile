package org.dukecon.platform

import kotlinx.coroutines.*

actual fun dispatcher(): CoroutineDispatcher = Dispatchers.Main
