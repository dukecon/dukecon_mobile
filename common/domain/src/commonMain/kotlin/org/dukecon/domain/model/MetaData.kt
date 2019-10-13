package org.dukecon.domain.model


data class MetaData(val id: String,
                    val audiences: List<Audience>,
                    val eventTypes: List<EventType>,
                    val languages: List<Language>,
                    val defaultLanguage: Language,
                    val tracks: List<Track>,
                    val locations: List<Location>,
                    val defaultIcon: String
)
