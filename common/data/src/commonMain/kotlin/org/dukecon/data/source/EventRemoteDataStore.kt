package org.dukecon.data.source

import org.dukecon.data.model.*
import org.dukecon.data.repository.ConferenceRemote
import org.dukecon.data.repository.EventDataStore

open class EventRemoteDataStore(private val conferenceRemote: ConferenceRemote) :
        EventDataStore {

    override suspend fun getMetaData(): MetaDataEntity {
        return conferenceRemote.getMetaData()
    }

    override fun submitFeedback(feedback: FeedbackEntity) {
        conferenceRemote.submitFeedback(feedback)
    }

    override fun saveFavorites(favorite: List<FavoriteEntity>): List<FavoriteEntity> {
        return conferenceRemote.saveFavorites(favorite)
    }

    // no call to API yet
    override fun getFavorites(): List<FavoriteEntity> {
        return conferenceRemote.getFavorites()
    }

    override suspend fun getRooms(): List<RoomEntity> {
        return conferenceRemote.getRooms()
    }

    override fun saveRooms(rooms: List<RoomEntity>) {
        throw UnsupportedOperationException()
    }

    override suspend fun getSpeakers(): List<SpeakerEntity> {
        return conferenceRemote.getSpeakers()
    }

    override fun saveSpeakers(speakers: List<SpeakerEntity>) {
        throw UnsupportedOperationException()
    }

    override fun saveEvents(events: List<EventEntity>) {
        throw UnsupportedOperationException()
    }

    override suspend fun saveMetaData(metaData: MetaDataEntity) {
        throw UnsupportedOperationException()
    }

    override suspend fun getEvents(): List<EventEntity> {
        return conferenceRemote.getEvents()
    }

    override fun clearEvents() {
        throw UnsupportedOperationException()
    }
}