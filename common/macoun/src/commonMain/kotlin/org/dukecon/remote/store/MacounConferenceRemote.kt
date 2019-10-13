package org.dukecon.remote.store

import org.dukecon.data.model.*
import org.dukecon.data.repository.ConferenceRemote
import org.dukecon.remote.api.MacounApi
import org.dukecon.remote.mapper.*

class MacounConferenceRemote(
        private val macounApi: MacounApi,
        private val eventEntityMapper: EventEntityMapper,
        private val speakerEntityMapper: SpeakerEntityMapper,
        private val metaDataEntityMapper: MetaDataEntityMapper,
        private val roomEntityMapper: RoomEntityMapper
) : ConferenceRemote {
    override suspend fun getRooms(): List<RoomEntity> {
        val str = macounApi.getConferenceStr(macounApi.conference)
        val conference = ConferenceJsonDeserializer.toConference(str)
        return conference.rooms.map { roomEntityMapper.mapFromRemote(it) }
    }

    override suspend fun getEvents(): List<EventEntity> {
        val str = macounApi.getConferenceStr(macounApi.conference) //.talks.map { eventEntityMapper.mapFromRemote(it) }
        val conference = ConferenceJsonDeserializer.toConference(str)
        return conference.talks.map { eventEntityMapper.mapFromRemote(it) }
    }

    override suspend fun getSpeakers(): List<SpeakerEntity> {
        val str = macounApi.getConferenceStr(macounApi.conference)
        val conference = ConferenceJsonDeserializer.toConference(str)
        return conference.speakers.map { speakerEntityMapper.mapFromRemote(it) }
    }

    override suspend fun getMetaData(): MetaDataEntity {
        val str = macounApi.getConferenceStr(macounApi.conference)
        val conference = ConferenceJsonDeserializer.toConference(str)
        return metaDataEntityMapper.mapFromRemote(conference)
    }

    override fun submitFeedback(feedback: FeedbackEntity) {
    }

    override fun getFavorites(): List<FavoriteEntity> {
        return emptyList()
    }

    override fun saveFavorites(favorite: List<FavoriteEntity>): List<FavoriteEntity> {
        return emptyList()
    }
}