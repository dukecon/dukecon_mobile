package org.dukecon.data.model

data class MetaDataEntity(val id: String,
                          val audiences: List<AudienceEntity>,
                          val eventTypes: List<EventTypeEntity>,
                          val languages: List<LanguageEntity>,
                          val defaultLanguage: LanguageEntity,
                          val tracks: List<TrackEntity>,
                          val locations: List<LocationsEntity>,
                          val defaultIcon: String
)
