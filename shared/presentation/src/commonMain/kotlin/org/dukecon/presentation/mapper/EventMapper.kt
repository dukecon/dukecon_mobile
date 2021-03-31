package org.dukecon.presentation.mapper

import org.dukecon.domain.model.Event
import org.dukecon.presentation.model.EventView

/**
 * Map a [EventView] to and from a [Event] instance when data is moving between this layer and the
 * Domain layer
 */
open class EventMapper constructor(private val speakersMapper: SpeakerMapper) :
    Mapper<EventView, Event> {

  /** Map a [Event] instance to a [EventView] instance */
  override fun mapToView(type: Event): EventView {
    return EventView(
        type.eventId,
        type.title,
        type.eventDescription,
        type.startTime,
        type.endTime,
        type.speakers.map { speakersMapper.mapToView(it) },
        type.favorite.selected,
        type.room.names["de"] ?: "")
  }
}
