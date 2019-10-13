package org.dukecon.data.source

import org.dukecon.data.model.*
import org.dukecon.data.repository.ConferenceDataCache
import org.dukecon.data.repository.EventDataStore

/**
 * Implementation of the [EventDataStore] interface to provide a means of communicating
 * with the local data source
 */
open class EventCacheDataStore constructor(private val conferenceDataCache: ConferenceDataCache) :
        EventDataStore {
    override suspend fun getMetaData(): MetaDataEntity {
        return conferenceDataCache.getMetaData()
    }


    override fun submitFeedback(feedback: FeedbackEntity) {
        throw UnsupportedOperationException()
    }

    override fun saveFavorites(favorite: List<FavoriteEntity>): List<FavoriteEntity> {
        return conferenceDataCache.saveFavorites(favorite)
    }

    override fun getFavorites(): List<FavoriteEntity> {
        return conferenceDataCache.getFavorites()
    }


    override suspend fun getRooms(): List<RoomEntity> {
        return conferenceDataCache.getRooms()
    }

    override fun saveRooms(rooms: List<RoomEntity>) {
        conferenceDataCache.saveRooms(rooms)
    }

    override suspend fun getSpeakers(): List<SpeakerEntity> {
        return conferenceDataCache.getSpeakers()
    }

    override fun saveSpeakers(speakers: List<SpeakerEntity>) {
        conferenceDataCache.saveSpeakers(speakers)
    }

    override fun clearEvents() {
        return conferenceDataCache.clearEvents()
    }

    override fun saveEvents(events: List<EventEntity>) {
        conferenceDataCache.saveEvents(events)
    }

    override suspend fun getEvents(): List<EventEntity> {
        return conferenceDataCache.getEvents()
    }

    override suspend fun saveMetaData(metaData: MetaDataEntity) {
        conferenceDataCache.saveMetaData(metaData)
    }
}