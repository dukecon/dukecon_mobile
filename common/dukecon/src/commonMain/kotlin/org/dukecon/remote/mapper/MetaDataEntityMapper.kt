package org.dukecon.remote.mapper

import org.dukecon.data.model.*
import org.dukecon.remote.api.*

class MetaDataEntityMapper() : EntityMapper<MetaData, MetaDataEntity> {
    override fun mapFromRemote(type: MetaData): MetaDataEntity {
        return MetaDataEntity(
                type.id ?: "",
                type.audiences.map { mapAudiences(it) },
                type.eventTypes.map { mapEventTypes(it) },
                type.languages.map { mapLanguages(it) },
                mapLanguages(type.defaultLanguage),
                type.tracks.map { mapTracks(it) },
                type.locations.map { mapLocations(it) },
                type.defaultIcon)
    }

    private fun mapLocations(it: Location): LocationsEntity {
        return LocationsEntity(it.id, it.order, it.names.toMap(), it.icon, it.capacity)
    }

    private fun mapTracks(it: Track): TrackEntity {
        return TrackEntity(it.id, it.order, it.names.toMap(), it.icon)
    }

    private fun mapLanguages(it: Language): LanguageEntity {
        return LanguageEntity(it.id, it.code, it.order, it.names.toMap(), it.icon)
    }

    private fun mapEventTypes(it: EventType): EventTypeEntity {
        return EventTypeEntity(it.id, it.order, it.names.toMap(), it.icon)
    }

    private fun mapAudiences(audience: Audience): AudienceEntity {
        return AudienceEntity(audience.id, audience.order, audience.names.toMap(), audience.icon)
    }
}