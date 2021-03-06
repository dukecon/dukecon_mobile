package org.dukecon.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.dukecon.aspects.logging.LogLevel
import org.dukecon.aspects.logging.log

/**
 * Executes business logic synchronously or asynchronously using Coroutines.
 *
 * The [execute] method of [SuspendUseCase] is a suspend function as opposed to the
 * [UseCase.execute] method of [UseCase].
 */
abstract class SuspendUseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {

    /** Executes the use case asynchronously and returns a [Result].
     *
     * @return a [Result].
     *
     * @param parameters the input parameters to run the use case with
     */
    suspend operator fun invoke(parameters: P): Result<R> {
        return try {
            // Moving all use case's executions to the injected dispatcher
            // In production code, this is usually the Default dispatcher (background thread)
            // In tests, this becomes a TestCoroutineDispatcher
            withContext(coroutineDispatcher) {
                execute(parameters).let {
                    Result.Success(it)
                }
            }
        } catch (e: Exception) {
            log(LogLevel.ERROR, "SuspendUseCase", "error", e)
            Result.Error(e)
        }
    }

    /**
     * Override this to set the code to be executed.
     */
    protected abstract suspend fun execute(parameters: P): R
}
