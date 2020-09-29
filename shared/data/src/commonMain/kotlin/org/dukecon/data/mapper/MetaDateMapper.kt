package org.dukecon.data.mapper

import org.dukecon.data.model.*
import org.dukecon.domain.model.*

class MetaDateMapper constructor() : Mapper<MetaDataEntity, MetaData> {
    override fun mapFromEntity(type: MetaDataEntity): MetaData {
        return MetaData(
                id = type.id,
                audiences = type.audiences.map { mapAudienceFromEntity(it) },
                eventTypes = type.eventTypes.map { mapEventTypeFromEntity(it) },
                languages = type.languages.map { mapLanguageFromEntity(it) },
                defaultLanguage = mapLanguageFromEntity(type.defaultLanguage),
                tracks = type.tracks.map { mapTracksFromEntity(it) },
                locations = type.locations.map { mapLocationsFromEntity(it) },
                defaultIcon = type.defaultIcon
        )
    }

    private fun mapLocationsFromEntity(locationsEntity: LocationsEntity): Location {
        return Location(locationsEntity.id, locationsEntity.order, locationsEntity.names,
                locationsEntity.icon, locationsEntity.capacity)
    }

    private fun mapTracksFromEntity(trackEntity: TrackEntity): Track {
        return Track(trackEntity.id, trackEntity.order, trackEntity.names, trackEntity.icon)
    }

    private fun mapLanguageFromEntity(languageEntity: LanguageEntity): Language {
        return Language(languageEntity.id, languageEntity.code, languageEntity.order, languageEntity.names, languageEntity.icon)
    }

    private fun mapEventTypeFromEntity(eventTypeEntity: EventTypeEntity): EventType {
        return EventType(eventTypeEntity.id, eventTypeEntity.order, eventTypeEntity.names, eventTypeEntity.icon)
    }

    private fun mapAudienceFromEntity(audienceEntity: AudienceEntity): Audience {
        return Audience(audienceEntity.id, audienceEntity.order, audienceEntity.names, audienceEntity.icon)
    }

    override fun mapToEntity(type: MetaData): MetaDataEntity {
        // TODO should be calles ...
        return MetaDataEntity(type.id, emptyList(), emptyList(), emptyList(),
                LanguageEntity("", "", 0, emptyMap(), ""),
                emptyList(), emptyList(), "")

    }

}