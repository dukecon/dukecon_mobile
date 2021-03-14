package org.dukecon.remote.mapper

import org.dukecon.data.model.EventEntity
import org.dukecon.remote.models.Event


/**
 * Map a [Event] to and from a [EventEntity] instance when data is moving between
 * this later and the Data layer
 */
internal class EventEntityMapper : EntityMapper<Event, EventEntity> {

    override fun mapFromRemote(type: Event): EventEntity {
        return EventEntity(
                type.id ?: "",
                type.title ?: "",
                type.abstractText ?: "",
                (type.start ?: "").parseDate(),
                (type.end ?: "").parseDate(),
                type.speakerIds ?: emptyList(),
                type.locationId ?: "",
                type.languageId ?: "",
                type.trackId ?: "",
                type.audienceId ?: "",
                type.typeId ?: "",
                type.demo ?: false,
                type.simultan ?: false,
                type.veryPopular ?: false,
                type.fullyBooked ?: false,
                type.numberOfFavorites ?: 0)
    }
}