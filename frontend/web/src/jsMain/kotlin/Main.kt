import androidx.compose.runtime.mutableStateOf
import androidx.compose.web.css.padding
import androidx.compose.web.css.px
import androidx.compose.web.elements.Button
import androidx.compose.web.elements.Div
import androidx.compose.web.elements.Span
import androidx.compose.web.elements.Text
import androidx.compose.web.renderComposable
import io.ktor.client.engine.js.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.dukecon.aspects.logging.LogLevel
import org.dukecon.aspects.logging.log
import org.dukecon.cache.storage.InMemoryStorage
import org.dukecon.domain.model.Room
import org.dukecon.time.CurrentDataTimeProvider
import org.dukecon.web.ui.configuration.JavalandConfiguration
import org.dukecon.web.ui.configuration.RepositoryFactory

val repository =
    RepositoryFactory.createConferenceRepository(
        JavalandConfiguration(),
        object : CurrentDataTimeProvider {
          override fun currentTimeMillis(): Long {
            return 0
          }
        },
        InMemoryStorage())
// = RepositoryFactory.createConferenceRepository(Co)
// DukeconApi("https://programm.javaland.eu/2019/rest/", "javaland2019")

fun main() {

  val mainScope = MainScope()

  GlobalScope.launch { repository.update() }

  val count = mutableStateOf(Room("3", "Room"))
  val sessions = mutableStateOf("")

  renderComposable(rootElementId = "root") {
    Div(style = { padding(25.px) }) {
      Button(
          attrs = {
            onClick {
              count.value = Room("4", "Room minus")
              log(LogLevel.DEBUG, "Web", "minus")
            }
          }) { Text("-") }

      Span(style = { padding(15.px) }) { Text(count.value.name) }

      Button(
          attrs = {
            onClick {
              count.value = Room("4", "Room plus")
              log(LogLevel.DEBUG, "Web", "plus")
            }
          }) { Text("+") }
      Button(
          attrs = {
            onClick {
              log(LogLevel.DEBUG, "Web", "suspend")
              mainScope.launch {
                log(LogLevel.DEBUG, "Web", "response.map")
                val response = repository.getSpeakers()
                response.forEach { speaker -> log(LogLevel.DEBUG, "Web", speaker.name) }
                // sessions.value =
                //  response.map { speaker -> "$speaker.name" }.toList().joinToString { " " }
                // count.value = Room("4", "Room plus")
              }
            }
          }) { Text(sessions.value) }
    }
    Div { Text(sessions.value) }
  }
}
