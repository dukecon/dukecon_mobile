import androidx.compose.desktop.Window
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.dukecon.api.EventsList
import org.dukecon.configuration.JavalandConfiguration
import org.dukecon.configuration.RepositoryFactory
import org.dukecon.presentation.EventsViewModel


fun main() {
    val conf = JavalandConfiguration()
    val repository = RepositoryFactory.createConferenceRepository(conf)
    val statusScreenViewModel = EventsViewModel(repository)

    GlobalScope.launch {
        repository.update()
    }

    return Window {
        EventsList(statusScreenViewModel)
    }
}