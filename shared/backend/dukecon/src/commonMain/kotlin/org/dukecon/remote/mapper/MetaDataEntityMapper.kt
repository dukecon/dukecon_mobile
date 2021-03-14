package org.dukecon.remote.mapper

import org.dukecon.data.model.*
import org.dukecon.remote.api.*
import org.dukecon.remote.models.*
import org.dukecon.remote.models.EventType
import org.dukecon.remote.models.Language
import org.dukecon.remote.models.Location
import org.dukecon.remote.models.MetaData
import org.dukecon.remote.models.Track

internal class MetaDataEntityMapper() : EntityMapper<MetaData, MetaDataEntity> {
    override fun mapFromRemote(type: MetaData): MetaDataEntity {
        return MetaDataEntity(
                id = type.id ?: "",
                audiences = type.audiences?.let { it.map { aud -> mapAudiences(aud) } } ?: emptyList(),
                eventTypes = type.eventTypes?.let { it.map { event -> mapEventTypes(event) } } ?: emptyList(),
                languages = type.languages?.let { it.map { lang -> mapLanguages(lang) } } ?: emptyList(),
                defaultLanguage = mapLanguages(getDefaultLangugae(type.defaultLanguage)),
                tracks = type.tracks?.let { it.map { track -> mapTracks(track) } } ?: emptyList(),
                locations = type.locations?.let { it.map { loc -> mapLocations(loc) } } ?: emptyList(),
                defaultIcon = type.defaultIcon ?: "")
    }

    private fun getDefaultLangugae(defaultLanguage: Language?): Language {
        return defaultLanguage?.let {
            defaultLanguage
        } ?: Language()
    }

    private fun mapLocations(it: Location): LocationsEntity {
        return LocationsEntity(
                id = it.id ?: "",
                order = it.order ?: 0,
                names = it.names?.toMap() ?: emptyMap(),
                icon = it.icon ?: "",
                capacity = it.capacity ?: 0
        )
    }

    private fun mapTracks(it: Track): TrackEntity {
        return TrackEntity(
                id = it.id ?: "",
                order = it.order ?: 0,
                names = it.names?.toMap() ?: emptyMap(),
                icon = it.icon ?: ""
        )
    }

    private fun mapLanguages(it: Language): LanguageEntity {
        return LanguageEntity(
                it.id ?: "",
                it.code ?: "",
                it.order ?: 0,
                it.names?.toMap() ?: emptyMap(),
                it.icon ?: ""
        )
    }

    private fun mapEventTypes(it: EventType): EventTypeEntity {
        return EventTypeEntity(
                id = it.id ?: "",
                order = it.order ?: 0,
                names = it.names?.toMap() ?: emptyMap(),
                icon = it.icon ?: ""
        )
    }

    private fun mapAudiences(audience: Audience): AudienceEntity {
        return AudienceEntity(
                audience.id ?: "",
                audience.order ?: 0,
                audience.names?.toMap() ?: emptyMap(),
                audience.icon ?: ""
        )
    }
}