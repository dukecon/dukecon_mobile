package org.dukecon.remote.store

import org.dukecon.data.model.*
import org.dukecon.data.repository.ConferenceRemote
import org.dukecon.remote.api.DukeconApi
import org.dukecon.remote.mapper.EventEntityMapper
import org.dukecon.remote.mapper.MetaDataEntityMapper
import org.dukecon.remote.mapper.RoomEntityMapper
import org.dukecon.remote.mapper.SpeakerEntityMapper

class DukeconConferenceRemote(
        private val dukeconApi: DukeconApi,
        private val eventEntityMapper: EventEntityMapper,
        private val speakerEntityMapper: SpeakerEntityMapper,
        private val metaDataEntityMapper: MetaDataEntityMapper,
        private val roomEntityMapper: RoomEntityMapper
) : ConferenceRemote {
    override suspend fun getRooms(): List<RoomEntity> {
        return dukeconApi.getConference(dukeconApi.conference).metaData.locations.map { roomEntityMapper.mapFromRemote(it) }
    }

    override suspend fun getEvents(): List<EventEntity> {
        val events = dukeconApi.getConference(dukeconApi.conference).events
        return events.map { eventEntityMapper.mapFromRemote(it) }
    }

    override suspend fun getSpeakers(): List<SpeakerEntity> {
        return dukeconApi.getConference(dukeconApi.conference).speakers.map { speakerEntityMapper.mapFromRemote(it) }
    }

    override suspend fun getMetaData(): MetaDataEntity {
        return metaDataEntityMapper.mapFromRemote(dukeconApi.getConference(dukeconApi.conference).metaData)
    }

    override fun submitFeedback(feedback: FeedbackEntity) {
    }

    override fun getFavorites(): List<FavoriteEntity> {
        return emptyList()
    }

    override fun saveFavorites(favorite: List<FavoriteEntity>): List<FavoriteEntity> {
        TODO ("not supported without oauth")
    }
}