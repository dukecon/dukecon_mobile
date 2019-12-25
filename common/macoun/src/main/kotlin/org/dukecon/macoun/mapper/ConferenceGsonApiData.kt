package org.dukecon.macoun.mapper

import com.google.gson.annotations.SerializedName

data class ConferenceJson(
        val dtd: String = "",
        val conference: String = "",
        val year: Int = 0,
        val breakingnews: String = "",
        val speakers: List<SpeakerJson> = emptyList(),
        val talks: List<TalkJson> = emptyList(),
        val rooms: List<LocationJson> = emptyList()
)

data class MetaData(
        val id: String = "",
        val audiences: List<Audience> = emptyList(),
        val eventTypes: List<EventType> = emptyList(),
        val languages: List<Language> = emptyList(),
        val defaultLanguage: Language = Language(),
        val tracks: List<Track> = emptyList(),
        val locations: List<LocationJson> = emptyList(),
        val defaultIcon: String = ""
)

data class Audience(
        val id: String,
        val order: Int,
        val names: DefinitionsAudienceNames,
        val icon: String = ""
)

data class EventType(
        val id: String,
        val order: Int,
        val names: DefinitionsEventTypeNames,
        val icon: String = ""
)

data class Language(
        val id: String = "",
        val code: String = "",
        val order: Int = -1,
        val names: DefinitionsLanguageNames = DefinitionsLanguageNames(),
        val icon: String = ""
)

data class Track(
        val id: String,
        val order: Int,
        val names: DefinitionsTrackNames,
        val icon: String = ""
)

data class LocationJson(
        val id: String = "",
        val sort_key: String = "",
        val name: String = "",
        val seats: Int = 0
)

data class TalkJson(
        val id: String = "",
        val title: String = "",
        val description: String = "",
        val speaker_id: List<String> = emptyList(),
        @SerializedName("room-id")
        val room_id: String = "",
        val startdate: String = "",
        val duration: Int = 0,
        val category: String = "",
        val booking: BookingJson = BookingJson(),
        val links: EmptyObject = EmptyObject(),
        @SerializedName("-sidetracked")
        val sidetracked:Boolean = false
)

data class BookingJson(
        val from: String = "",
        val to: String = "",
        val seats: Int = 0)

class EmptyObject {
}

data class SpeakerJson(
        val id: String = "",
        val firstname: String = "",
        val lastname: String = "",
        val image: String = "",
        val twitter: String = "",
        val description: String = ""
)

data class Feedback(
        val comment: String,
        val rating: Int
)

// Synthetic class name
data class DefinitionsAudienceNames(val de: String = "", val en: String = "") {
    fun toMap(): Map<String, String> {
        return HashMap<String, String>().also {
            it["de"] = de
            it["en"] = en
        }.toImmutableMap()
    }
}

// Synthetic class name
data class DefinitionsEventTypeNames(val de: String = "", val en: String = "") {
    fun toMap(): Map<String, String> {
        return HashMap<String, String>().also {
            it["de"] = de
            it["en"] = en
        }.toImmutableMap()
    }
}

// Synthetic class name
data class DefinitionsLanguageNames(val de: String = "", val en: String = "") {
    fun toMap(): Map<String, String> {
        return HashMap<String, String>().also {
            it["de"] = de
            it["en"] = en
        }.toImmutableMap()
    }
}

// Synthetic class name
data class DefinitionsTrackNames(val de: String = "", val en: String = "") {
    fun toMap(): Map<String, String> {
        return HashMap<String, String>().also {
            it["de"] = de
            it["en"] = en
        }.toImmutableMap()
    }
}

// Synthetic class name
data class DefinitionsLocationNames(val de: String = "", val en: String = "") {
    fun toMap(): Map<String, String> {
        return HashMap<String, String>().also {
            it["de"] = de
            it["en"] = en
        }.toImmutableMap()
    }
}
