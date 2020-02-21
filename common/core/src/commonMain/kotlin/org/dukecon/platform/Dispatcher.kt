package org.dukecon.platform

import kotlinx.coroutines.*

expect fun dispatcher(): CoroutineDispatcher
