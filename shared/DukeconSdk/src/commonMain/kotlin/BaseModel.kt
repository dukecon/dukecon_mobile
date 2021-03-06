import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.dukecon.aspects.logging.LogLevel
import org.dukecon.aspects.logging.log
import org.dukecon.platform.dispatcher
import kotlin.coroutines.CoroutineContext

open class BaseModel {
    internal val mainScope = MainScope(Dispatchers.Main)
    internal val ktorScope = MainScope(Dispatchers.Main)

    open fun onDestroy() {
        mainScope.job.cancel()
        ktorScope.job.cancel()
    }
}

internal class MainScope(private val mainContext: CoroutineContext) : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = dispatcher() + job + exceptionHandler

    internal val job = Job()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        showError(throwable)
    }

    //TODO: Some way of exposing this to the caller without trapping a reference and freezing it.
    fun showError(t: Throwable) {
        log(LogLevel.ERROR, "MainScope", "showError", t)
    }
}