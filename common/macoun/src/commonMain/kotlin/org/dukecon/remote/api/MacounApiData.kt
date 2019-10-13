package org.dukecon.remote.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.dukecon.domain.aspects.twitter.TwitterLinks
import org.dukecon.remote.mapper.toImmutableMap

@Serializable
data class Conference(
        val dtd: String = "",
        val conference: String = "",
        val year: Int = 0,
        val breakingnews: String = "",
        val speakers: List<Speaker> = emptyList(),
        val talks: List<Talk> = emptyList(),
        val rooms: List<Location> = emptyList()
)

@Serializable
data class MetaData(
        val id: String = "",
        val audiences: List<Audience> = emptyList(),
        val eventTypes: List<EventType> = emptyList(),
        val languages: List<Language> = emptyList(),
        val defaultLanguage: Language = Language(),
        val tracks: List<Track> = emptyList(),
        val locations: List<Location> = emptyList(),
        val defaultIcon: String = ""
)

@Serializable
data class Audience(
        val id: String,
        val order: Int,
        val names: DefinitionsAudienceNames,
        val icon: String = ""
)

@Serializable
data class EventType(
        val id: String,
        val order: Int,
        val names: DefinitionsEventTypeNames,
        val icon: String = ""
)

@Serializable
data class Language(
        val id: String = "",
        val code: String = "",
        val order: Int = -1,
        val names: DefinitionsLanguageNames = DefinitionsLanguageNames(),
        val icon: String = ""
)

@Serializable
data class Track(
        val id: String,
        val order: Int,
        val names: DefinitionsTrackNames,
        val icon: String = ""
)

@Serializable
data class Location(
        val id: String = "",
        @SerialName("sort-key")
        val sort_key: String = "",
        val name: String = "",
        val seats: Int = 0
)

@Serializable
data class Talk(
        val id: String = "",
        val title: String = "",
        val description: String = "",
        val startdate: String = "",
        val duration: Int = 0,
        @SerialName("speaker-id")
        val speaker_id: List<String> = emptyList(),
        @SerialName("room-id")
        val room_id: String = "",
        val category: String = "",
        val booking: Booking = Booking(),
        val links: EmptyObject = EmptyObject(),
        @SerialName("-sidetracked")
        val sidetracked:Boolean = false
)

@Serializable
data class Booking(
        val from: String = "",
        val to: String = "",
        val seats: Int = 0)

@Serializable
class EmptyObject {
}

@Serializable
data class Speaker(
        val id: String = "",
        val firstname: String = "",
        val lastname: String = "",
        val image: String = "",
        val twitter: String = "",
        val description: String = ""
)

@Serializable
data class Feedback(
        val comment: String,
        val rating: Int
)

@Serializable
data class Keycloak(
        val realm: String,
        val auth_server_url: String,
        val ssl_required: String,
        val resource: String,
        val redirectUri: String,
        val useAccountManagement: String
)

// Synthetic class name
@Serializable
data class DefinitionsAudienceNames(val de: String = "", val en: String = "") {
    fun toMap(): Map<String, String> {
        return HashMap<String, String>().also {
            it["de"] = de
            it["en"] = en
        }.toImmutableMap()
    }
}

// Synthetic class name
@Serializable
data class DefinitionsEventTypeNames(val de: String = "", val en: String = "") {
    fun toMap(): Map<String, String> {
        return HashMap<String, String>().also {
            it["de"] = de
            it["en"] = en
        }.toImmutableMap()
    }
}

// Synthetic class name
@Serializable
data class DefinitionsLanguageNames(val de: String = "", val en: String = "") {
    fun toMap(): Map<String, String> {
        return HashMap<String, String>().also {
            it["de"] = de
            it["en"] = en
        }.toImmutableMap()
    }
}

// Synthetic class name
@Serializable
data class DefinitionsTrackNames(val de: String = "", val en: String = "") {
    fun toMap(): Map<String, String> {
        return HashMap<String, String>().also {
            it["de"] = de
            it["en"] = en
        }.toImmutableMap()
    }
}

// Synthetic class name
@Serializable
data class DefinitionsLocationNames(val de: String = "", val en: String = "") {
    fun toMap(): Map<String, String> {
        return HashMap<String, String>().also {
            it["de"] = de
            it["en"] = en
        }.toImmutableMap()
    }
}
