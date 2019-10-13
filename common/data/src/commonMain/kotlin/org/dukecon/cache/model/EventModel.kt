package org.dukecon.cache.model

import io.ktor.util.date.GMTDate
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.dukecon.date.parseDate

@Serializable
data class EventModel(val id: String,
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
                      val numberOfFavorites: Int) {
    @Transient
    val startsAt: GMTDate
        get() = startTime.parseDate()

    @Transient
    val endsAt: GMTDate
        get() = endTime.parseDate()
}

