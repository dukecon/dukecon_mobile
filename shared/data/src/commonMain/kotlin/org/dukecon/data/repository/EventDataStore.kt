package org.dukecon.data.repository

import org.dukecon.data.model.*

interface EventDataStore {

  fun clearEvents()

  suspend fun getEvents(): List<EventEntity>
  fun saveEvents(events: List<EventEntity>)

  suspend fun getSpeakers(): List<SpeakerEntity>
  fun saveSpeakers(speakers: List<SpeakerEntity>)

  suspend fun getRooms(): List<RoomEntity>
  fun saveRooms(rooms: List<RoomEntity>)

  fun getFavorites(): List<FavoriteEntity>
  fun saveFavorites(favorite: List<FavoriteEntity>): List<FavoriteEntity>
  fun submitFeedback(feedback: FeedbackEntity)

  suspend fun getMetaData(): MetaDataEntity
  suspend fun saveMetaData(metaData: MetaDataEntity)
}
