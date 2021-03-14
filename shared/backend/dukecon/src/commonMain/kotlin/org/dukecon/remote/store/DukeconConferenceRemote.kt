package org.dukecon.remote.store

import org.dukecon.aspects.logging.LogLevel
import org.dukecon.aspects.logging.log
import org.dukecon.data.model.*
import org.dukecon.data.repository.ConferenceRemote
import org.dukecon.data.source.ConferenceConfiguration
import org.dukecon.remote.api.DukeconApi
import org.dukecon.remote.mapper.*
import org.dukecon.remote.models.MetaData

class DukeconConferenceRemote(
    private val dukeconApi: DukeconApi,
    conferenceConfiguration: ConferenceConfiguration,
    twitterLinkMapper: TwitterUrlMapper
) : ConferenceRemote {
    private val eventEntityMapper = EventEntityMapper()
    private val speakerEntityMapper =
        SpeakerEntityMapper(conferenceConfiguration, twitterLinkMapper)
    private val metaDataEntityMapper = MetaDataEntityMapper()
    private val roomEntityMapper = RoomEntityMapper()

    override suspend fun getRooms(): List<RoomEntity> {
        return dukeconApi.getConference(dukeconApi.conference).metaData?.let { meta ->
            meta.locations?.let { loc -> loc.map { roomEntityMapper.mapFromRemote(it) } }
        } ?: emptyList()
    }

    override suspend fun getEvents(): List<EventEntity> {
        log(LogLevel.DEBUG, "DukeconConferenceRemote", "getEvents==>")
        val events = dukeconApi.getConference(dukeconApi.conference).events
        log(LogLevel.DEBUG, "DukeconConferenceRemote", "getEvents<==")
        return events?.let { it.map { event -> eventEntityMapper.mapFromRemote(event) } }
            ?: emptyList()
    }

    override suspend fun getSpeakers(): List<SpeakerEntity> {
        return dukeconApi.getConference(dukeconApi.conference).speakers?.let { speakers ->
            speakers.map { speakerEntityMapper.mapFromRemote(it) }
        } ?: emptyList()
    }

    override suspend fun getMetaData(): MetaDataEntity {
        return metaDataEntityMapper.mapFromRemote(
            dukeconApi.getConference(dukeconApi.conference).metaData
                ?: MetaData()
        )
    }

    override fun submitFeedback(feedback: FeedbackEntity) {
    }

    override fun getFavorites(): List<FavoriteEntity> {
        return emptyList()
    }

    override fun saveFavorites(favorite: List<FavoriteEntity>): List<FavoriteEntity> {
        TODO("not supported without oauth")
    }
}