package org.dukecon.cache.model

import kotlinx.serialization.Serializable

@Serializable
data class ConferenceModel(
    val sessions: List<EventModel> = emptyList(),
    val speakers: List<SpeakerModel> = emptyList(),
    val favorites: List<FavoriteModel> = emptyList(),
    val rooms: List<RoomModel> = emptyList(),
    val metaData: MetaDataModel = emptyMetaModel()
)

fun emptyMetaModel(): MetaDataModel = MetaDataModel(id = "", locations = emptyList())
