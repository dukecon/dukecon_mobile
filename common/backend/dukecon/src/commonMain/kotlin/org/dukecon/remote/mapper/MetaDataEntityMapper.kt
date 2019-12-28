package org.dukecon.remote.mapper

import org.dukecon.data.model.*
import org.dukecon.remote.api.*

class MetaDataEntityMapper() : EntityMapper<MetaData, MetaDataEntity> {
    override fun mapFromRemote(type: MetaData): MetaDataEntity {
        return MetaDataEntity(
                id = type.id,
                audiences = type.audiences.map { mapAudiences(it) },
                eventTypes = type.eventTypes.map { mapEventTypes(it) },
                languages = type.languages.map { mapLanguages(it) },
                defaultLanguage = mapLanguages(type.defaultLanguage),
                tracks = type.tracks.map { mapTracks(it) },
                locations = type.locations.map { mapLocations(it) },
                defaultIcon = type.defaultIcon)
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