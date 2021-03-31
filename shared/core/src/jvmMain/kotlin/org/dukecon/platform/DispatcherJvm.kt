package org.dukecon.platform

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual fun dispatcher(): CoroutineDispatcher = Dispatchers.Main
