package org.dukecon.domain.model

data class MetaData(
    val id: String = "",
    val audiences: List<Audience> = emptyList(),
    val eventTypes: List<EventType> = emptyList(),
    val languages: List<Language> = emptyList(),
    val defaultLanguage: Language = Language("", "", 0, emptyMap(), ""),
    val tracks: List<Track> = emptyList(),
    val locations: List<Location> = emptyList(),
    val defaultIcon: String = ""
)
