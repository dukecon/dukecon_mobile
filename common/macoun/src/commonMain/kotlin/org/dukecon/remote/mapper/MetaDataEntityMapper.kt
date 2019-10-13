package org.dukecon.remote.mapper

import org.dukecon.data.model.*
import org.dukecon.remote.api.Conference
import org.dukecon.remote.api.Talk
import org.dukecon.remote.api.Location

class MetaDataEntityMapper : EntityMapper<Conference, MetaDataEntity> {
    override fun mapFromRemote(type: Conference): MetaDataEntity {
        return MetaDataEntity(
                id = type.conference,
                audiences = emptyList(),
                eventTypes = emptyList(),//   type.talks.distinctBy { it.category }.map { mapEvent(it) },
                languages = emptyList(),
                defaultLanguage = mapLanguages("de"),
                tracks = emptyList(), // type.talks.distinctBy { it.category }.map { mapTrack(it) },
                locations = emptyList(), // type.rooms.map { mapLocations(it) },
                defaultIcon = "")
    }

    private fun mapTrack(it: Talk): TrackEntity =
            TrackEntity(
                    id = it.id,
                    order = 0,
                    names = "".toNamesMap(),
                    icon = ""
            )

    private fun mapEvent(it: Talk): EventTypeEntity =
            EventTypeEntity(it.id, 0, toTypeHashMap(""), "")

    private fun toTypeHashMap(category: String): Map<String, String> {
        return HashMap<String, String>().also {
            it["de"] = category
            it["en"] = ""
        }.toImmutableMap()
    }

    private fun mapLocations(it: Location): LocationsEntity {
        return LocationsEntity(
                id = it.id,
                order = it.sort_key.toInt(),
                names = it.name.toNamesMap(),
                icon = "",
                capacity = it.seats
        )
    }

    private fun mapLanguages(lang: String): LanguageEntity {
        return LanguageEntity(lang, lang, 0, "".toNamesMap(), "")
    }
}

private fun String.toNamesMap(): Map<String, String> {
    return HashMap<String, String>().also {
        it["de"] = this
        it["en"] = ""
    }.toImmutableMap()
}
