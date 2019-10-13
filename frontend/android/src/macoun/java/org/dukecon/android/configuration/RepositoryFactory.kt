package org.dukecon.android.configuration

import okhttp3.OkHttpClient
import org.dukecon.cache.repository.JsonSerializedConferenceDataCache
import org.dukecon.data.mapper.*
import org.dukecon.data.repository.LocalAndRemoteDataRepository
import org.dukecon.data.source.ConferenceConfiguration
import org.dukecon.data.source.EventCacheDataStore
import org.dukecon.data.source.EventRemoteDataStore
import org.dukecon.domain.aspects.twitter.TwitterLinks
import org.dukecon.domain.repository.ConferenceRepository
import org.dukecon.remote.api.MacounApi
import org.dukecon.remote.mapper.EventEntityMapper
import org.dukecon.remote.mapper.MetaDataEntityMapper
import org.dukecon.remote.mapper.RoomEntityMapper
import org.dukecon.remote.mapper.SpeakerEntityMapper
import org.dukecon.remote.store.MacounConferenceRemote

object RepositoryFactory {
    fun createConferenceRepository(conferenceConfiguration: ConferenceConfiguration,
                                   okHttpClient: OkHttpClient): ConferenceRepository {

        val api = MacounApi(conferenceConfiguration.baseUrl,
                conferenceConfiguration.conferenceId)

        val conferenceRemote = MacounConferenceRemote(api,
                EventEntityMapper(),
                SpeakerEntityMapper(conferenceConfiguration, TwitterLinks()),
                MetaDataEntityMapper(),
                RoomEntityMapper())

        return LocalAndRemoteDataRepository(
                remoteDataStore = EventRemoteDataStore(conferenceRemote),
                localDataStore = EventCacheDataStore(JsonSerializedConferenceDataCache()),
                eventMapper = EventMapper(),
                speakerMapper = SpeakerMapper(TwitterLinks()),
                roomMapper = RoomMapper(),
                feedbackMapper = FeedbackMapper(),
                favoriteMapper = FavoriteMapper(),
                metadataMapper = MetaDateMapper())
    }
}