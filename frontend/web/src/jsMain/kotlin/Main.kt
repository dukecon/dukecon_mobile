import androidx.compose.runtime.mutableStateOf
import androidx.compose.web.css.padding
import androidx.compose.web.css.px
import androidx.compose.web.elements.Button
import androidx.compose.web.elements.Div
import androidx.compose.web.elements.Span
import androidx.compose.web.elements.Text
import androidx.compose.web.renderComposable
import org.dukecon.aspects.logging.LogLevel
import org.dukecon.aspects.logging.log
import org.dukecon.domain.model.Room

fun main() {
  val count = mutableStateOf(Room("3", "Room"))

  renderComposable(rootElementId = "root") {
    Div(style = { padding(25.px) }) {
      Button(
          attrs = {
            onClick {
              count.value = Room("4", "Room minus")
              log(LogLevel.DEBUG, "Web", "minus")
            }
          }) { Text("-") }

      Span(style = { padding(15.px) }) { Text("${count.value.name}") }

      Button(
          attrs = {
            onClick {
              count.value = Room("4", "Room plus")
              log(LogLevel.DEBUG, "Web", "plus")
            }
          }) { Text("+") }
    }
  }
}
