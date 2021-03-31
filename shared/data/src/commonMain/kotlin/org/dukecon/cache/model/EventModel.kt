package org.dukecon.cache.model

import io.ktor.util.date.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Suppress("PLUGIN_WARNING")
@Serializable
data class EventModel(
    val id: String,
    val title: String,
    val abstractText: String,
    val startTime: String,
    val endTime: String,
    val speakerIds: List<String>,
    val locationId: String,
    val languageId: String,
    val trackId: String,
    val audienceId: String,
    val eventTypeId: String,
    val demo: Boolean,
    val simultan: Boolean,
    val veryPopular: Boolean,
    val fullyBooked: Boolean,
    val numberOfFavorites: Int
) {
  @Transient
  val startsAt: GMTDate
    get() = startTime.parseDate()

  @Transient
  val endsAt: GMTDate
    get() = endTime.parseDate()
}

fun String.parseDate(): GMTDate {
  try {
    val year = substring(0, 4).toIntOrFormatError()
    val month = substring(5, 7).toIntOrFormatError()
    val day = substring(8, 10).toIntOrFormatError()

    val hour = substring(11, 13).toIntOrFormatError()
    val minute = substring(14, 16).toIntOrFormatError()
    val second = substring(17, 19).toIntOrFormatError()

    return GMTDate(second, minute, hour, day, Month.from(month - 1), year)
  } catch (e: Error) {
    return GMTDate(0)
  }
}

fun String.toIntOrFormatError() = toIntOrNull() ?: throw Error("Format of $this is not correct")
