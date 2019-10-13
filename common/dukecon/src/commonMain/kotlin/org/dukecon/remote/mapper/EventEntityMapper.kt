package org.dukecon.remote.mapper

import org.dukecon.data.model.EventEntity
import org.dukecon.remote.api.Event
import org.dukecon.date.parseDate


open class EventEntityMapper constructor() : EntityMapper<Event, EventEntity> {

    override fun mapFromRemote(type: Event): EventEntity {
        return EventEntity(
                type.id,
                type.title,
                type.abstractText ?: "",
                type.start.parseDate(),
                type.end.parseDate(),
                type.speakerIds,
                type.locationId,
                type.languageId,
                type.trackId ?: "",
                type.audienceId ?: "",
                type.typeId ?: "",
                type.demo,
                type.simultan,
                type.veryPopular,
                type.fullyBooked,
                type.numberOfFavorites)
    }
}