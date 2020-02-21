package org.dukecon.domain.model

import io.ktor.util.date.GMTDate

data class Event(val eventId: String,
                 val title: String,
                 val eventDescription: String,
                 val startTime: GMTDate,
                 val endTime: GMTDate,
                 val speakers: List<Speaker>,
                 val favorite: Favorite,
                 val room: Location,
                 val track: Track,
                 val audience: Audience,
                 val eventType: EventType,
                 val demo: Boolean,
                 val language: Language,
                 val simultan: Boolean,
                 val veryPopular: Boolean,
                 val fullyBooked: Boolean,
                 val numberOfFavorites: Int)
