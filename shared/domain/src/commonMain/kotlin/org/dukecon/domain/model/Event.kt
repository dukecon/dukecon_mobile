package org.dukecon.domain.model

import io.ktor.util.date.GMTDate

data class Event(val eventId: String,
                 val title: String,
                 val eventDescription: String,
                 val startTime: GMTDate,
                 val endTime: GMTDate,
                 val speakers: List<Speaker> = emptyList(),
                 val favorite: Favorite = Favorite(),
                 val room: Location = Location(),
                 val track: Track = Track(),
                 val audience: Audience = Audience(),
                 val eventType: EventType = EventType(),
                 val demo: Boolean = false,
                 val language: Language = Language(),
                 val simultan: Boolean = false,
                 val veryPopular: Boolean = false,
                 val fullyBooked: Boolean = false,
                 val numberOfFavorites: Int = -1)
