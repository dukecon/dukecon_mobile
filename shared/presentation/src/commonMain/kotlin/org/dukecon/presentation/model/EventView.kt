package org.dukecon.presentation.model

import io.ktor.util.date.GMTDate

data class EventView(
    val id: String,
    val title: String,
    val description: String,
    val startTime: GMTDate,
    val endTime: GMTDate,
    val speakers: List<SpeakerView>,
    val favorite: Boolean,
    val room: String
)
