package org.dukecon.presentation.model

import io.ktor.util.date.GMTDate
import org.dukecon.domain.model.Favorite

data class EventView(val id: String,
                     val title: String,
                     val description: String,
                     val startTime: GMTDate,
                     val endTime: GMTDate,
                     val speakers: List<SpeakerView>,
                     val favorite: Boolean,
                     val room: String)