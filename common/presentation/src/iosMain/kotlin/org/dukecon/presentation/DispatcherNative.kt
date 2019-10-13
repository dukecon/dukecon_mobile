package org.dukecon.presentation

import kotlinx.coroutines.*
import platform.darwin.*
import kotlin.coroutines.*
import platform.Foundation.NSLog

internal actual fun dispatcher(): CoroutineDispatcher = UI

private object UI : CoroutineDispatcher() {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        val queue = dispatch_get_main_queue()
        dispatch_async(queue) {
            NSLog("[DEBUG]: (CoroutineDispatcher), dispatch_async")
            block.run()
        }
    }
}
