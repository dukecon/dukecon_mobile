package org.dukecon.data.model

import org.dukecon.cache.model.EventModel
import org.dukecon.cache.model.RoomModel
import org.dukecon.cache.model.SpeakerModel

data class ConferenceEntity(
    val speakers: List<SpeakerModel>,
    val events: List<EventModel>,
    val Rooms: List<RoomModel>
)
