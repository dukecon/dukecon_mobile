package org.dukecon.data.repository

import org.dukecon.data.model.*

interface ConferenceRemote {
    suspend fun getEvents(): List<EventEntity>
    suspend fun getSpeakers(): List<SpeakerEntity>
    suspend fun getRooms(): List<RoomEntity>
    fun submitFeedback(feedback: FeedbackEntity)
    suspend fun getMetaData(): MetaDataEntity
    fun getFavorites(): List<FavoriteEntity>
    fun saveFavorites(favorite: List<FavoriteEntity>): List<FavoriteEntity>
}