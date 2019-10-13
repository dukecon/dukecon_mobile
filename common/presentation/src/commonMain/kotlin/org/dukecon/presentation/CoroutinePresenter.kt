package org.dukecon.presentation

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class CoroutinePresenter<V : BaseView>: CoroutineScope, BasePresenter<V> {

    private val job = Job()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        showError(throwable)
    }

    override val coroutineContext: CoroutineContext
        get() = dispatcher() + job + exceptionHandler

    open fun onDestroy() {
        job.cancel()
    }
}
