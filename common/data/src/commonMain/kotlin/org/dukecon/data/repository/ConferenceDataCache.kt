package org.dukecon.data.repository

import org.dukecon.data.model.*


interface ConferenceDataCache {

    fun clearEvents()

    fun saveEvents(events: List<EventEntity>)

    fun getEvents(): List<EventEntity>

    fun getSpeakers(): List<SpeakerEntity>
    fun getSpeaker(id: String): SpeakerEntity

    fun saveSpeakers(speakers: List<SpeakerEntity>)

    fun getRooms(): List<RoomEntity>
    fun saveRooms(rooms: List<RoomEntity>)

    fun getEvent(id: String): EventEntity

    fun getFavorites(): List<FavoriteEntity>
    fun saveFavorites(favorite: List<FavoriteEntity>): List<FavoriteEntity>

    fun getKeycloak(): KeycloakEntity

    fun getMetaData(): MetaDataEntity
    fun saveMetaData(metaDataEntity: MetaDataEntity)
}